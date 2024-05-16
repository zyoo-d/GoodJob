package com.good.board.report.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.good.board.report.model.ReportCommonDTO;
import com.test.util.DBUtil;

/**
 * 신고 공통 정보를 다루는 DAO 클래스입니다.
 */
public class ReportCommonDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	/**
     * ReportCommonDAO 생성자입니다.
     * 데이터베이스 연결을 설정합니다.
     */
	public ReportCommonDAO() {
		this.conn = DBUtil.open();
	}

	 /**
     * 데이터베이스 연결을 닫습니다.
     */
	public void close() {
		try {
			this.conn.close();
		} catch (Exception e) {
			System.out.println("ReportCommonDAO.close 오류");
			e.printStackTrace();
		}
	}

	/**
     * 총 신고 게시글 개수를 가져옵니다.
     *
     * @param rp_seq 신고 사유 번호 (nullable)
     * @return 총 신고 게시글 개수
     */
	public int getTotalReportCount(Integer rp_seq) {

		try {

			StringBuilder sql = new StringBuilder("SELECT COUNT(*) AS cnt FROM vwAllReportList");

			if (rp_seq != null) {
				sql.append(" WHERE rp_seq = ?");
			}

			pstat = conn.prepareStatement(sql.toString());

			if (rp_seq != null) {
				pstat.setInt(1, rp_seq);
			}

			rs = pstat.executeQuery();

			if(rs.next()) {

				return rs.getInt("cnt");

			}

		} catch (Exception e) {
			System.out.println("총 신고 게시글 갯수 로드 실패");
			e.printStackTrace();
		}

		return 0;

	}

	/**
     * 특정 사용자의 총 신고 횟수를 가져옵니다.
     *
     * @param id 사용자 ID
     * @return 사용자의 총 신고 횟수
     */
	public int getUserTotalReportCount(String id) {

		try {

			String sql = "select count(*) as cnt from vwAllReports where id = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);

			rs = pstat.executeQuery();
			if(rs.next()) {

				return rs.getInt("cnt");

			}

		} catch (Exception e) {
			System.out.println("유저 신고 횟수 로드 실패");
			e.printStackTrace();
		}

		return 0;

	}

	 /**
     * 게시판별 신고 횟수를 가져옵니다.
     *
     * @return 게시판별 신고 횟수가 담긴 HashMap (게시판 타입 -> 신고 횟수)
     */
	public HashMap<String, Integer> getReportCountByBoard(){

		HashMap<String, Integer> map = new HashMap<>();

		try {

			String sql = "select report_type ,count(*) as cnt from vwAllReports group by report_type";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			while(rs.next()) {

				map.put(rs.getString("report_type"), rs.getInt("cnt"));

			}

			return map;

		} catch (Exception e) {
			System.out.println("게시판별 신고 횟수 로드 실패");
			e.printStackTrace();
		}

		return null;

	}

	 /**
     * 전체 신고 목록을 가져옵니다.
     *
     * @param rp_seq     신고 사유 번호 (nullable)
     * @param startIndex 시작 인덱스
     * @param endIndex   끝 인덱스
     * @return 신고 목록
     */
	public ArrayList<ReportCommonDTO> getAllReportList(Integer rp_seq, int startIndex, int endIndex){

		ArrayList<ReportCommonDTO> list = new ArrayList<>();
		try {
			StringBuilder sql = new StringBuilder("SELECT * FROM (SELECT ROWNUM AS rnum, t.* FROM (SELECT * "
			        + " FROM vwAllReportList "
			        + " WHERE (TYPE, sub_type, report_seq) NOT IN ("
			        + "     SELECT TYPE, sub_type, report_seq "
			        + "     FROM tblReportCheck "
			        + " )");

			if (rp_seq != null) {
			    sql.append(" AND rp_seq = ?");
			}

			sql.append(" ORDER BY report_regdate DESC) t) WHERE rnum BETWEEN ? AND ?");

			pstat = conn.prepareStatement(sql.toString());
			int parameterIndex = 1;
			if (rp_seq != null) {
				pstat.setInt(parameterIndex++, rp_seq);
			}
			pstat.setInt(parameterIndex++, startIndex + 1);
			pstat.setInt(parameterIndex, endIndex);

			rs = pstat.executeQuery();


			while(rs.next()) {

				ReportCommonDTO dto = new ReportCommonDTO();
				String type = rs.getString("type");
				String sub_type = rs.getString("sub_type");
				String seq = rs.getString("seq");


				dto.setType(type);
				dto.setSub_type(sub_type);
				dto.setSeq(seq);
				dto.setTitle(parseTitle(rs.getString("title")));
				dto.setRegdate(rs.getString("regdate"));
				dto.setWriter_id(rs.getString("writer_id"));
				dto.setReporter_id(rs.getString("reporter_id"));
				dto.setReport_detail(rs.getString("report_detail"));
				dto.setReport_regdate(rs.getString("report_regdate"));
				dto.setReport_count(rs.getString("report_count"));
				dto.setRp_seq(parseReportType(rs.getString("rp_seq")));
				dto.setReport_seq(rs.getString("report_seq"));

				System.out.println(type);

				if(type.equals("comment")) {
					String parentSeq = findParentSeq(seq, type, sub_type);
					dto.setParent_seq(parentSeq != null ? parentSeq : seq);
				} else {
					dto.setParent_seq(seq);
				}


				list.add(dto);

			}

		} catch (Exception e) {
			System.out.println("신고접수 목록 불러오기 실패");
			e.printStackTrace();
		}

		return list;

	}


	/**
	 * 부모 게시글 번호를 찾습니다.
	 *
	 * @param seq       댓글 번호
	 * @param type      게시글 타입
	 * @param sub_type  게시글 하위 타입
	 * @return 부모 게시글 번호
	 */
	private String findParentSeq(String seq, String type, String sub_type) {
		String parentSeq = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;

		try {
			String sql = "";

			switch (sub_type) {
			case "qna":
				sql = "select qna_seq as seq from tblQnAComment where qna_cm_seq = ? ";
				break;
			case "study":
				sql = "select std_seq as seq from tblStdComment where std_cm_seq = ? ";
				break;
			case "company":
				sql = "select cp_seq as seq from tblLiveComment where live_seq = ?";
				break;
			}

			System.out.println(sql);

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			rs = pstat.executeQuery();

			if (rs.next()) {
				parentSeq = rs.getString("seq");
			}
		} catch (Exception e) {
			System.out.println("댓글 어디 게시글 인지 찾기 실패");
			e.printStackTrace();
		} finally {
			// 자원 해제
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstat != null) {
				try {
					pstat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return parentSeq;
	}

	/**
	 * 신고 유형을 파싱합니다.
	 *
	 * @param re_seq 신고 유형 번호
	 * @return 파싱된 신고 유형
	 */
	private String parseReportType(String re_seq) {

		String result = "";

		switch (re_seq) {
		case "1": 
			result = "비방/욕설";
			break;
		case "2":
			result = "허위사실";
			break;
		case "3":
			result = "개인정보노출";
			break;
		case "4":
			result = "음란성";
			break;
		case "5":
			result = "게시글 도배";
			break;
		case "6":
			result = "부적절한 홍보";
			break;
		case "7":
			result = "기타";
			break;
		}

		return result;

	}


	/**
	 * 제목을 파싱합니다.
	 *
	 * @param title 파싱할 제목
	 * @return 파싱된 제목
	 */
	private String parseTitle(String title) {


		String result = title;
		if (result.length() > 20) {
			result = result.substring(0, 20) + "..";
		}

		return result;

	}

	/**
	 * 최근 신고된 게시글 제목 목록을 가져옵니다.
	 *
	 * @param count 가져올 게시글 수
	 * @return 최근 신고된 게시글 제목 목록
	 */
	public ArrayList<ReportCommonDTO> getRecentReportTitleList(int count) {
		ArrayList<ReportCommonDTO> list = new ArrayList<>();

		try {
			String sql = "SELECT seq, title, sub_type FROM (SELECT seq, title, sub_type FROM vwAllReportList ORDER BY report_regdate DESC) WHERE ROWNUM <= ?";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, count);
			rs = pstat.executeQuery();

			while(rs.next()) {
				ReportCommonDTO dto = new ReportCommonDTO();
				dto.setSeq(rs.getString("seq"));
				dto.setTitle(rs.getString("title"));
				dto.setSub_type(rs.getString("sub_type"));
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println("최근 신고된 게시글 불러오기 5개 실패");
			e.printStackTrace();
		}

		return list;
	}


/**
 * 사용자를 차단합니다.
 *
 * @param blockStatus  차단 상태 정보가 담긴 HashMap (사용자 ID -> 기존 차단 해제일)
 * @param status       차단 상태
 * @param blockDate    차단 시작일
 * @param releaseDate  차단 해제일
 * @param blockReason  차단 사유
 */
	public void blockuser(HashMap<String, String> blockStatus, String status, String blockDate, String releaseDate, String blockReason) {
		try {
			String sql = "INSERT INTO tblBanLog (ban_seq, ban_reason, ban_startdate, ban_enddate, id) VALUES (seqBanLog.nextVal, ?, ?, ?, ?)";
			List<String> insertIds = new ArrayList<>();
			HashMap<String, String> updateIds = new HashMap<>();

			for (Map.Entry<String, String> entry : blockStatus.entrySet()) {
				String id = entry.getKey();
				String oldReleaseDate = entry.getValue();

				if (oldReleaseDate != null) {
					updateIds.put(id, oldReleaseDate);
				} else {
					insertIds.add(id);
				}
			}

			if (!insertIds.isEmpty()) {
				pstat = conn.prepareStatement(sql);

				for (String id : insertIds) {
					pstat.setString(1, blockReason);
					pstat.setString(2, blockDate);
					pstat.setString(3, releaseDate);
					pstat.setString(4, id);
					pstat.addBatch();
				}

				pstat.executeBatch();
			}

			updateBlockPeriod(updateIds, status, blockDate, releaseDate, blockReason);
			changeLvUser(insertIds);


		} catch (Exception e) {
			System.out.println("유저 밴 실패");
			e.printStackTrace();
		}
	}




	/**
	 * 사용자의 레벨을 변경합니다.
	 *
	 * @param insertIds 레벨을 변경할 사용자 ID 목록
	 */
	private void changeLvUser(List<String> insertIds) {

		try {
			String sql = "UPDATE tblUser SET lv = 3 WHERE id = ?";
			PreparedStatement pstate = conn.prepareStatement(sql);

			for (String userId : insertIds) {
				pstate.setString(1, userId);
				pstate.addBatch();
				System.out.println(userId);
			}

			pstate.executeBatch();
		} catch (Exception e) {
			System.out.println("유저 밴 권한변경 실패");
			e.printStackTrace();
		} 
	}

	/**
	 * 차단 기간을 업데이트합니다.
	 *
	 * @param updateIds    업데이트할 사용자 ID와 기존 차단 해제일이 담긴 HashMap
	 * @param status       차단 상태
	 * @param blockDate    차단 시작일
	 * @param releaseDate  차단 해제일
	 * @param blockReason  차단 사유
	 */
	private void updateBlockPeriod(HashMap<String,String> updateIds, String status, String blockDate, String releaseDate, String blockReason) {
		try {


			StringBuilder updateSql = new StringBuilder("UPDATE tblBanLog SET ban_reason = ?, ban_enddate = ? WHERE id = ?");
			PreparedStatement updatePstat = conn.prepareStatement(updateSql.toString());

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			for (Map.Entry<String, String> entry : updateIds.entrySet()) {
				String id = entry.getKey();
				String oldReleaseDate = entry.getValue();

				if (oldReleaseDate != null) {
					// 이미 차단된 사용자
					Date newReleaseDate = calculateNewReleaseDate(oldReleaseDate, blockDate, releaseDate);

					updatePstat.setString(1, blockReason);
					updatePstat.setString(2, dateFormat.format(newReleaseDate));
					updatePstat.setString(3, id);
					updatePstat.addBatch();
				}
			}

			updatePstat.executeBatch();
		} catch (Exception e) {
			System.out.println("이미 밴 당한 유저 차단일자 누적 실패");
			e.printStackTrace();
		}
	}

	/**
	 * 새로운 차단 해제일을 계산합니다.
	 *
	 * @param oldReleaseDate 기존 차단 해제일
	 * @param blockDate      차단 시작일
	 * @param releaseDate    차단 해제일
	 * @return 새로운 차단 해제일
	 */
	private Date calculateNewReleaseDate(String oldReleaseDate, String blockDate, String releaseDate) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date oldDate = dateFormat.parse(oldReleaseDate);
			Date startDate = dateFormat.parse(blockDate);
			Date endDate = dateFormat.parse(releaseDate);

			long diffDays = (endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000);
			Calendar cal = Calendar.getInstance();
			cal.setTime(oldDate);
			cal.add(Calendar.DATE, (int) diffDays);

			return cal.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 사용자들의 차단 여부를 확인합니다.
	 *
	 * @param userIds 확인할 사용자 ID 목록
	 * @return 사용자별 차단 상태 정보가 담긴 HashMap (사용자 ID -> 차단 해제일)
	 */
	public HashMap<String, String> checkBlock(List<String> userIds) {
		HashMap<String, String> blockStatus = new HashMap<>();

		try {

			StringBuilder sql= new StringBuilder("select id, ban_enddate from tblBanLog where ban_enddate > sysdate and id in(");

			for(int i =0; i<userIds.size(); i++) {
				sql.append((i==0) ? "?" : ",?");
			}

			sql.append(")");


			pstat = conn.prepareStatement(sql.toString());

			for(int i=0; i<userIds.size(); i++) {
				pstat.setString(i+1, userIds.get(i));
			}

			rs = pstat.executeQuery();

			while(rs.next()) {
				String id = rs.getString("id");
				String endDate = rs.getString("ban_enddate");
				blockStatus.put(id, endDate);

			}

			for(String id : userIds) {
				if(!blockStatus.containsKey(id)) {
					blockStatus.put(id, null);
				}
			}

		} catch (Exception e) {
			System.out.println("유저 밴 여부 확인 실패");
			e.printStackTrace();
		}

		return blockStatus;

	}

	/**
	 * 신고 접수를 확인합니다.
	 *
	 * @param report_seqs       신고 번호 목록
	 * @param report_types      신고 타입 목록
	 * @param report_sub_types  신고 하위 타입 목록
	 * @param id                확인자 ID
	 */
	public void confirmReport(List<String> report_seqs, List<String> report_types, List<String> report_sub_types, String id) {
		try {
			String sql = "INSERT INTO tblReportCheck (rc_seq, type, sub_type, report_seq, id, checked_date) VALUES (seqReportCheck.nextVal, ?, ?, ?, ?, SYSDATE)";
			pstat = conn.prepareStatement(sql);

			for (int i = 0; i < report_seqs.size(); i++) {
				String report_seq = report_seqs.get(i);
				String report_type = report_types.get(i);
				String report_sub_type = report_sub_types.get(i);


				pstat.setString(1, report_type);
				pstat.setString(2, report_sub_type);
				pstat.setString(3, report_seq);
				pstat.setString(4, id);

				pstat.addBatch();
			}

			pstat.executeBatch();
		} catch (Exception e) {
			System.out.println("신고 접수 확인 데이터 삽입 실패");
			e.printStackTrace();
		}
	}
	
	/**
	 * 전체 신고 게시글 목록을 가져옵니다.
	 *
	 * @param startIndex 시작 인덱스
	 * @param endIndex   끝 인덱스
	 * @return 신고 게시글 목록
	 */
	public ArrayList<ReportCommonDTO> getAllReportBoardList(int startIndex, int endIndex){
		
		ArrayList<ReportCommonDTO> list = new ArrayList<>();
		
		try {
			
			String sql = "select * from (select rownum as rnum, t.* from (select writer_id, type, sub_type, seq, title, to_char(regdate,'yyyy-mm-dd') as regdate, count(*) as cnt from vwallreportlist group by writer_id, type, sub_type, seq, title,regdate order by cnt desc)t) where rnum between ? and ?";
			
			pstat = conn.prepareStatement(sql);
			
			int parameterIndex = 1;
			
			pstat.setInt(parameterIndex++, startIndex + 1);
			pstat.setInt(parameterIndex, endIndex);
			
			rs = pstat.executeQuery();
			
			
			while(rs.next()) {
				
				ReportCommonDTO dto = new ReportCommonDTO();
				String type = rs.getString("type");
				String sub_type = rs.getString("sub_type");
				String seq = rs.getString("seq");
				
				
				dto.setType(type);
				dto.setSub_type(sub_type);
				dto.setSeq(seq);
				dto.setRegdate(rs.getString("regdate"));
				dto.setTitle(rs.getString("title"));
				System.out.println(rs.getString("writer_id"));
				dto.setWriter_id(rs.getString("writer_id"));
				dto.setReport_count(rs.getString("cnt"));
				
				
				if(type.equals("comment")) {
					String parentSeq = findParentSeq(seq, type, sub_type);
					dto.setParent_seq(parentSeq != null ? parentSeq : seq);
				} else {
					dto.setParent_seq(seq);
				}
				
				list.add(dto);
				
			}
			
		} catch (Exception e) {
			System.out.println("신고 접수 목록 로드 실패");
			e.printStackTrace();
		}
		
		return list;
		
	}

	/**
	 * 총 신고 게시글 수를 가져옵니다.
	 *
	 * @return 총 신고 게시글 수
	 */
	public int getTotalReportBoardCount() {
		
		try {
			
			String sql = "select count(*) as cnt from (select count(*) from vwAllReportList group by type, sub_type, seq)";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				
				return rs.getInt("cnt");
				
			}
			
		} catch (Exception e) {
			System.out.println("신고 게시글 수 로드 실패");
			e.printStackTrace();
		}
		return 0;
	}



}

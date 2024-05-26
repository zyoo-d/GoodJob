package com.good.company.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.good.board.model.StudyDTO;
import com.good.company.model.ReviewDTO;
import com.test.util.DBUtil;

/**
 * ReviewDAO 클래스는 기업 리뷰 관련 데이터베이스 작업을 수행합니다.
 */
public class ReviewDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	 /**
     * 기본 생성자입니다. 데이터베이스 연결을 수행합니다.
     */
	public ReviewDAO() {
		this.conn = DBUtil.open();
	}

	 /**
     * 데이터베이스 연결을 닫습니다.
     */
	public void close() {
		try {
			this.conn.close();
		} catch (Exception e) {
			System.out.println("ReviewDAO.close 오류");
			e.printStackTrace();
		}
	}

	/**
	 * 등록된 리뷰수 불러오는 메서드
	 * 
	 * @param input 기업 시퀀스
	 * @return 리뷰수
	 */
	public int countRiview(String input) {
		try {
			String sql = "select count(*) as cnt from tblCompanyReview where cp_seq = ?";

			pstat = conn.prepareStatement(sql);
			String cp_seq = input;

			pstat.setString(1, cp_seq);
			rs = pstat.executeQuery();

			while (rs.next()) {
				int cnt = rs.getInt("cnt");
				return cnt;
			}

		} catch (Exception e) {
			System.out.println("ReviewDAO.countRiview");
			e.printStackTrace();
		}

		return 0;
	}


	/**
	 * 기업리뷰 목록 조회하는 메서드
	 * 
	 * @param cp_seq 기업 시퀀스
	 * @return 리뷰 목록
	 */
	public ArrayList<ReviewDTO> listReview(String cp_seq) {
		try {
			String sql = "select * from vwCompanyReview where cp_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, cp_seq);

			rs = pstat.executeQuery();

			ArrayList<ReviewDTO> listReview = new ArrayList<ReviewDTO>();
			while (rs.next()) {
				ReviewDTO dto = new ReviewDTO();

				dto.setCp_seq(rs.getString("cp_seq"));
				dto.setId(rs.getString("id"));
				dto.setCp_rv_seq(rs.getString("cp_rv_seq"));
				dto.setSalary_score(rs.getDouble("salary_score"));
				dto.setWelfare_score(rs.getDouble("welfare_score"));
				dto.setStability_score(rs.getDouble("stability_score"));
				dto.setCulture_score(rs.getDouble("culture_score"));
				dto.setGrowth_score(rs.getDouble("growth_score"));
				dto.setLinereview(rs.getString("linereview"));
				dto.setGood(rs.getString("good"));
				dto.setBad(rs.getString("bad"));
				dto.setCp_rv_regdate(rs.getString("cp_rv_regdate"));
				dto.setCp_rv_confirm(rs.getInt("cp_rv_confirm"));
				dto.setNickname(rs.getString("nickname"));

				listReview.add(dto);
			}
			return listReview;

		} catch (Exception e) {
			System.out.println("ReviewDAO.listReview");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 기업별 태그리스트를 호출하는 메서드
	 * 
	 * @param cp_seq 기업 시퀀스
	 * @return 태그 리스트
	 */
	public ArrayList<ReviewDTO> tagList(String cp_seq) {
		ArrayList<ReviewDTO> tagList = new ArrayList<>();
		String sql = "SELECT cr.cp_seq, t.tag_keyword, COUNT(*) as tag_count " + "FROM tblCompanyReview cr "
				+ "JOIN tblReviewTag rt ON cr.cp_rv_seq = rt.cp_rv_seq " + "JOIN tblTag t ON t.tag_seq = rt.tag_seq "
				+ "WHERE cr.cp_seq = ? " + "GROUP BY cr.cp_seq, t.tag_keyword " + "ORDER BY tag_count DESC";

		try (PreparedStatement pstat = conn.prepareStatement(sql)) {
			pstat.setString(1, cp_seq);
			try (ResultSet rs = pstat.executeQuery()) {
				while (rs.next()) {
					ReviewDTO dto = new ReviewDTO();
					dto.setCp_seq(rs.getString("cp_seq"));
					ArrayList<String> tlist = new ArrayList<>();
					tlist.add(rs.getString("tag_keyword"));
					dto.setTag_list(tlist);
					tagList.add(dto);
				}
			}
		} catch (Exception e) {
			System.out.println("ReviewDAO.tagList");
			e.printStackTrace();
		}
		return tagList;
	}

	/**
	 * 등록수 기준 상위 태그 조회 메서드
	 * 
	 * @return 상위 태그 리스트
	 */
	public ArrayList<String> showTagList() {
		try {
			ArrayList<String> showTagList = new ArrayList<>();
			String sql = "select tag_keyword from (select * from tblTag t left join (select tag_seq ,count(*) as cnt from tblreviewTag group by tag_seq order by cnt desc) rt on t.tag_seq = rt.tag_seq) where rownum <=10";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			while (rs.next()) {
				showTagList.add(rs.getString("tag_keyword"));
			}

			return showTagList;

		} catch (Exception e) {
			System.out.println("ReviewDAO.showTagList");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 리뷰 추가 메서드
	 * 
	 * @param bdto 리뷰 정보를 담은 DTO 객체
	 * @return 추가된 행의 수
	 */
	public int addReview(ReviewDTO bdto) {
		try {
			String sql = "insert into tblCompanyReview "
					+ "(CP_RV_SEQ,SALARY_SCORE,WELFARE_SCORE,STABILITY_SCORE,CULTURE_SCORE,GROWTH_SCORE,LINEREVIEW,GOOD,BAD,CP_RV_REGDATE,CP_SEQ,ID,CP_RV_CONFIRM) VALUES "
					+ "(SEQCOMPANYREVIEW.nextVal,?,?,?,?,?,?,?,?,sysdate,?,?,0)"; // cp_rv_confirm=0(대기)

			pstat = conn.prepareStatement(sql);
			pstat.setDouble(1, bdto.getSalary_score());
			pstat.setDouble(2, bdto.getWelfare_score());
			pstat.setDouble(3, bdto.getStability_score());
			pstat.setDouble(4, bdto.getCulture_score());
			pstat.setDouble(5, bdto.getGrowth_score());
			pstat.setString(6, bdto.getLinereview());
			pstat.setString(7, bdto.getGood());
			pstat.setString(8, bdto.getBad());
			pstat.setString(9, bdto.getCp_seq());
			pstat.setString(10, bdto.getId());

			return pstat.executeUpdate();
		} catch (Exception e) {
			System.out.println("ReivewDAO.addReview");
			e.printStackTrace();
		}
		return 0;
	}


	/**
	 * 리뷰 시퀀스로 리뷰 정보를 조회하는 메서드
	 * 
	 * @param cp_rv_seq 리뷰 시퀀스
	 * @return 리뷰 정보를 담은 DTO 객체
	 */
	public ReviewDTO getReviewByCpRvSeq(String cp_rv_seq) {
		try {
			String sql = "SELECT * FROM tblCompanyReview WHERE cp_rv_seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, cp_rv_seq);
			rs = pstat.executeQuery();

			if (rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setCp_seq(rs.getString("cp_seq"));
				dto.setId(rs.getString("id"));
				dto.setCp_rv_seq(rs.getString("cp_rv_seq"));
				dto.setSalary_score(rs.getDouble("salary_score"));
				dto.setWelfare_score(rs.getDouble("welfare_score"));
				dto.setStability_score(rs.getDouble("stability_score"));
				dto.setCulture_score(rs.getDouble("culture_score"));
				dto.setGrowth_score(rs.getDouble("growth_score"));
				dto.setLinereview(rs.getString("linereview"));
				dto.setGood(rs.getString("good"));
				dto.setBad(rs.getString("bad"));
				dto.setCp_rv_regdate(rs.getString("cp_rv_regdate"));
				dto.setCp_rv_confirm(rs.getInt("cp_rv_confirm"));

				return dto;
			}
		} catch (Exception e) {
			System.out.println("ReviewDAO.getReviewByCpRvSeq");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 관리자 리뷰 승인여부 변경 메서드
	 * 
	 * @param cp_rv_seq 리뷰 시퀀스
	 * @param confirm   승인 여부 (0: 대기, 1: 승인, 2: 반려)
	 * @return 업데이트 성공 여부
	 */
	public boolean updateReviewConfirm(String cp_rv_seq, int confirm) {
		try {
			String sql = "UPDATE tblCompanyReview SET cp_rv_confirm = ? WHERE cp_rv_seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, confirm);
			pstat.setString(2, cp_rv_seq);
			int result = pstat.executeUpdate();
			return result > 0;
		} catch (Exception e) {
			System.out.println("ReviewDAO.updateReviewConfirm()");
			e.printStackTrace();
		} 
		
		return false;
	}
	/**
	 * 여러 개의 리뷰를 일괄 승인하는 메서드
	 * 
	 * @param cp_rv_seqs 승인할 리뷰 시퀀스 배열
	 * @return 업데이트 성공 여부
	 */
	public boolean allApprove(String[] cp_rv_seqs) {
	    try {
	        String sql = "UPDATE tblCompanyReview SET cp_rv_confirm = 1 WHERE cp_rv_seq IN (";
	        
	        
	        for (int i = 0; i < cp_rv_seqs.length; i++) {
	            sql += "?";
	            if (i < cp_rv_seqs.length - 1) {
	                sql += ",";
	            }
	        }
	        sql += ")";
	        
	        pstat = conn.prepareStatement(sql);
	        
	        
	        for (int i = 0; i < cp_rv_seqs.length; i++) {
	            pstat.setString(i + 1, cp_rv_seqs[i]);
	        }
	        
	        int result = pstat.executeUpdate();
	        return result > 0;
	    } catch (Exception e) {
	        System.out.println("ReviewDAO.allApprove()");
	        e.printStackTrace();
	    } 
	    
	    return false;
	}
	/**
	 * 리뷰 삭제 메서드
	 * 
	 * @param cp_rv_seq 삭제할 리뷰 시퀀스
	 */
	public void deleteReview(String cp_rv_seq) {
		try {

			String sql ="";
			sql = "delete from tblReviewTag where cp_rv_seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, cp_rv_seq);
			pstat.executeUpdate();

			sql = "delete from tblCompanyReview where cp_rv_seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, cp_rv_seq);
			pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("ReviewDAO.deleteReview");
			e.printStackTrace();
		}
	}

	/**
	 * 리뷰 수정 메서드
	 * 
	 * @param rdto 수정할 리뷰 정보를 담은 DTO 객체
	 */
	public void updateReviewByCpRvSeq(ReviewDTO rdto) {
		try {
			String sql = "UPDATE tblCompanyReview SET "
					+ "salary_score = ?, welfare_score = ?, stability_score = ?, culture_score = ?, growth_score = ?, "
					+ "linereview = ?, good = ?, bad = ? " + "WHERE cp_rv_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setDouble(1, rdto.getSalary_score());
			pstat.setDouble(2, rdto.getWelfare_score());
			pstat.setDouble(3, rdto.getStability_score());
			pstat.setDouble(4, rdto.getCulture_score());
			pstat.setDouble(5, rdto.getGrowth_score());
			pstat.setString(6, rdto.getLinereview());
			pstat.setString(7, rdto.getGood());
			pstat.setString(8, rdto.getBad());
			pstat.setString(9, rdto.getCp_rv_seq());

			pstat.executeUpdate();
		} catch (Exception e) {
			System.out.println("ReviewDAO.updateReviewByCpRvSeq");
			e.printStackTrace();
		}
	}

	/**
	 * 내가 작성한 리뷰 조회 메서드
	 * 
	 * @param map 조회 조건을 담은 HashMap (id, begin, end)
	 * @return 리뷰 목록
	 */
	public ArrayList<ReviewDTO> myReview(HashMap<String, String> map) {
		try {
			String sql = String.format(
					"select * from (select a.*, rownum as rnum from (select * from vwmyreview where id = '%s' order by cp_rv_regdate desc) a) where rnum between %s and %s",
					map.get("id"), map.get("begin"), map.get("end"));

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			ArrayList<ReviewDTO> list = new ArrayList<>();
			while (rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setCp_seq(rs.getString("cp_seq"));
				dto.setCp_name(rs.getString("cp_name"));
				dto.setId(rs.getString("id"));
				dto.setRnum(rs.getString("rnum"));
				dto.setCp_rv_seq(rs.getString("cp_rv_seq"));
				dto.setLinereview(rs.getString("linereview"));
				dto.setCp_rv_regdate(rs.getString("cp_rv_regdate"));
				dto.setCp_rv_confirm(rs.getInt("cp_rv_confirm"));

				list.add(dto);
			}
			return list;

		} catch (Exception e) {
			System.out.println("ReviewDAO.myReview");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 사용자가 작성한 리뷰 개수를 조회하는 메서드
	 * 
	 * @param id 사용자 아이디
	 * @return 리뷰 개수
	 */
	public int getCount(String id) {
		try {
			String sql = "select count(*) as cnt from vwmyreview where id = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);

			rs = pstat.executeQuery();

			if (rs.next()) {
				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			System.out.println("ReviewDAO.getCount");
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 승인 대기 중인 면접후기 개수를 조회하는 메서드
	 * 
	 * @return 면접후기 개수
	 */
	public int getPendingInterviewCount() {

		try {

			String sql = "select count(*) as cnt from tblInterview where itv_confirm = 0";

			stat = conn.createStatement();
			rs= stat.executeQuery(sql);

			if(rs.next()) {
				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			System.out.println("(관리자) 면접후기 승인 대기 수 로드 실패");
			e.printStackTrace();
		}

		return 0;


	}

	/**
	 * 승인 대기 중인 리뷰 개수를 조회하는 메서드
	 * 
	 * @return 리뷰 개수
	 */
	public int getPendingReviewsCount() {

		try {

			String sql = "select count(*) as cnt from tblCompanyReview where cp_rv_confirm = 0 ";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			if (rs.next()) {
				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			System.out.println("(관리자) 승인대기 리뷰수 로드 실패");
			e.printStackTrace();
		}

		return 0;

	}

	/**
	 * 모든 리뷰 목록을 출력하는 메서드
	 * 
	 * @param startIndex 시작 인덱스
	 * @param endIndex 끝 인덱스
	 * @return 리뷰 목록
	 */
	public ArrayList<ReviewDTO> getAllReviews(int startIndex, int endIndex) {
		
		ArrayList<ReviewDTO> list = new ArrayList<>();
		
		try {

			String sql = "SELECT * FROM (SELECT ROWNUM AS rnum, t.* FROM (SELECT * FROM vwAllReview where CP_RV_CONFIRM = 0 ORDER BY CP_RV_REGDATE) t) WHERE rnum BETWEEN ? AND ?";

			pstat = conn.prepareStatement(sql);
			int parameterIndex = 1;
			
			pstat.setInt(parameterIndex++, startIndex + 1);
			pstat.setInt(parameterIndex, endIndex);

			rs = pstat.executeQuery();

			while (rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setCp_rv_seq(rs.getString("cp_rv_seq"));
				dto.setId(rs.getString("id"));
				dto.setSalary_score(rs.getDouble("salary_score"));
				dto.setWelfare_score(rs.getDouble("welfare_score"));
				dto.setStability_score(rs.getDouble("stability_score"));
				dto.setCulture_score(rs.getDouble("culture_score"));
				dto.setGrowth_score(rs.getDouble("growth_score"));
				dto.setLinereview(rs.getString("linereview"));
				dto.setGood(rs.getString("good"));
				dto.setBad(rs.getString("bad"));
				dto.setCp_rv_regdate(rs.getString("cp_rv_regdate"));
				dto.setCp_rv_confirm(rs.getInt("cp_rv_confirm"));
				dto.setCp_name(rs.getString("cp_name"));

				list.add(dto);
			}
			

		}catch (Exception e) {
			System.out.println("ReviewDAO.getAllReviews");
			e.printStackTrace();
		}

		return list;
		
	}
	
	/**
	 * 모든 리뷰 목록을 출력하는 메서드
	 * 
	 * @return 리뷰 목록
	 */
	public ArrayList<ReviewDTO> getAllReviews() {
		
		ArrayList<ReviewDTO> list = new ArrayList<>();
		
		try {
			
			String sql = "select * from vwAllReview where CP_RV_CONFIRM = 0";
			
			pstat = conn.prepareStatement(sql);
			
			
			rs = pstat.executeQuery();
			
			while (rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setCp_rv_seq(rs.getString("cp_rv_seq"));
				dto.setId(rs.getString("id"));
				dto.setSalary_score(rs.getDouble("salary_score"));
				dto.setWelfare_score(rs.getDouble("welfare_score"));
				dto.setStability_score(rs.getDouble("stability_score"));
				dto.setCulture_score(rs.getDouble("culture_score"));
				dto.setGrowth_score(rs.getDouble("growth_score"));
				dto.setLinereview(rs.getString("linereview"));
				dto.setGood(rs.getString("good"));
				dto.setBad(rs.getString("bad"));
				dto.setCp_rv_regdate(rs.getString("cp_rv_regdate"));
				dto.setCp_rv_confirm(rs.getInt("cp_rv_confirm"));
				dto.setCp_name(rs.getString("cp_name"));
				
				list.add(dto);
			}
			
			
		}catch (Exception e) {
			System.out.println("ReviewDAO.getAllReviews");
			e.printStackTrace();
		}
		
		return list;
		
	}
	/**
	 * 반려된 리뷰를 추가하는 메서드
	 * 
	 * @param cp_rv_seq 리뷰 시퀀스
	 * @param rejectReason 반려 사유
	 * @return 추가 성공 여부
	 */
	public boolean insertRejectReview(String cp_rv_seq, String rejectReason) {
		try {
			String sql = "INSERT INTO tblRejectReview (cp_rv_seq, rj_reason) VALUES (?, ?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, cp_rv_seq);
			pstat.setString(2, rejectReason);
			int result = pstat.executeUpdate();
			return result > 0;
		} catch (Exception e) {
			System.out.println("ReviewDAO.insertRejectReview()");
			e.printStackTrace();
		} 
		
		return false;
	}
	
	/**
	 * 회사 태그 목록을 조회하는 메서드
	 * 
	 * @return 태그 목록
	 */
	public ArrayList<String> showComTagList() {
		try {
			ArrayList<String> showTagList = new ArrayList<>();
			String sql = "SELECT tag_keyword FROM tblTag";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			while (rs.next()) {
				showTagList.add(rs.getString("tag_keyword"));
			}

			return showTagList;

		} catch (Exception e) {
			System.out.println("ReviewDAO.showTagList");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 마지막으로 추가된 리뷰의 시퀀스를 조회하는 메서드
	 * 
	 * @return 리뷰 시퀀스
	 */
	public String getLastInsertedCpRvSeq() {
		try {
			String sql = "SELECT cp_rv_seq FROM tblCompanyReview WHERE ROWNUM = 1 ORDER BY cp_rv_seq DESC";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("cp_rv_seq");
			}
		} catch (Exception e) {
			System.out.println("ReviewDAO.getLastInsertedCpRvSeq");
			e.printStackTrace();
		}
		return null;
	}


}

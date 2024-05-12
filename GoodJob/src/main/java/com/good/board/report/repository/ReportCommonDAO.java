package com.good.board.report.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

public class ReportCommonDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public ReportCommonDAO() {
		this.conn = DBUtil.open();
	}

	public void close() {
		try {
			this.conn.close();
		} catch (Exception e) {
			System.out.println("ReportCommonDAO.close 오류");
			e.printStackTrace();
		}
	}

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

	public ArrayList<ReportCommonDTO> getAllReportList(Integer rp_seq, int startIndex, int endIndex){

		ArrayList<ReportCommonDTO> list = new ArrayList<>();
	    try {
	        StringBuilder sql = new StringBuilder("SELECT * FROM (SELECT ROWNUM AS rnum, t.* FROM (SELECT * FROM vwAllReportList");
	        if (rp_seq != null) {
	            sql.append(" WHERE rp_seq = ?");
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

				dto.setType(rs.getString("type"));
				dto.setSub_type(parseType(rs.getString("sub_type")));
				dto.setSeq(rs.getString("seq"));
				dto.setTitle(parseTitle(rs.getString("title")));
				dto.setRegdate(rs.getString("regdate"));
				dto.setWriter_id(rs.getString("writer_id"));
				dto.setReporter_id(rs.getString("reporter_id"));
				dto.setReport_detail(rs.getString("report_detail"));
				dto.setReport_regdate(rs.getString("report_regdate"));
				dto.setReport_count(rs.getString("report_count"));
				dto.setRp_seq(parseReportType(rs.getString("rp_seq")));

				list.add(dto);

			}

		} catch (Exception e) {
			System.out.println("ReportCommonDAO.getAllReportList");
			e.printStackTrace();
		}

		return list;

	}
	
//	1	비방/욕설
//	2	허위사실
//	3	개인정보노출
//	4	음란성
//	5	게시글 도배
//	6	부적절한 홍보
//	7	기타
	
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
	
	private String parseType(String type) {
		
		String result = "";
		
		switch (type) {
		case "qna":
			
			result = "질문 게시판(여기어때)";
			
			break;

		case "study":
				
			result = "스터디 게시판";
			
			break;
		}
		
		return result;
		
	}
	
	private String parseTitle(String title) {
		
		
		String result = title;
		if (result.length() > 20) {
			result = result.substring(0, 20) + "..";
		}
		
		return result;
		
	}

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
			System.out.println("ReportCommonDAO.getRecentReportTitleList");
			e.printStackTrace();
		}

		return list;
	}

	public void blockuser(HashMap<String,String> blockStatus, String status, String blockDate, String releaseDate,
			String blockReason) {
		
		try {
			
			StringBuilder sql = new StringBuilder();
			List<String> insertIds = new ArrayList<>();
			HashMap<String, String> updateIds = new HashMap<>();
			
			for(Map.Entry<String, String> entry: blockStatus.entrySet()) {
				
				String id = entry.getKey();
	            String oldReleaseDate = entry.getValue();
				
	            if (oldReleaseDate != null) {
	                updateIds.put(id, oldReleaseDate);
	            } else {
	                insertIds.add(id);
	                sql.append("INSERT INTO tblBanLog (ban_seq, ban_reason, ban_startdate, ban_enddate, id) VALUES (seqBanLog.nextVal, ?, ?, ?, ?);");
	            }
				
			}
			
			if (!insertIds.isEmpty()) {
	            pstat = conn.prepareStatement(sql.toString());
	            int paramIndex = 1;
	            for (int i = 0; i < insertIds.size(); i++) {
	                pstat.setString(paramIndex++, blockReason);
	                pstat.setString(paramIndex++, blockDate);
	                pstat.setString(paramIndex++, releaseDate);
	                pstat.setString(paramIndex++, insertIds.get(i));
	            }
	            pstat.executeBatch();
	        }
			
			updateBlockPeriod(updateIds, status, blockDate, releaseDate, blockReason);
			
		} catch (Exception e) {
			System.out.println("ReportCommonDAO.blockuser");
			e.printStackTrace();
		}
	}
	
	
	
	

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
	                updatePstat.setString(2, blockDate);
	                updatePstat.setString(3, dateFormat.format(newReleaseDate));
	                updatePstat.setString(4, id);
	                updatePstat.addBatch();
	            }
	        }

	        updatePstat.executeBatch();
	    } catch (Exception e) {
	        System.out.println("ReportCommonDAO.updateBlockPeriod");
	        e.printStackTrace();
	    }
	}
	
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
			System.out.println("ReportCommonDAO.checkBlock");
			e.printStackTrace();
		}
		
		return blockStatus;
		
	}
	
	
	





}

package com.good.board.report.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

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






}

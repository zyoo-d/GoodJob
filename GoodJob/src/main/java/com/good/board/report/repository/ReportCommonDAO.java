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
	
	public int getTotalReportCount() {
		
		try {
			
			String sql = "select count(*) as cnt from vwAllReports";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
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
	
	public ArrayList<ReportCommonDTO> getAllReportList(){
		
		ArrayList<ReportCommonDTO> list = new ArrayList<>();
		
		try {	
				
				String sql = "select * from vwAllReportList";
				
				stat = conn.createStatement();
				rs = stat.executeQuery(sql);
				
				
				while(rs.next()) {
					
					ReportCommonDTO dto = new ReportCommonDTO();
					
					dto.setType(rs.getString("type"));
					dto.setSub_type(rs.getString("sub_type"));
					dto.setSeq(rs.getString("seq"));
					dto.setTitle(rs.getString("title"));
					dto.setRegdate(rs.getString("regdate"));
					dto.setWriter_id(rs.getString("writer_id"));
					dto.setReporter_id(rs.getString("reporter_id"));
					dto.setReport_detail(rs.getString("report_detail"));
					dto.setReport_regdate(rs.getString("report_regdate"));
					dto.setReport_count(rs.getString("report_count"));
					
					list.add(dto);
					
				}
				
			} catch (Exception e) {
				System.out.println("ReportCommonDAO.getAllReportList");
				e.printStackTrace();
			}
			
		return list;
		
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

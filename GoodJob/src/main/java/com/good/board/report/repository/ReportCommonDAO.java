package com.good.board.report.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import com.test.util.DBUtil;

public class ReportCommonDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public ReportCommonDAO() {
		this.conn = DBUtil.open();
	}
	
	public int getTotalReportCount() {
		
		try {
			
			String sql = "select count(*) as cnt from vwAllReport";
			
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
			
			String sql = "select count(*) as cnt from vwAllReport where id = ?";
			
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
	

}

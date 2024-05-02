package com.good.admin.visitor.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import com.test.util.DBUtil;

public class VisitorDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public VisitorDAO() {
		this.conn = DBUtil.open();
	}
			
	public int getVisitorsForDate(LocalDate date) {
		    
		try {
			
			String sql = "select visit_count from tblVisitor where visit_date = ?";
			
			LocalDate nowDate = LocalDate.now();
			
			pstat = conn.prepareStatement(sql);
			pstat.setDate(1, Date.valueOf(nowDate));
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				return rs.getInt("visit_count");
				
			}
			
		} catch (Exception e) {
			System.out.println("VisitorDAO.getVisitorsForDate");
			e.printStackTrace();
		}   
		return 0;
			
	}		
		    
	
	
	
	

}

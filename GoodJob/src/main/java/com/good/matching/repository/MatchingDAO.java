package com.good.matching.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.good.matching.model.MatchingDTO;
import com.test.util.DBUtil;

public class MatchingDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	private CallableStatement cstmt;
	
	public MatchingDAO() {
		this.conn = DBUtil.open();
	}
	
	public void close() {
		try {
			
			this.conn.close();
			
		} catch (Exception e) {
			System.out.println("MatchingDAO.conn close 실패");
			e.printStackTrace();
		}
	}

	public HashMap<Integer, String> getScore(String id) {
		
		try {	
				
				String sql = "select * from vwUserSurvey where id = ?";
				
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, "id");
				
				rs = pstat.executeQuery();
				
				HashMap<Integer, String> map = new HashMap<>();
				
				while(rs.next()) {
					
					map.put(rs.getInt("WELFARE"),"WELFARE");
					map.put(rs.getInt("STABILITY"),"STABILITY");
					map.put(rs.getInt("CLUTURE"),"CLUTURE");
					map.put(rs.getInt("POTENTIAL"),"POTENTIAL");
					
				}
				
				return map;
				
			} catch (Exception e) {
				System.out.println("MatchingDAO.getScore");
				e.printStackTrace();
			}
		
		return null;
		
	}

	public ArrayList<MatchingDTO> getMatching(String[] columnNames, String id) {
		
		try {	
				
				String sql = "begin procMatching(?,?,?,?,?,?) end;";
				
				cstmt = conn.prepareCall(sql);
				cstmt.setString(1, "id");
				cstmt.setString(2, columnNames[0]);
				cstmt.setString(3, columnNames[1]);
				cstmt.setString(4, columnNames[2]);
				cstmt.setString(5, columnNames[3]);
				
				rs = (ResultSet) cstmt.getObject(6);
				
				ArrayList<MatchingDTO> list = new ArrayList<>();
				
				while(rs.next()) {
					
					MatchingDTO dto = new MatchingDTO();
					
					dto.setCp_seq(rs.getString("cp_seq"));
					dto.setCulture(rs.getString("culture"));
					dto.setPotential(rs.getString("potential"));
					dto.setStability(rs.getString("stability"));
					dto.setWelfare(rs.getString("welfare"));
					
					list.add(dto);
					
				}
				
				return list;
				
				
			} catch (Exception e) {
				System.out.println("MatchingDAO.getMatching");
				e.printStackTrace();
			}
		
		
		
		
		return null;
		
	}
	
	
	
	

}

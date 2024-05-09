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

import oracle.jdbc.OracleTypes;


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
				pstat.setString(1, id);
				
				rs = pstat.executeQuery();
				
				HashMap<Integer, String> map = new HashMap<>();
				
				while(rs.next()) {
					
					map.put(rs.getInt("WELFARE"),"WELFARE");
					map.put(rs.getInt("STABILITY"),"STABILITY");
					map.put(rs.getInt("CULTURE"),"CULTURE");
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

            String sql = "{call procMatching(?,?,?,?,?)}";

            System.out.println(id);
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, id);
            cstmt.setString(2, "culture");
            cstmt.setString(3, "stability");
            cstmt.setString(4, "potential");
            cstmt.setString(5, "welfare");
            cstmt.execute();
            cstmt.close();
            
            sql="select * from tempResults";
            stat = conn.createStatement();
            
            rs = stat.executeQuery(sql);
            System.out.println(rs==null);
            
            ArrayList<MatchingDTO> list = new ArrayList<>();

            System.out.println(111);
            
            while(rs.next()) {
                System.out.println(222);
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

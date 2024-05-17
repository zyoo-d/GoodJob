package com.good.company.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.good.company.model.WelFareDTO;
import com.test.util.DBUtil;

/**
 * WelFareDAO 클래스는 데이터베이스에서 복지 관련 데이터를 조회하고 관리하는 기능을 제공합니다.
 */
public class WelFareDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	/**
     * 기본 생성자입니다. 데이터베이스 연결을 수행합니다.
     */
	public WelFareDAO() {
		this.conn = DBUtil.open();
	}

	/**
     * 데이터베이스 연결을 닫습니다.
     */
	public void close() {

		try {
			this.conn.close();
		} catch (Exception e) {
			System.out.println("WelFareDAO.close 오류");
			e.printStackTrace();
		}
	}
	
    /**
     * 특정 회사의 복지 정보를 조회합니다.
     *
     * @param cp_name 회사 이름
     * @return 복지 정보 리스트
     */
	public ArrayList<WelFareDTO> companyWelfare(String cp_name) {
	    try {
	        String sql = "select * from vwCompanyWelfare where cp_seq = ?";
	        pstat = conn.prepareStatement(sql);
	        pstat.setString(1, cp_name);
	        rs = pstat.executeQuery();

	        ArrayList<WelFareDTO> list = new ArrayList<WelFareDTO>();

	        while (rs.next()) {
	            WelFareDTO dto = new WelFareDTO();
	            dto.setCp_seq(rs.getString("cp_seq"));
	            dto.setWel_seq(rs.getString("wel_seq"));
	            dto.setWel_name(rs.getString("wel_name"));
	            dto.setWeltype_seq(rs.getString("weltype_seq"));
	            dto.setWeltype_name(rs.getString("weltype_name"));
	            
	            list.add(dto);
	        }
	        //System.out.println(list);
	        return list;

	    } catch (Exception e) {
	        System.out.println("WelFareDAO.companyWelfare");
	        e.printStackTrace();
	    }

	    return null;
	}
}

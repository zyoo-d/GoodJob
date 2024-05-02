package com.good.company.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.good.company.model.CompanyDTO;
import com.test.util.DBUtil;

public class CompanyDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public CompanyDAO() {
		this.conn = DBUtil.open();
	}
	
	public ArrayList<CompanyDTO> rcrtCompany() {
		
		try {

			String sql = "select cp_seq, cp_name, image from tblCompany";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			ArrayList<CompanyDTO> listCompany = new ArrayList<CompanyDTO>();
			while (rs.next()) {

				CompanyDTO dto = new CompanyDTO();
				
				dto.setCp_seq(rs.getString("cp_seq"));
				dto.setCp_name(rs.getString("cp_name"));
				dto.setCp_address(rs.getString("cp_address"));
				
				listCompany.add(dto);
							
			}	
			return listCompany;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}

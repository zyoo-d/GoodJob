package com.goodjob.test.heeyeon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.test.util.DBUtil;

import lombok.Data;

public class HeeTestDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public HeeTestDAO() {
		this.conn = DBUtil.open();
	}

	public ArrayList<HeeCompany> list() {
		try {
			String sql = "select cp_seq, cp_name from tblcompany";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			ArrayList<HeeCompany> list = new ArrayList<>();
			while (rs.next()) {
				HeeCompany com = new HeeCompany();
				com.setSeq(rs.getString("cp_seq"));
				com.setName(rs.getString("cp_name"));
				list.add(com);
			}
			return list;

		} catch (Exception e) {
			System.out.println("TestDAO.list");
			e.printStackTrace();
		}
		return null;
	}

	public int addCode(HeeCompany company) {
		try {
			String sql = "insert into tblCode values (?,?,?)";

			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, company.getSeq());
			pstat.setString(2, company.getCode());
			pstat.setString(3, company.getName());
			
			pstat.executeUpdate();
			pstat.close();
			return 1;
			
		} catch (Exception e) {
			System.out.println("TestDAO.addCode");
			e.printStackTrace();
		}
		return 0;
	}

	public ArrayList<HeeCompany> comList() {
		try {
			String sql = "select distinct seq, LPAD(code, 8, '0') as code, name from tblcode where seq < 2103";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			ArrayList<HeeCompany> list = new ArrayList<>();
			while (rs.next()) {
				HeeCompany com = new HeeCompany();
				com.setSeq(rs.getString("seq"));
				com.setCode(rs.getString("code"));
				com.setName(rs.getString("name"));
				list.add(com);
			}
			return list;

		} catch (Exception e) {
			System.out.println("TestDAO.comList");
			e.printStackTrace();
		}
		return null;
	}

	public int updateInfo(HeeCompany com) {
		try {
			String sql = "update tblCompany set ceo = ?, founded = ? where cp_seq = ?";

			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, com.getCeo_nm());
			pstat.setString(2, com.getEst_dt());
			pstat.setString(3, com.getSeq());
			
			pstat.executeUpdate();
			pstat.close();
			return 1;
			
		} catch (Exception e) {
			System.out.println("TestDAO.addCode");
			e.printStackTrace();
		}
		return 0;
	}

	public int addFinance(HeeFinance finance) {
		try {
			String sql = "insert into tblFinance(fnc_seq, fnc_sales, fnc_ebit, fnc_income, fnc_period, fnc_regdate, cp_seq) values (seqFinance.nextVal, ?,?,?,?, default, ?)";

			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, finance.getFnc_sales());
			pstat.setString(2, finance.getFnc_ebit());
			pstat.setString(3, finance.getFnc_income());
			pstat.setString(4, finance.getFnc_period());
			pstat.setString(5, finance.getSeq());
			
			pstat.executeUpdate();
			pstat.close();
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, finance.getP_sales());
			pstat.setString(2, finance.getP_ebit());
			pstat.setString(3, finance.getP_income());
			pstat.setString(4, finance.getP_period());
			pstat.setString(5, finance.getSeq());
			
			pstat.executeUpdate();
			pstat.close();
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, finance.getOl_sales());
			pstat.setString(2, finance.getOl_ebit());
			pstat.setString(3, finance.getOl_income());
			pstat.setString(4, finance.getOl_period());
			pstat.setString(5, finance.getSeq());
			
			pstat.executeUpdate();
			pstat.close();
			return 1;
			
		} catch (Exception e) {
			System.out.println("TestDAO.addCode");
			e.printStackTrace();
		}
		return 0;
	}

	public int updateHire(HashMap<String, Integer> map) {
		try {
			String sql = "update tblHire set hire_avr_year = ? where cp_seq = ?";

			pstat = conn.prepareStatement(sql);
			
			pstat.setInt(1, map.get("month"));
			pstat.setInt(2, map.get("seq"));
			
			pstat.executeUpdate();
			pstat.close();
			return 1;
			
		} catch (Exception e) {
			System.out.println("TestDAO.addCode");
			e.printStackTrace();
		}
		return 0;
	}
	
	
}
package com.good.matching.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.good.matching.model.WishDTO;
import com.test.util.DBUtil;

public class WishDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public WishDAO() {
		this.conn = DBUtil.open();
	}
	
	public int addWishs(WishDTO dto) {
		try {
			String sql = "insert into tblUserDetail values (?,?,?,?)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getEdu_seq());
			pstat.setString(3, dto.getSalary_seq());
			pstat.setString(4, dto.getCareer());

			pstat.executeUpdate();
			pstat.close();
			
			ArrayList<String> temp = dto.getLc_code();
			for (String lc : temp) {
				sql = "insert into tblwishlocation values (?,?)";
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, lc);
				pstat.setString(2, dto.getId());
				pstat.executeUpdate();
				pstat.close();
			}
			
			temp.clear();
			temp = dto.getWel_seq();
			for (String wel : temp) {
				sql = "insert into tblwishwelfare values (?,?)";
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, dto.getId());
				pstat.setString(2, wel);
				pstat.executeUpdate();
				pstat.close();
			}
			
			return 1;

		} catch (Exception e) {
			System.out.println("WishDAO.addWish");
			e.printStackTrace();
		}
		return 0;
	}

	public WishDTO getWish(String id) {
		try {
			String sql = "select * from tbluserdetail where id = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			rs = pstat.executeQuery();

			WishDTO dto = new WishDTO();
			dto.setId(id);
			if (rs.next()) {
				dto.setEdu_seq(rs.getString("edu_seq"));
				dto.setSalary_seq(rs.getString("salary_seq"));
				dto.setCareer(rs.getString("career"));
			}
			rs.close();
			pstat.close();
			
			sql = "select * from tblwishwelfare where id = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			rs = pstat.executeQuery();
			ArrayList<String> wel = new ArrayList<>();
			while (rs.next()) {
				wel.add(rs.getString("wel_seq"));
			}
			dto.setWel_seq(wel);
			rs.close();
			pstat.close();
			
			sql = "select * from tblwishlocation where id = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			rs = pstat.executeQuery();
			ArrayList<String> lc = new ArrayList<>();
			while (rs.next()) {
				lc.add(rs.getString("lc_code"));
			}
			dto.setLc_code(lc);
			rs.close();
			pstat.close();

			return dto;
		} catch (Exception e) {
			System.out.println("WishDAO.getWish");
			e.printStackTrace();
		}
		return null;
	}

}

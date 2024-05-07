package com.good.matching.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.good.matching.model.PreferDTO;
import com.test.util.DBUtil;

public class PreferDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public PreferDAO() {
		this.conn = DBUtil.open();
	}
	public void close()  {
        try {
            this.conn.close();
        } catch (Exception e) {
            System.out.println("PreferDAO.close 오류");
            e.printStackTrace();
        }
    }
	public int addPrefer(PreferDTO dto) {
		try {
			String sql = "insert into tbluserprefer values (?,?,?,?,?,?,sysdate)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getId());
			pstat.setInt(2, dto.getWelfare());
			pstat.setInt(3, dto.getSalary());
			pstat.setInt(4, dto.getStability());
			pstat.setInt(5, dto.getCulture());
			pstat.setInt(6, dto.getPotential());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("PreferDAO.addPrefer");
			e.printStackTrace();
		}
		return 0;
	}
	public void delPrefer(String id) {
		try {
			String sql = "delete from tbluserprefer where id = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			pstat.executeUpdate();
			pstat.close();

		} catch (Exception e) {
			System.out.println("PreferDAO.delPrefer");
			e.printStackTrace();
		}
		
	}
}

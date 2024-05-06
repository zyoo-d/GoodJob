package com.good.user.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.good.user.model.UserDTO;
import com.test.util.DBUtil;

public class UserDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public UserDAO() {
		this.conn = DBUtil.open();
	}

	public int checkId(String id) {
		try {

			String sql = "select count(*) as cnt from tblUser where id = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);

			rs = pstat.executeQuery();

			if (rs.next()) {
				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			System.out.println("checkId.doPost");
			e.printStackTrace();
		}
		return -1;

	}
	public int checkNick(String nickname) {
		try {
			
			String sql = "select count(*) as cnt from tblUser where nickname = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, nickname);
			
			rs = pstat.executeQuery();
			
			
			if (rs.next()) {
				return rs.getInt("cnt");
			}
			
		} catch (Exception e) {
			System.out.println("checkId.doPost");
			e.printStackTrace();
		}
		return -1;
		
	}

	public int signUp(UserDTO dto) {
		try {
			String sql = "INSERT INTO tblUser (id, pw, name, tel, address, email, nickname) VALUES (?, ?, ?, ?, ?, ?, ?)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getPw());
			pstat.setString(3, dto.getName());
			pstat.setString(4, dto.getTel());
			pstat.setString(5, dto.getAddress());
			pstat.setString(6, dto.getEmail() + "@" + dto.getDomain());
			pstat.setString(7, dto.getNickname());

			System.out.println(sql); // Optional: for debugging purposes
			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("UserDAO.signUp");
			e.printStackTrace();
		}
		return 0;
	}

	public UserDTO login(UserDTO dto) {
		// queryParamDTOReturn
		try {

			String sql = "select * from tblUser where id = ? and pw = ? and (lv = 1 or lv = 2)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getPw());

			rs = pstat.executeQuery();
			UserDTO result = new UserDTO();

			if (rs.next()) {
				result.setName(rs.getString("name"));
				result.setLv(rs.getString("lv"));
			}
			rs.close();
			pstat.close();
			
			sql = "SELECT (SELECT COUNT(*) FROM tbluserdetail WHERE id = ?) AS wish, (SELECT COUNT(*) FROM tbluserprefer WHERE id = ?) AS prefer from dual";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getId());
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				result.setWish(rs.getString("wish"));
				result.setPrefer(rs.getString("prefer"));
			}
			rs.close();
			pstat.close();
			
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}

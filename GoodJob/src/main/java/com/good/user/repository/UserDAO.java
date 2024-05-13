package com.good.user.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

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
	
	public void close()  {

        try {
            this.conn.close();
        } catch (Exception e) {
            System.out.println("UserDAO.close 오류");
            e.printStackTrace();
        }
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

	public String getId(UserDTO dto) {
		try {
			String sql = "select id from tblUser where name=? and tel=?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getTel());

			rs = pstat.executeQuery();

			if (rs.next()) {
				return rs.getString("id");
			}

		} catch (Exception e) {
			System.out.println("UserDAO.getId");
			e.printStackTrace();
		}
		return null;
	}

	public int changePw(UserDTO dto) {
		try {
			String sql = "UPDATE tblUser SET PW = ? WHERE ID = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getPw());
			pstat.setString(2, dto.getId());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("UserDAO.changePw");
			e.printStackTrace();
		}
		
		return 0;
	}

	public int getPw(UserDTO dto) {
		try {
			String sql = "select count(*) as cnt from tblUser where id = ? and tel = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getTel());

			rs = pstat.executeQuery();

			if (rs.next()) {
				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			System.out.println("UserDAO.getPw");
			e.printStackTrace();
		}
		return 0;
	}

	public int edit(UserDTO dto) {
		
		try {
			String sql = "update tblUser set pw = ?, email = ?, address = ? where id = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getPw());
			pstat.setString(2, dto.getEmail());
			pstat.setString(3, dto.getAddress());
			pstat.setString(4, dto.getId());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("UserDAO.edit");
			e.printStackTrace();
		}
		
		return 0;
	}

	public UserDTO userInfo(String id) {
		try {
			String sql = "select * from tblUser where id = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);

			rs = pstat.executeQuery();

			if (rs.next()) {
				UserDTO dto = new UserDTO();
				dto.setEmail(rs.getString("email"));
				dto.setAddress(rs.getString("address"));

				return dto;
			}

		} catch (Exception e) {
			System.out.println("UserDAO.userInfo");
			e.printStackTrace();
		}
		return null;
	}

	public int unregister(String id) {
		
		try {
			String sql = "update tblUser set lv = 4 where id = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("UserDAO.unregister");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public String getName(String id) {
		
		try {

			String sql = "select name from tblUser where id = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);

			rs = pstat.executeQuery();

			if (rs.next()) {
				return rs.getString("name");
			}

		} catch (Exception e) {
			System.out.println("유저 이름 로드 실패");
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public int getNewSubscriberCount() {
		
		try {
			
			LocalDate currentDate = LocalDate.now();

			String sql = "select count(*) as cnt from tblUser where regdate = ?";
			

			pstat = conn.prepareStatement(sql);
			pstat.setDate(1, Date.valueOf(currentDate));
			
			rs = pstat.executeQuery();

			if (rs.next()) {
				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			System.out.println("일일 신규 가입자수 로드 실패");
			e.printStackTrace();
		}
		
		return 0;
		
		
	}
	
	



}

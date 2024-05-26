package com.good.user.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import com.good.user.model.UserDTO;
import com.test.util.DBUtil;
/**
 * UserDAO 클래스는 사용자와 관련된 데이터베이스 작업을 처리합니다.
 */
public class UserDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
    /**
     * 데이터베이스 연결을 초기화하는 생성자입니다.
     */
	public UserDAO() {
		this.conn = DBUtil.open();
	}

    /**
     * 데이터베이스 연결을 닫습니다.
     */
	public void close()  {

        try {
            this.conn.close();
        } catch (Exception e) {
            System.out.println("QnaBoardDAO.close 오류");
            e.printStackTrace();
        }
    }

    /**
     * 주어진 사용자 ID가 데이터베이스에 이미 존재하는지 확인합니다.
     * 
     * @param id 확인할 사용자 ID입니다.
     * @return 데이터베이스에서 찾은 사용자 ID의 수입니다.
     */
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
	
	/**
     * 주어진 별명이 데이터베이스에 이미 존재하는지 확인합니다.
     * 
     * @param nickname 확인할 별명입니다.
     * @return 데이터베이스에서 찾은 별명의 수입니다.
     */
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

	   /**
     * 사용자 정보를 데이터베이스에 등록합니다.
     * 
     * @param dto 등록할 사용자의 정보가 담긴 UserDTO 객체입니다.
     * @return 데이터베이스에 등록된 행의 수입니다.
     */
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

	  /**
     * 사용자의 로그인을 처리하고 인증 정보를 반환합니다.
     * 
     * @param dto 로그인할 사용자의 정보가 담긴 UserDTO 객체입니다.
     * @return 로그인에 성공한 경우 인증된 사용자 정보를 포함한 UserDTO 객체를 반환합니다. 그렇지 않으면 null을 반환합니다.
     */
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

	   /**
     * 사용자의 이름과 전화번호로 ID를 조회합니다.
     * 
     * @param dto 사용자의 이름과 전화번호가 담긴 UserDTO 객체입니다.
     * @return 조회된 사용자의 ID입니다.
     */
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

    /**
     * 사용자의 비밀번호를 변경합니다.
     * 
     * @param dto 비밀번호를 변경할 사용자의 정보가 담긴 UserDTO 객체입니다.
     * @return 비밀번호 변경에 성공한 경우 변경된 행의 수를 반환합니다. 그렇지 않으면 0을 반환합니다.
     */
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

    /**
     * 사용자의 비밀번호를 확인합니다.
     * 
     * @param dto 비밀번호를 확인할 사용자의 정보가 담긴 UserDTO 객체입니다.
     * @return 비밀번호가 일치하는 경우 1을, 일치하지 않는 경우 0을 반환합니다.
     */
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

    /**
     * 사용자 정보를 수정합니다.
     * 
     * @param dto 수정할 사용자의 정보가 담긴 UserDTO 객체입니다.
     * @return 정보 수정에 성공한 경우 변경된 행의 수를 반환합니다. 그렇지 않으면 0을 반환합니다.
     */
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

    /**
     * 사용자의 정보를 조회합니다.
     * 
     * @param id 조회할 사용자의 ID입니다.
     * @return 조회된 사용자의 정보가 담긴 UserDTO 객체를 반환합니다. 사용자가 존재하지 않으면 null을 반환합니다.
     */
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
	
    /**
     * 사용자를 탈퇴 처리합니다.
     * 
     * @param id 탈퇴할 사용자의 ID입니다.
     * @return 탈퇴 처리에 성공한 경우 변경된 행의 수를 반환합니다. 그렇지 않으면 0을 반환합니다.
     */
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

    /**
     * 여러 사용자를 탈퇴 처리합니다.
     * 
     * @param ids 탈퇴할 사용자들의 ID 배열입니다.
     * @return 탈퇴 처리에 성공한 경우 변경된 행의 수를 반환합니다. 그렇지 않으면 0을 반환합니다.
     */
	public int unregister(String[] ids) {
	    try {
	        StringBuilder sql = new StringBuilder("update tblUser set lv = 4 where id in (");
	        for (int i = 0; i < ids.length; i++) {
	            sql.append("?");
	            if (i < ids.length - 1) {
	                sql.append(",");
	            }
	        }
	        sql.append(")");

	        pstat = conn.prepareStatement(sql.toString());
	        for (int i = 0; i < ids.length; i++) {
	            pstat.setString(i + 1, ids[i]);
	        }

	        return pstat.executeUpdate();
	    } catch (Exception e) {
	        System.out.println("UserDAO.unregister");
	        e.printStackTrace();
	    }
	    return 0;
	}
	
    /**
     * 사용자의 이름을 조회합니다.
     * 
     * @param id 조회할 사용자의 ID입니다.
     * @return 조회된 사용자의 이름입니다.
     */
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
	
	/**
     * 오늘 등록된 신규 사용자 수를 조회합니다.
     * 
     * @return 오늘 등록된 신규 사용자 수입니다.
     */
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

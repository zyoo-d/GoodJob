package com.good.matching.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.good.matching.model.WishDTO;
import com.test.util.DBUtil;
/**
 * WishDAO 클래스는 사용자의 위시리스트 관련 데이터를 데이터베이스에 저장하고 관리하는 기능을 수행합니다.
 * 위시리스트 데이터의 추가, 수정, 조회 등을 관리합니다.
 */
public class WishDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	/**
     * 데이터베이스 연결을 초기화합니다.
     */
	public WishDAO() {
		this.conn = DBUtil.open();
	}
	/**
     * 데이터베이스 연결을 종료합니다.
     */
	public void close()  {
        try {
            this.conn.close();
        } catch (Exception e) {
            System.out.println("WishDAO.close 오류");
            e.printStackTrace();
        }
    }
	/**
     * 사용자의 위시리스트를 데이터베이스에 추가합니다.
     *
     * @param dto 위시리스트 정보를 담고 있는 WishDTO 객체
     * @return 작업 성공 여부를 나타내는 정수 (성공: 1, 실패: 0)
     */
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
	/**
     * 사용자의 위시리스트를 조회합니다.
     *
     * @param id 사용자 id
     * @return 사용자의 위시리스트 정보 WishDTO
     */
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
	/**
     * 사용자의 위시리스트를 수정합니다.
     *
     * @param dto 위시리스트 정보를 담고 있는 WishDTO 객체
     * @return 작업 성공 여부를 나타내는 정수 (성공: 1, 실패: 0)
     */
	public int editWish(WishDTO dto) {
		try {
			String sql = "update tblUserDetail set edu_seq = ?, salary_seq = ?, career = ? where id = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getEdu_seq());
			pstat.setString(2, dto.getSalary_seq());
			pstat.setString(3, dto.getCareer());
			pstat.setString(4, dto.getId());

			pstat.executeUpdate();
			pstat.close();
			
			sql = "delete tblwishlocation where id = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getId());
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
			
			sql = "delete tblwishwelfare where id = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getId());
			pstat.executeUpdate();
			pstat.close();
			
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
			System.out.println("WishDAO.editWish");
			e.printStackTrace();
		}
		return 0;
	}

}

package com.good.matching.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.good.matching.model.PreferDTO;
import com.test.util.DBUtil;
/**
 * PreferDAO 클래스는 사용자의 선호도 정보를 데이터베이스에 추가, 삭제하는 기능을 제공합니다.
 * 이 클래스는 DBUtil을 통해 데이터베이스 연결을 관리하며, 선호도 정보의 CRUD 작업을 수행합니다.
 */
public class PreferDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	/**
     * 데이터베이스 연결을 초기화합니다.
     */
	public PreferDAO() {
		this.conn = DBUtil.open();
	}
	/**
     * 데이터베이스 연결을 종료합니다.
     */
	public void close()  {
        try {
            this.conn.close();
        } catch (Exception e) {
            System.out.println("PreferDAO.close 오류");
            e.printStackTrace();
        }
    }
	/**
     * 사용자의 선호도 정보를 데이터베이스에 추가합니다.
     *
     * @param dto 추가할 선호도 정보가 담긴 PreferDTO 객체
     * @return 데이터베이스 작업 성공 여부를 나타내는 정수 (성공: 1, 실패: 0)
     */
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
	/**
     * 특정 사용자의 선호도 정보를 데이터베이스에서 삭제합니다.
     *
     * @param id 삭제할 사용자의 ID
     */
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

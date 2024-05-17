package com.good.matching.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.good.matching.model.MatchingDTO;
import com.test.util.DBUtil;

/**
 * MatchingDAO 클래스는 사용자와 회사 간의 매칭 점수를 계산하고 관련 데이터를 관리하는 역할을 합니다. 이 클래스는 데이터베이스
 * 연결을 통해 매칭 관련 데이터를 조회하고, 매칭 점수를 계산하는 메서드를 제공합니다.
 */
public class MatchingDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	private CallableStatement cstmt;

	/**
	 * 데이터베이스 연결을 초기화합니다.
	 */
	public MatchingDAO() {
		this.conn = DBUtil.open();
	}

	/**
	 * 데이터베이스 연결을 종료합니다.
	 */
	public void close() {
		try {
			this.conn.close();
		} catch (Exception e) {
			System.out.println("MatchingDAO.close 오류");
			e.printStackTrace();
		}
	}

	/**
	 * 사용자 ID에 따라 사용자의 설문 결과를 조회하여 각 카테고리의 점수를 HashMap으로 반환합니다.
	 * 
	 * @param id 사용자 ID
	 * @return HashMap&lt;String, Integer&gt; 각 카테고리와 해당 점수를 포함하는 HashMap
	 */
	public HashMap<String, Integer> getColumn(String id) {

		try {
			String sql = "select * from vwUserSurvey where id = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, id);

			rs = pstat.executeQuery();

			HashMap<String, Integer> map = new HashMap<>();

			if (rs.next()) {

				map.put("welfare", rs.getInt("welfare"));
				map.put("stability", rs.getInt("stability"));
				map.put("culture", rs.getInt("culture"));
				map.put("potential", rs.getInt("potential"));

			}

			return map;

		} catch (Exception e) {
			System.out.println("매칭 컬럼 점수 가져오기 실패");
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * 매칭 절차를 실행하고, 결과로 나온 매칭 데이터를 ArrayList로 반환합니다.
	 * 
	 * @param columnNames 사용할 컬럼 이름 배열
	 * @param id          사용자 ID
	 * @return ArrayList&lt;MatchingDTO&gt; 매칭 결과 리스트
	 */
	public ArrayList<MatchingDTO> getMatching(String[] columnNames, String id) {
		ArrayList<MatchingDTO> list = new ArrayList<>();

		try {
			String sql = "BEGIN procMatching(?, ?, ?, ?, ?); END;";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, id);

			for (int i = 0; i < columnNames.length; i++) {
				pstat.setString(i + 2, columnNames[i]);
			}
			pstat.execute();

			sql = "select * from tempResults";
			stat = conn.createStatement();

			rs = stat.executeQuery(sql);

			if (rs != null) {
				while (rs.next()) {

					MatchingDTO dto = new MatchingDTO();
					dto.setCp_seq(rs.getString("cp_seq"));
					dto.setCulture(rs.getInt("culture"));
					dto.setPotential(rs.getInt("potential"));
					dto.setStability(rs.getInt("stability"));
					dto.setWelfare(rs.getInt("welfare"));
					dto.setSalary(rs.getInt("salary"));
					list.add(dto);
				}
			}

		} catch (Exception e) {
			System.out.println("MatchingDAO.getMatching - Exception");
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 특정 사용자 ID에 대한 점수 정보를 가져오는 메서드입니다.
	 * 
	 * @param id 사용자 ID
	 * @return MatchingDTO 사용자의 점수 정보를 담은 DTO
	 */
	public MatchingDTO getScore(String id) {

		try {

			String sql = "select * from vwUserSurvey where id = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, id);

			rs = pstat.executeQuery();

			MatchingDTO dto = new MatchingDTO();

			if (rs.next()) {

				dto.setWelfare((int) Math.round(rs.getDouble("welfare")));
				dto.setCulture((int) Math.round(rs.getDouble("culture")));
				dto.setPotential((int) Math.round(rs.getDouble("potential")));
				dto.setStability((int) Math.round(rs.getDouble("stability")));
				dto.setSalary((int) Math.round(rs.getDouble("salary")));

			}

			return dto;

		} catch (Exception e) {
			System.out.println("MatchingDAO.getScore");
			e.printStackTrace();
		}

		return null;

	}

}

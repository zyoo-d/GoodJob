package com.good.matching.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.good.matching.model.SurveyDTO;
import com.test.util.DBUtil;
/**
 * SurveyDAO 클래스는 설문조사 데이터에 접근하여 데이터베이스 작업을 수행합니다.
 * 이 클래스는 설문 데이터를 조회, 수정하는 기능을 제공합니다.
 */
public class SurveyDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	/**
     * 데이터베이스 연결을 초기화합니다.
     */
	public SurveyDAO() {
		this.conn = DBUtil.open();
	}
	/**
     * 데이터베이스 연결을 종료합니다.
     */
	public void close()  {
        try {
            this.conn.close();
        } catch (Exception e) {
            System.out.println("SurveyDAO.close 오류");
            e.printStackTrace();
        }
    }
	/**
     * 모든 설문조사 데이터를 리스트 형태로 반환합니다.
     * 
     * @return 설문조사 데이터 리스트
     */
	public ArrayList<SurveyDTO> listSurvey() {
		try {
			String sql = "SELECT ROWNUM as rnum, t.* FROM (SELECT * FROM tblsurvey ORDER BY DBMS_RANDOM.VALUE) t";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			ArrayList<SurveyDTO> list = new ArrayList<>();
			while (rs.next()) {
				SurveyDTO dto = new SurveyDTO();
				
				dto.setRnum(rs.getString("rnum"));
				dto.setSv_seq(rs.getString("sv_seq"));
				dto.setSv_content(rs.getString("sv_content"));
				dto.setSv_category(rs.getString("sv_category"));
				dto.setCompare(rs.getString("compare"));

				list.add(dto);
			}
			return list;

		} catch (Exception e) {
			System.out.println("SurveyDAO.listSurvey");
			e.printStackTrace();
		}
		return null;
	}
	/**
     * 특정 카테고리의 설문조사 데이터를 리스트 형태로 반환합니다.
     * 
     * @param category 조회할 설문조사의 카테고리
     * @return 해당 카테고리의 설문조사 데이터 리스트
     */
	public ArrayList<SurveyDTO> listSurvey(String category) {
		try {
			String sql = "SELECT * FROM tblsurvey where sv_category = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, category);
			rs = pstat.executeQuery();

			ArrayList<SurveyDTO> list = new ArrayList<>();
			while (rs.next()) {
				SurveyDTO dto = new SurveyDTO();
				
				dto.setSv_seq(rs.getString("sv_seq"));
				dto.setSv_content(rs.getString("sv_content"));
				dto.setSv_category(rs.getString("sv_category"));
				dto.setCompare(rs.getString("compare"));

				list.add(dto);
			}
			pstat.close();
			return list;

		} catch (Exception e) {
			System.out.println("SurveyDAO.listSurvey");
			e.printStackTrace();
		}
		return null;
	}
	/**
     * 설문조사 질문을 수정합니다.
     * 
     * @param dto 수정할 내용이 담긴 SurveyDTO 객체
     * @return 수정 작업 성공 여부를 나타내는 정수 (성공: 1, 실패: 0)
     */
	public int editQuestion(SurveyDTO dto) {
		try {
			String sql = "update tblsurvey set sv_content = ? where sv_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getSv_content());
			pstat.setString(2, dto.getSv_seq());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("SurveyDAO.editSurvey");
			e.printStackTrace();
		}
		return 0;
	}
}

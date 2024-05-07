package com.good.matching.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.good.matching.model.SurveyDTO;
import com.test.util.DBUtil;

public class SurveyDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public SurveyDAO() {
		this.conn = DBUtil.open();
	}

	public void close()  {
        try {
            this.conn.close();
        } catch (Exception e) {
            System.out.println("SurveyDAO.close 오류");
            e.printStackTrace();
        }
    }
	
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
}

package com.good.board.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.good.board.model.StudyDTO;
import com.test.util.DBUtil;

public class StudyDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public StudyDAO() {
		this.conn = DBUtil.open();
	}

	public int addStudy(StudyDTO dto) {
		try {
			String sql = "INSERT INTO tblstudy (STD_SEQ, STD_TITLE, STD_CONTENT, STD_ING, STD_REGDATE, CP_SEQ, ID, STD_DUEDATE) VALUES (seqStudy.NEXTVAL, ?, ?, 'N', SYSDATE, ?, ?, TO_DATE(?, 'YYYY-MM-DD'))";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getStd_title());
			pstat.setString(2, dto.getStd_content());
			pstat.setString(3, dto.getCp_seq());
			pstat.setString(4, dto.getId());
			pstat.setString(5, dto.getStd_duedate());
			
			int result = pstat.executeUpdate();
			pstat.close();
			return result;

		} catch (Exception e) {
			System.out.println("StudyDAO.addStudy");
			e.printStackTrace();
		}
		return 0;
	}
	
	
}

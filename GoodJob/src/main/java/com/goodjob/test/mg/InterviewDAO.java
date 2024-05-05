package com.goodjob.test.mg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.goodjob.test.heeyeon.HeeCompany;
import com.test.util.DBUtil;

public class InterviewDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public InterviewDAO() {
		this.conn = DBUtil.open();
	}

	public ArrayList<InterviewDTO> list() {
		try {

			String sql = "select * from tblInterview";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<InterviewDTO> list = new ArrayList<>();
			while (rs.next()) {

				InterviewDTO dto = new InterviewDTO();

				   
			    dto.setITV_SEQ(rs.getString("ITV_SEQ"));
			    dto.setITV_CPNAME(rs.getString("ITV_CPNAME")); // 회사 이름 세팅
			    dto.setITV_MEETDATE(rs.getString("ITV_MEETDATE")); // 면접 날짜 세팅
			    dto.setITV_EVALUATION(rs.getString("ITV_EVALUATION")); // 면접 평가 세팅
			    dto.setITV_REGDATE(rs.getString("ITV_REGDATE")); // 작성일자 세팅
			    dto.setCP_SEQ(rs.getString("CP_SEQ")); // 회사 번호 세팅
			    dto.setID(rs.getString("ID")); // 작성자 id 세팅
			    dto.setITV_DIFFICULTY(rs.getString("ITV_DIFFICULTY")); // 난이도 세팅
			    dto.setITV_CATEGORY(rs.getString("ITV_CATEGORY")); // 카테고리 세팅
			    dto.setITV_CAREER(rs.getString("ITV_CAREER")); // 커리어 세팅
			    dto.setITV_PERSONNEL(rs.getString("ITV_PERSONNEL")); // 면접 인원 세팅
			    dto.setITV_QUESTION(rs.getString("ITV_QUESTION")); // 면접 질문 세팅
			    dto.setITV_TIP(rs.getString("ITV_TIP")); // 면접 팁 세팅
			    dto.setITV_WHETHER(rs.getString("ITV_WHETHER")); // 면접 합격 여부 세팅
				
				list.add(dto);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}

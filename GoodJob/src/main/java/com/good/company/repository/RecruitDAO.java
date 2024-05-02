package com.good.company.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.ArrayList;

import com.good.matching.model.RecruitDTO;
import com.test.util.DBUtil;

public class RecruitDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public RecruitDAO() {
		this.conn = DBUtil.open();
	}

	public String getNextSeq() {
		try {
			String sql = "select seqRecruit.nextVal as seq from dual";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("seq");
			}

		} catch (Exception e) {
			System.out.println("RecruitDAO.getNextSeq");
			e.printStackTrace();
		}
		return null;
	}

	public int addRecruit(RecruitDTO dto) {
		//queryParamNoReturn
				try {

					String sql = "insert into tblRecruit values (seqRecruit.nextVal, ?, ?, ?, ?, ?, ?)";

					pstat = conn.prepareStatement(sql);
					pstat.setString(1, dto.getRcrt_address());
					pstat.setString(2, dto.getRcrt_career());
					pstat.setString(3, dto.getRcrt_salary());
					pstat.setString(4, dto.getStartdate());
					pstat.setString(5, dto.getEnddate());
					pstat.setString(6, dto.getCp_seq());

					return pstat.executeUpdate();

				} catch (Exception e) {
					System.out.println("addRecruit");
					e.printStackTrace();
				}
				
				return 0;
			}
	public ArrayList<RecruitDTO> RecruitList() {
		try {
			String sql = "select * from tblRecruit";
			
			ArrayList<RecruitDTO> list = new ArrayList<>();
			
			while (rs.next()) {
				RecruitDTO dto = new RecruitDTO();
				dto.setRcrt_seq(rs.getString("rcrt_seq"));

				list.add(dto);
			}
			return list;

		} catch (Exception e) {
			System.out.println("RecruitList");
			e.printStackTrace();
		}
		return null;
	}

	public int addJobRcrt(HashMap<String, String> map) {
		try {
			String sql = "insert into tblJobRcrt (rcrt_seq, job_seq) values (?,?)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, map.get("seq"));		
			pstat.setString(2, map.get("code"));		

			pstat.executeUpdate();
			pstat.close();
			return 1;

		} catch (Exception e) {
			System.out.println("RecruitDAO.addJobRcrt");
			e.printStackTrace();
		}
		return 0;
	}

	public int addRecruit(RecruitDTO dto) {
		try {
			String sql = "insert into tblRecruit (RCRT_SEQ, RCRT_NAME, RCRT_LINK, STARTDATE,ENDDATE,MIN_CAREER,MAX_CAREER,EDU_SEQ, SALARY_SEQ, CP_SEQ) values (?,?,?,?,?,?,?,?,?,?)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getRcrt_seq());		
			pstat.setString(2, dto.getRcrt_name());
			pstat.setString(3, dto.getRcrt_link());
			pstat.setString(4, dto.getStartdate());
			pstat.setString(5, dto.getEnddate());
			pstat.setString(6, dto.getMin_career());
			pstat.setString(7, dto.getMax_career());
			pstat.setString(8, dto.getEdu_seq());
			pstat.setString(9, dto.getSalary_seq());
			pstat.setString(10, dto.getCp_seq());
			
			pstat.executeUpdate();
			pstat.close();
			return 1;

		} catch (Exception e) {
			System.out.println("RecruitDAO.addJobRcrt");
			e.printStackTrace();
		}
		return 0;
	}
}

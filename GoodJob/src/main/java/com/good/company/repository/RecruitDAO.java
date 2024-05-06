package com.good.company.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.ArrayList;

import com.good.company.model.RecruitDTO;
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

	/*
	 * public int addRecruit(RecruitDTO dto) { //queryParamNoReturn try {
	 * 
	 * String sql =
	 * "insert into tblRecruit values (seqRecruit.nextVal, ?, ?, ?, ?, ?, ?)";
	 * 
	 * pstat = conn.prepareStatement(sql); pstat.setString(1,
	 * dto.getRcrt_address()); pstat.setString(2, dto.getRcrt_career());
	 * pstat.setString(3, dto.getRcrt_salary()); pstat.setString(4,
	 * dto.getStartdate()); pstat.setString(5, dto.getEnddate()); pstat.setString(6,
	 * dto.getCp_seq());
	 * 
	 * return pstat.executeUpdate();
	 * 
	 * } catch (Exception e) { System.out.println("addRecruit");
	 * e.printStackTrace(); }
	 * 
	 * return 0; }
	 */
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
	
	public int countComRcrt(String cp_seq) {
		
		try {

			LocalDate currentDate = LocalDate.now();
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    String formatCurrentDate = currentDate.format(formatter);
			
		    String sql = "select count(*) as cnt from tblRecruit where cp_seq = ? and enddate >= ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, cp_seq);
			pstat.setString(2, formatCurrentDate);
			
			rs = pstat.executeQuery();

			if (rs.next()) {
				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			System.out.println("등록된 기업수 로드 실패");
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	
	
	
	
	/**
	 * 기업별 채용공고 목록 조회 메서드
	 * @param cp_seq
	 * @return
	 */


	public ArrayList<RecruitDTO> comRecruitList(String cp_seq) {
	    try {
	    	
	        LocalDate currentDate = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        String formatCurrentDate = currentDate.format(formatter);

	        String sql = "SELECT * FROM vwComRcrt WHERE cp_seq=? AND enddate >= ?";
	        pstat = conn.prepareStatement(sql);
	        pstat.setString(1, cp_seq);
	        pstat.setString(2, formatCurrentDate);

	        rs = pstat.executeQuery();
	        
	        ArrayList<RecruitDTO> comRecruitList = new ArrayList<>();

	        while (rs.next()) {

	            RecruitDTO dto = new RecruitDTO();
	            dto.setRcrt_seq(rs.getString("rcrt_seq"));
	            dto.setCp_seq(rs.getString("cp_seq"));
	            dto.setEdu_seq(rs.getString("edu_seq"));
	            dto.setMax_career(rs.getString("max_career"));
	            dto.setMin_career(rs.getString("min_career"));
	            dto.setRcrt_name(rs.getString("rcrt_name"));
	            dto.setStartdate(rs.getString("startdate"));
	            dto.setEnddate(rs.getString("enddate"));
	            dto.setSalary_seq(rs.getString("salary_seq"));
	            dto.setRcrt_link(rs.getString("rcrt_link"));
	            dto.setSalary_type(rs.getString("salary_type"));
	            dto.setEdu_type(rs.getString("edu_type"));
	            dto.setCp_address(rs.getString("cp_address"));
	            comRecruitList.add(dto);
	        }
	        return comRecruitList;

	    } catch (Exception e) {
	        System.out.println("RecruitDAO.comRecruitList");
	        e.printStackTrace();
	    }
	    return null;
	}
}

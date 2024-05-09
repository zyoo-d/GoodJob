package com.good.board.report.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.good.board.report.model.StdCmReportDTO;
import com.test.util.DBUtil;

public class StdCmReportDAO implements ReportDAO<StdCmReportDTO>{
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public StdCmReportDAO() {
		this.conn = DBUtil.open();
	}
	
	public void close() {
		try {
			this.conn.close();
		} catch (Exception e) {
			System.out.println("StdCmReportDAO.close 실패");
			e.printStackTrace();
		}
	}

	@Override
	public int create(StdCmReportDTO dto) {
		
		try {
			
			String sql = "insert into tblStdCmReport(std_cm_rp_seq, std_cm_rp_regdate, id, rp_seq, std_cm_seq, std_cm_rp_detail) values(seqStdCmReport.nextVal, sysdate, ?,?,?,?)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getRp_seq());
			pstat.setString(3, dto.getStd_cm_seq());
			pstat.setString(4, dto.getStd_cm_rp_detail());
			
			return pstat.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("스터디 댓글 신고 등록 실패");
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public ArrayList<StdCmReportDTO> list() {
		
		
		return null;
	}

	@Override
	public void delete(String seq) {
	}

	@Override
	public void view(String seq) {
	}

	@Override
	public int totalCount() {
		
		try {
			
			String sql = "select count(*) as cnt from tblStdCmReport";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				
				return rs.getInt("cnt");
				
			}
			
		} catch (Exception e) {
			System.out.println("tblStdCmReport.totalCount");
			e.printStackTrace();
		}
		
		
		return 0;
	}

	@Override
	public int isReport(String id, String seq) {
		
		try {
			
			String sql = "select count(*) as cnt from tblStdCmReport where std_cm_seq = ? and id = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			pstat.setString(2, id);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				return rs.getInt("cnt");
				
			}
			
		} catch (Exception e) {
			System.out.println("tblStdCmReport.isReport");
			e.printStackTrace();
		}
		
		
		return 0;
	}

	@Override
	public int getReportCountByDate(LocalDate date) {
		
		try {
			
			String sql = "select count(*) as cnt from tblStdCmReport where TRUNC(std_cm_rp_regdate) = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setDate(1, Date.valueOf(date));
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("cnt");
			}
					
			
		} catch (Exception e) {
			System.out.println("tblStdCmReport.getReportCountByDate");
			e.printStackTrace();
		}
		return 0;
	}



}

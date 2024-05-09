package com.good.board.report.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.good.board.report.model.StdReportDTO;
import com.test.util.DBUtil;

public class StdReportDAO implements ReportDAO<StdReportDTO> {

	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public StdReportDAO() {
		this.conn = DBUtil.open();
	}
	
	public void close() {
		try {
			this.conn.close();
		} catch (Exception e) {
			System.out.println("StdReportDAO.close 실패");
			e.printStackTrace();
		}
	}
	
	@Override
	public int create(StdReportDTO dto) {
		
		try {
			
			String sql = "insert into tblStdReport(std_rp_seq, std_rp_regdate, id, std_seq, rp_seq, std_rp_detail)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getStd_seq());
			pstat.setString(3, dto.getRp_seq());
			pstat.setString(4, dto.getStd_rp_detail());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("스터디 게시글 신고 생성 실패");
			e.printStackTrace();
		}
		return 0;
		
	}

	@Override
	public ArrayList<StdReportDTO> list() {
		
		
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
			
			String sql = "select count(*) as cnt from tblStdReport";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				
				return rs.getInt("cnt");
				
			}
			
		} catch (Exception e) {
			System.out.println("tblStdReport.totalCount");
			e.printStackTrace();
		}
		
		
		return 0;
	}

	@Override
	public int isReport(String id, String seq) {
		
		try {
			
			String sql = "select count(*) as cnt from tblStdReport where std_seq = ? and id = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			pstat.setString(2, id);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				return rs.getInt("cnt");
				
			}
			
		} catch (Exception e) {
			System.out.println("tblStdReport.isReport");
			e.printStackTrace();
		}
		
		
		return 0;
	}

	@Override
	public int getReportCountByDate(LocalDate date) {
		
		try {
			
			String sql = "select count(*) as cnt from tblStdReport where TRUNC(std_rp_regdate) = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setDate(1, Date.valueOf(date));
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("cnt");
			}
					
			
		} catch (Exception e) {
			System.out.println("tblStdReport.getReportCountByDate");
			e.printStackTrace();
		}
		return 0;
	}

	

	

}

package com.good.board.report.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.good.board.report.model.LiveCmReportDTO;
import com.test.util.DBUtil;

public class LiveCmReportDAO implements ReportDAO<LiveCmReportDTO> {
	
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public LiveCmReportDAO() {
		this.conn = DBUtil.open();
	}
	
	public void close() {
		try {
			this.conn.close();
		} catch (Exception e) {
			System.out.println("LiveCmReportDAO.close 실패");
			e.printStackTrace();
		}
	}
	@Override
	public int create(LiveCmReportDTO dto) {
		
		try {
			
			String sql = "insert into tblLiveCmReport(live_cm_rp_seq, live_cm_rp_regdate, id, rp_seq, live_seq, live_cm_rp_detail) values(seqLiveCmReport, sysdate, ?,?,?,?)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getId() );
			pstat.setString(2, dto.getRp_seq());
			pstat.setString(3, dto.getLive_seq());
			pstat.setString(4, dto.getLive_cm_rp_detail());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("실시간 댓글 신고 등록 실패");
			e.printStackTrace();
		}
		
		
		return 0;
	}

	@Override
	public ArrayList<LiveCmReportDTO> list() {
		
		
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
			
			String sql = "select count(*) as cnt from tblLiveCmReport";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				
				return rs.getInt("cnt");
				
			}
			
		} catch (Exception e) {
			System.out.println("tblLiveCmReport.totalCount");
			e.printStackTrace();
		}
		
		
		return 0;
	}

	@Override
	public int isReport(String id, String seq) {
		
		try {
			
			String sql = "select count(*) as cnt from tblLiveCmReport where live_seq = ? and id = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			pstat.setString(2, id);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				return rs.getInt("cnt");
				
			}
			
		} catch (Exception e) {
			System.out.println("tblLiveCmReport.isReport");
			e.printStackTrace();
		}
		
		
		return 0;
	}

	@Override
	public int getReportCountByDate(LocalDate date) {
		
		try {
			
			String sql = "select count(*) as cnt from tblLiveCmReport where TRUNC(live_cm_rp_regdate) = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setDate(1, Date.valueOf(date));
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("cnt");
			}
					
			
		} catch (Exception e) {
			System.out.println("tblLiveCmReport.getReportCountByDate");
			e.printStackTrace();
		}
		return 0;
	}


	



}

package com.good.board.report.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import com.good.board.report.model.QnaReportDTO;
import com.test.util.DBUtil;

public class QnaReportDAO implements ReportDAO<QnaReportDTO> {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public QnaReportDAO() {
		this.conn = DBUtil.open();
	}
	
	public void close() {
		try {
			this.conn.close();
		} catch (Exception e) {
			System.out.println("QnaReportDAO.close 실패");
			e.printStackTrace();
		}
	}

	@Override
	public int create(QnaReportDTO dto) {
		
		try {
			
			String sql = "insert into tblQnAReport (qna_rp_seq, qna_rp_regdate, qna_seq, rp_seq, id, qna_rp_detail) values(seqQnAReport.nextVal,sysdate,?,?,?,?)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getQna_seq());
			pstat.setString(2, dto.getRp_seq());
			pstat.setString(3, dto.getId());
			pstat.setString(4, dto.getQna_rp_detail());
			
			return pstat.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("질문게시판 신고 등록 실패");
			e.printStackTrace();
		}
		
		return 0;
		
	}

	@Override
	public ArrayList<QnaReportDTO> list() {
		
		
		return null;
	}

	@Override
	public void delete(String seq) {
	}

	@Override
	public void view(String seq) {
		
		try {
			
			String sql = "select * from tblQnAReport q inner join tblReportType r on q.rp_seq = r.rp_seq where qna_rp_seq = ? ";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				QnaReportDTO dto = new QnaReportDTO.Builder()
						.withQna_Rp_Seq(rs.getString("qna_rp_seq"))
						.withQna_Rp_Regdate(rs.getString("qna_rp_regdate"))
						.withQna_Seq(rs.getString("qna_seq"))
						.withRp_Seq(rs.getString("rp_seq"))
						.build(); //여기부터 계속
				
				
				
			}
			
			
		} catch (Exception e) {
			System.out.println("QnaReportDAO.view");
			e.printStackTrace();
		}
		
		
	}

	@Override
	public int totalCount() {
		
		try {
			
			String sql = "select count(*) as cnt from tblQnAReport";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if(rs.next()) {
				
				return rs.getInt("cnt");
				
			}
			
		} catch (Exception e) {
			System.out.println("QnaReportDAO.totalCount");
			e.printStackTrace();
		}
		
		
		return 0;
	}

	@Override
	public boolean isReported(String id, String seq) {
		
		try {
			
			String sql = "select count(*) as cnt from tblQnAReport where qna_seq = ? and id = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			pstat.setString(2, id);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				return rs.getInt("cnt") > 0;
				
			}
			
		} catch (Exception e) {
			System.out.println("QnaReportDAO.isReport");
			e.printStackTrace();
		}
		
		return false;
		
	}

	@Override
	public int getReportCountByDate(LocalDate date) {
		
		try {
			
			String sql = "select count(*) as cnt from tblQnAReport where TRUNC(qna_rp_regdate) = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setDate(1, Date.valueOf(date));
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("cnt");
			}
					
			
		} catch (Exception e) {
			System.out.println("QnaReportDAO.getReportCountByDate");
			e.printStackTrace();
		}
		return 0;
	}

	


}

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

/**
 * 스터디 게시글 신고 정보를 다루는 DAO 클래스입니다.
 * ReportDAO 인터페이스를 구현하여 CRUD 기능을 제공합니다.
 */
public class StdReportDAO implements ReportDAO<StdReportDTO> {

	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	 /**
     * StdReportDAO 생성자입니다.
     * 데이터베이스 연결을 설정합니다.
     */
	public StdReportDAO() {
		this.conn = DBUtil.open();
	}
	/**
     * 데이터베이스 연결을 닫습니다.
     */
	public void close() {
		try {
			this.conn.close();
		} catch (Exception e) {
			System.out.println("StdReportDAO.close 실패");
			e.printStackTrace();
		}
	}
	
	 /**
     * 새로운 스터디 게시글 신고를 생성합니다.
     *
     * @param dto 생성할 스터디 게시글 신고 정보
     * @return 생성된 스터디 게시글 신고의 수
     */
	@Override
	public int create(StdReportDTO dto) {
		
		try {
			
			String sql = "insert into tblStdReport(std_rp_seq, std_rp_regdate, id, std_seq, rp_seq, std_rp_detail) values(seqStdReport.nextVal,sysdate,?,?,?,?)";
			
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



	  /**
     * 전체 스터디 게시글 신고 수를 가져옵니다.
     *
     * @return 전체 스터디 게시글 신고 수
     */
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

	 /**
     * 특정 사용자가 특정 스터디 게시글을 신고했는지 확인합니다.
     *
     * @param id  사용자 ID
     * @param seq 스터디 게시글 번호
     * @return 사용자가 해당 스터디 게시글을 신고했다면 true, 그렇지 않으면 false
     */
	@Override
	public boolean isReported(String id, String seq) {
		
		try {
			
			String sql = "select count(*) as cnt from tblStdReport where std_seq = ? and id = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			pstat.setString(2, id);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				return rs.getInt("cnt") > 0;
				
			}
			
		} catch (Exception e) {
			System.out.println("tblStdReport.isReport");
			e.printStackTrace();
		}
		
		
		return false;
	}

	 /**
     * 특정 날짜의 스터디 게시글 신고 수를 가져옵니다.
     *
     * @param date 조회할 날짜
     * @return 해당 날짜의 스터디 게시글 신고 수
     */
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

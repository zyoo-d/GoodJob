package com.good.board.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.test.util.DBUtil;

public class QnaBoardDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public QnaBoardDAO() {
		this.conn = DBUtil.open();
	}
	
	public int listCount() {
		
		try {
			
			String sql = "select count(*) as cnt from tblQna";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			return rs.getInt("cnt");
			
			
			
		} catch (Exception e) {
			System.out.println("질문게시판 글 개수 로드 실패");
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	public void create() {
		
	}
	
	public List<QnaBoardDTO> qnaList(){
		
		try {
			
			String sql = "select * from tblQna";
			
			
			
		} catch (Exception e) {
			System.out.println("QnaBoardDAO.qnaList");
			e.printStackTrace();
		}
		
		
	}
	
	
}

package com.good.board.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.good.board.model.StdCommentDTO;
import com.test.util.DBUtil;

public class StdCommentDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public StdCommentDAO() {
		this.conn = DBUtil.open();
	}
	public void addComment(StdCommentDTO dto) {
	    try {
	        // SQL 문장 작성
	        String sql = "INSERT INTO tblStdComment (STD_CM_SEQ, STD_CM_CONTENT, STD_CM_REGDATE, STD_SEQ, STD_CM_BSEQ, ID) VALUES (std_cm_seq.nextval, ?, SYSDATE, ?, NULL, ?)";
	        
	        // PreparedStatement 생성 및 값 설정
	        pstat = conn.prepareStatement(sql);
	        pstat.setString(1, dto.getSTD_CM_CONTENT());
	        pstat.setString(2, dto.getSTD_SEQ());
	        pstat.setString(3, dto.getID());
	        
	        // SQL 실행
	        pstat.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        // 리소스 해제
	        try {
	            if (pstat != null) pstat.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
	
}
	
	
   
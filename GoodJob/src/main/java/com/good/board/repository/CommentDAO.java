package com.good.board.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.good.board.model.CommentDTO;
import com.test.util.DBUtil;

public class CommentDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public CommentDAO() {
		this.conn = DBUtil.open();
	}
	public int addComment(CommentDTO dto) {
	    try {
	        // SQL 문장 작성
	        String sql = "INSERT INTO tblStdComment (STD_CM_SEQ, STD_CM_CONTENT, STD_CM_REGDATE, STD_SEQ, STD_CM_BSEQ, ID) VALUES (SEQSTDCOMMENT.nextval, ?, SYSDATE, ?, NULL, ?)";
	        
	        // PreparedStatement 생성 및 값 설정
	        pstat = conn.prepareStatement(sql);
	        pstat.setString(1, dto.getSTD_CM_CONTENT());
	        pstat.setString(2, dto.getSTD_SEQ());
	        pstat.setString(3, dto.getID());
	        
	        return pstat.executeUpdate();
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    } 
	    return 0;
	}
	public ArrayList<CommentDTO> getComments(String stdSeq) {
	    ArrayList<CommentDTO> comments = new ArrayList<>();
	    try {
	        String sql = "SELECT * FROM vwstdcomment WHERE STD_SEQ = ?";
	        pstat = conn.prepareStatement(sql);
	        pstat.setString(1, stdSeq);
	        rs = pstat.executeQuery();

	        while (rs.next()) {
	            CommentDTO dto = new CommentDTO();
	            dto.setSTD_CM_SEQ(rs.getString("STD_CM_SEQ"));
	            dto.setSTD_CM_CONTENT(rs.getString("STD_CM_CONTENT"));
	            dto.setSTD_CM_REGDATE(rs.getString("STD_CM_REGDATE"));
	            dto.setSTD_SEQ(rs.getString("STD_SEQ"));
	            dto.setSTD_CM_BSEQ(rs.getString("STD_CM_BSEQ"));
	            dto.setID(rs.getString("ID"));
	            dto.setNICKNAME(rs.getString("NICKNAME"));

	            comments.add(dto);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return comments;
	}
	
	
}	

	
	
   
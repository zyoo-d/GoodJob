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
	        pstat.setString(1, dto.getContent());
	        pstat.setString(2, dto.getBoard_seq());
	        pstat.setString(3, dto.getId());
	        
	    
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
	            dto.setCm_seq(rs.getString("cm_seq"));
	            dto.setContent(rs.getString("content"));
	            dto.setRegdate(rs.getString("regdate"));
	            dto.setBoard_seq(rs.getString("board_seq"));
	            dto.setCm_bseq(rs.getString("cm_bseq"));
	            dto.setId(rs.getString("id"));
	            dto.setNickname(rs.getString("nickname"));
	            
	            comments.add(dto);	        
	        }
	        return comments;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	  
	}


	public CommentDTO getComment(String sTD_SEQ) {

			try {
				
				String sql = "SELECT * FROM vwstdComment WHERE STD_SEQ = ? AND STD_CM_SEQ = (SELECT MAX(STD_CM_SEQ) FROM vwstdComment WHERE STD_SEQ = ?)";
				pstat = conn.prepareStatement(sql);
				pstat.setString(1, sTD_SEQ);
				pstat.setString(2, sTD_SEQ);
						
				rs = pstat.executeQuery();
				
				
				if (rs.next()) {
					
					CommentDTO dto = new CommentDTO();
					
					dto.setCm_seq(rs.getString("cm_seq"));
					dto.setContent(rs.getString("content"));
					dto.setRegdate(rs.getString("regdate"));
					dto.setCm_bseq(rs.getString("cm_bseq"));
					dto.setId(rs.getString("id"));
			        dto.setNickname(rs.getString("nickname"));
					
					return dto;				
				}	
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return null;
		}
	
	
	}
	
	
	
	

	
	
   
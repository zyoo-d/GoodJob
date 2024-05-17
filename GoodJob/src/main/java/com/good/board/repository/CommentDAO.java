package com.good.board.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.good.board.model.CommentDTO;
import com.test.util.DBUtil;
/*
  CommentDAO 클래스

  게시판 댓글 관련 데이터를 데이터베이스에서 CRUD(Create, Read, Update, Delete) 작업을 수행하는 DAO(Data Access Object) 클래스입니다.
  댓글 작성, 댓글 목록 조회, 최신 댓글 조회 등의 기능을 제공합니다.

 */
public class CommentDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public CommentDAO() {
		this.conn = DBUtil.open();
	}
	
	public void close() {
		try {
			this.conn.close();
		} catch (Exception e) {
			System.out.println("CommentDAO.close 오류");
			e.printStackTrace();
		}
	}
	/**
     * 새로운 댓글을 작성합니다.
     *
     * @param dto 작성할 댓글 정보를 담은 CommentDTO 객체
     * @return 작성 결과 (성공 시 1, 실패 시 0)
     */
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
	
	
	
	
	
	 /**
     * 지정된 게시글의 댓글 목록을 조회합니다.
     *
     * @param stdSeq 게시글 ID
     * @return 댓글 목록을 담은 ArrayList
     */
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

	 /**
     * 지정된 게시글의 최신 댓글을 조회합니다.
     *
     * @param sTD_SEQ 게시글 ID
     * @return 최신 댓글 정보를 담은 CommentDTO 객체
     */
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
	
	
	
	

	
	
   
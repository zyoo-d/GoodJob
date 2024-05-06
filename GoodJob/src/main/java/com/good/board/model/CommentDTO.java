package com.good.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import lombok.Data;

@Data
public class CommentDTO {
	
	private String STD_CM_CONTENT;  //댓글 내용
	private String STD_CM_REGDATE; // 댓글 작성일
	private String STD_SEQ;		// 글 번호
	private String STD_CM_BSEQ; // 대댓글 번호
	private String ID;			// 댓글 작성자 id > 화면에 출력은 x
	private String STD_CM_SEQ;	// 댓글 번호
	private String NICKNAME;	// 작성자 닉네임
	

}
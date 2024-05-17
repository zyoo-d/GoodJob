package com.good.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import lombok.Data;

@Data
public class CommentDTO {

	private String content; // 댓글 내용
	private String regdate; // 댓글 작성일
	private String board_seq; // 글 번호
	private String cm_bseq; // 대댓글 번호
	private String id; // 댓글 작성자 id > 화면에 출력은 x
	private String cm_seq; // 댓글 번호
	private String nickname; // 작성자 닉네임
	private String cp_seq; //기업 번호

}
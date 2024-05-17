package com.good.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import lombok.Data;
/*
 CommentDTO 클래스

 게시판 댓글에 관련된 데이터를 저장하고 관리하는 DTO(Data Transfer Object) 클래스입니다.
댓글 내용, 작성일, 게시글 번호, 대댓글 번호, 작성자 ID, 댓글 번호, 작성자 닉네임, 기업 번호 등의 정보를 포함합니다.

*/
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
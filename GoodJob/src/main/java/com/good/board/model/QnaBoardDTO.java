package com.good.board.model;


import java.sql.Timestamp;

import lombok.Data;

/**
* QnaBoardDTO 클래스는 QnA 게시판의 게시글 정보를 담고 있는 데이터 전송 객체입니다.
*/
@Data
public class QnaBoardDTO{
	
	private int qna_seq;
	private String qna_title;
	private String qna_content;
	private Timestamp qna_regdate;
	private String id;
	private int cp_seq;
	private String cp_name;
	private int qna_views;
	private int qna_comments;
	private String rnum;

}

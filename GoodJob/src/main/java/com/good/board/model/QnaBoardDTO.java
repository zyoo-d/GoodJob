package com.good.board.model;


import java.sql.Timestamp;

import lombok.Data;

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

}

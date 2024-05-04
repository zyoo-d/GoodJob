package com.good.board.model;

import java.util.Date;

import lombok.Data;

@Data
public class BoardDTO {
	
	private int seq;
	private String title;
	private String content;
	private Date regDate;
	private String id;
	
}

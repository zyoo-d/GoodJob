package com.good.board.model;

import java.util.Date;

import lombok.Data;

@Data
public class BoardCommonDTO {
	
	private String seq;
	private String title;
	private String content;
	private String regDate;
	private String id;
	private String view;
	private String board_type;
	
}

package com.good.board.model;

import lombok.Data;

@Data
public class NoticeDTO {
	private int nt_seq;
	private String nt_title;
	private String nt_content;
	private String nt_regdate;
	private String id;
	private int nt_views;
}

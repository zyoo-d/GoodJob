package com.good.board.model;

import lombok.Data;

@Data
public class NoticeDTO {
	private String nt_seq;
	private String nt_title;
	private String nt_content;
	private String nt_regdate;
	private String id;
	private String nickname;
	private int nt_views;
}

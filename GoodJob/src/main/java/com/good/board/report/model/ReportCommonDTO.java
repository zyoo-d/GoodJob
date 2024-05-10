package com.good.board.report.model;

import lombok.Data;

@Data
public class ReportCommonDTO {
	
	private String type;
	private String sub_type;
	private String seq;
	private String title;
	private String regdate;
	private String writer_id;
	private String reporter_id;
	private String report_detail;
	private String report_regdate;
	private String report_count;

}

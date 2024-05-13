package com.good.board.report.model;

import lombok.Data;

@Data
public class ReportCommonDTO {
	
	private String type;
	private String sub_type;
	private String seq;
	private String title;
	private String regdate;
	private String rp_seq;
	private String writer_id;
	private String reporter_id;
	private String report_detail;
	private String report_regdate;
	private String report_count;
	private String report_seq;
	
	private String parent_seq;

	
	//<button id="report-btn" data-boardtype="live" data-seq="${cdto.cm_seq}">[ 신고 ]</button>
}

package com.good.board.report.model;

import lombok.Data;

/**
 * 신고 공통 정보를 담는 DTO 클래스입니다.
 */
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

	
}

package com.good.company.model;

import lombok.Data;

@Data
public class RecruitDTO {
	
	private String rcrt_seq;
	private String rcrt_name;
	private String rcrt_link;
	private String min_career;
	private String max_career;
	private String startdate;
	private String enddate;
	private String edu_seq;
	private String salary_seq;
	private String cp_seq;
}

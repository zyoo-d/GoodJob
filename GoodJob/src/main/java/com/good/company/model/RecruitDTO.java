package com.good.company.model;

import java.util.ArrayList;

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
	private String cp_name;
	private String hire_avr_salary;

    	private String salary_type;
    	private String edu_type;
    
	private String cp_address;
    	private ArrayList<String> job_name;

}

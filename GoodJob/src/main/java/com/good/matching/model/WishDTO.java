package com.good.matching.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class WishDTO {
	private String id;
	private String edu_seq;
	private String salary_seq;
	private String career;
	private ArrayList<String> lc_code;
	private ArrayList<String> wel_seq;
}

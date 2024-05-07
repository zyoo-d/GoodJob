package com.good.company.model;


import java.util.ArrayList;

import lombok.Data;

@Data
public class ReviewDTO {
	 private String cp_seq;
	 private String id;
	 private String cp_rv_seq;
	 private int salary_score;
	 private int welfare_score;
	 private int lngvt_score;
	 private int culture_score;
	 private int growth_score;
	 private String linereview;
	 private String good;
	 private String bad;
	 private String cp_rv_regdate;
	 private int cp_rv_confirm;
	    
	 private String tag_seq;
	 private String tag_keyword;

}

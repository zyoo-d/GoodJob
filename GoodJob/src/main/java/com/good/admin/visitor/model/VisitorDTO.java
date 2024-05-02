package com.good.admin.visitor.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class VisitorDTO {
	
	private LocalDate visit_date;
	private int visit_count;
	

}

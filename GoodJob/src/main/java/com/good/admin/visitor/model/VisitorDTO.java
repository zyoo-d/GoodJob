package com.good.admin.visitor.model;

import java.time.LocalDate;

import lombok.Data;

/**
* VisitorDTO 클래스는 방문자 데이터를 담고 있는 데이터 전송 객체입니다.
*/
@Data
public class VisitorDTO {
	
	private LocalDate visit_date;
	private int visit_count;
	
}

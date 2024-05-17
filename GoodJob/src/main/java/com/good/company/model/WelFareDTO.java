package com.good.company.model;

import lombok.Data;

/**
* 복지 정보를 담는 DTO 클래스
*/
@Data
public class WelFareDTO {
	
	private String cp_seq;
	private String wel_seq;
	private String wel_name;
	private String weltype_seq;
	private String weltype_name;

}

package com.good.matching.model;

import java.util.ArrayList;

import lombok.Data;
/**
 * 사용자의 직업 관련 '위시리스트' 정보를 저장하는 데이터 전송 객체(DTO)입니다.
 * 이 클래스는 사용자의 교육 수준, 원하는 연봉 범위, 경력 선호도, 지역 코드, 복지 옵션과 같은 다양한 선호도 정보를 관리합니다.
 */
@Data
public class WishDTO {
	private String id;
	private String edu_seq;
	private String salary_seq;
	private String career;
	private ArrayList<String> lc_code;
	private ArrayList<String> wel_seq;
}

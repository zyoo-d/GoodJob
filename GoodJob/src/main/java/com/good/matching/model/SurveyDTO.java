package com.good.matching.model;

import lombok.Data;
/**
 * 설문조사 데이터를 나타내는 데이터 전송 객체(DTO)입니다.
 * 이 객체는 설문조사의 각 항목에 대한 정보를 저장하며, 설문조사 결과 처리 및 표시에 사용됩니다.
 */
@Data
public class SurveyDTO {

	private String rnum;
	private String sv_seq;
	private String sv_content;
	private String sv_category;
	private String compare;
}

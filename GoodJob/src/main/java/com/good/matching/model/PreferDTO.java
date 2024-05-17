package com.good.matching.model;

import lombok.Data;
/**
 * 사용자의 선호도 정보를 저장하는 데이터 전송 객체(DTO)입니다.
 * 이 객체는 사용자의 복지, 급여, 안정성, 문화, 성장 가능성 등 선호도 관련 여러 지표를 저장합니다.
 */
@Data
public class PreferDTO {
	private String id;
	private int welfare;
	private int salary;
	private int stability;
	private int culture;
	private int potential;
	private String prf_regdate;
}

package com.good.board.report.repository;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * 신고 정보를 다루는 DAO 인터페이스입니다.
 * 제네릭 타입 T는 신고 정보를 담는 DTO 클래스를 나타냅니다.
 *
 * @param <T> 신고 정보를 담는 DTO 클래스
 */
public interface ReportDAO<T>{
	
	 /**
     * 데이터베이스 연결을 닫습니다.
     */
	public void close();
	/**
     * 새로운 신고를 생성합니다.
     *
     * @param dto 생성할 신고 정보
     * @return 생성된 신고의 수
     */
	public int create(T dto);
	 /**
     * 특정 날짜의 신고 수를 가져옵니다.
     *
     * @param date 조회할 날짜
     * @return 해당 날짜의 신고 수
     */
	public int getReportCountByDate(LocalDate date);
	 /**
     * 전체 신고 수를 가져옵니다.
     *
     * @return 전체 신고 수
     */
	public int totalCount();
	/**
     * 특정 사용자가 특정 대상을 신고했는지 확인합니다.
     *
     * @param id  사용자 ID
     * @param seq 신고 대상 식별자
     * @return 사용자가 해당 대상을 신고했다면 true, 그렇지 않으면 false
     */
	public boolean isReported(String id, String seq);
}

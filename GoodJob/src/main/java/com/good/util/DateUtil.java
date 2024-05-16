package com.good.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
* DateUtil 클래스는 날짜와 관련된 유틸리티 메서드를 제공합니다.
*/
public class DateUtil {

	/**
	    * 최근 지정된 일수만큼의 날짜 목록을 반환합니다.
	    *
	    * @param count 최근 일수
	    * @return 최근 날짜 목록
	    */
	public static List<LocalDate> getRecentDays(int count) {
		List<LocalDate> recentDays = new ArrayList<>();
		LocalDate today = LocalDate.now();
		for (int i = count - 1; i >= 0; i--) {
			recentDays.add(today.minusDays(i));
		}
		return recentDays;
	}

	 /**
	    * 주어진 시작 날짜부터 한 달 동안의 레이블 목록을 반환합니다.
	    * 레이블은 "월-일~월-일" 형식으로 3일 단위로 그룹화됩니다.
	    *
	    * @param startDate 시작 날짜
	    * @return 월 레이블 목록
	    */
	public static List<String> getMonthLabels(LocalDate startDate){
		List<String> labels = new ArrayList<>();
		int daysInMonth = startDate.lengthOfMonth();
		int interval = 3;
		int remaining = daysInMonth;

		while(remaining > 0) {
			LocalDate start = startDate.plusDays(daysInMonth - remaining);
			int days = Math.min(remaining, interval);
			LocalDate end = start.plusDays(days);
			labels.add(String.format("%d-%d~%d-%d", start.getMonthValue(), start.getDayOfMonth(), end.getMonthValue(), end.getDayOfMonth()));
			remaining -= days;
			if(remaining <= interval) {
				interval = remaining;
			}

		}

		return labels;
	}

	 /**
	    * 1월부터 12월까지의 레이블 목록을 반환합니다.
	    *
	    * @return 월 레이블 목록
	    */
	public static List<String> getYearLabels(){
		List<String> labels = new ArrayList<>();
		for(int i=1; i<=12; i++) {
			labels.add(String.format("%02d", i));
		}
		return labels;
	}

	 /**
	    * 현재 날짜부터 한 해 전까지의 연도-월 목록을 반환합니다.
	    *
	    * @return 연도-월 목록
	    */
	public static List<String> getYearlist(){
		List<String> yearList = new ArrayList<>();
		LocalDate currentDate = LocalDate.now();

		// 한 해 전 날짜 계산
		LocalDate oneYearAgo = currentDate.minusYears(1);

		// 연도 포맷 지정
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
		// 현재 날짜부터 한 해 전까지의 연도-월을 리스트에 추가
        while (!currentDate.isBefore(oneYearAgo)) {
            String formattedDate = currentDate.format(formatter);
            yearList.add(formattedDate);
            currentDate = currentDate.minusMonths(1);
        }
        
        return yearList;

	}

}

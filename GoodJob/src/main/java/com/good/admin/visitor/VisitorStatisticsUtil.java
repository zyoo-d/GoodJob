package com.good.admin.visitor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.good.util.DateUtil;

/**
* VisitorStatisticsUtil 클래스는 방문자 통계 관련 유틸리티 메서드를 제공합니다.
*/
public class VisitorStatisticsUtil {

	/**
	    * 주어진 시작 날짜부터 한 달 동안의 레이블 목록을 반환합니다.
	    * DateUtil 클래스의 getMonthLabels 메서드를 호출합니다.
	    *
	    * @param startDate 시작 날짜
	    * @return 월 레이블 목록
	    */
	public static List<String> getMonthLabels(LocalDate startDate) {
		return DateUtil.getMonthLabels(startDate);
	}
	
	/**
	    * 최근 날짜 목록과 방문자 수 맵을 사용하여 최근 방문자 수 목록을 반환합니다.
	    *
	    * @param recentDays   최근 날짜 목록
	    * @param visitorCounts 날짜별 방문자 수 맵
	    * @return 최근 방문자 수 목록
	    */
	public static List<Integer> getRecentVisitorCounts(List<LocalDate> recentDays, Map<LocalDate, Integer> visitorCounts) {
	    List<Integer> recentVisitors = new ArrayList<>();
	    for (LocalDate date : recentDays) {
	        recentVisitors.add(visitorCounts.getOrDefault(date, 0));
	    }
	    return recentVisitors;
	}

	/**
	    * 주어진 시작 날짜부터 한 달 동안의 방문자 수 목록을 반환합니다.
	    *
	    * @param givenStartDate 시작 날짜
	    * @param visitorCounts   날짜별 방문자 수 맵
	    * @return 월별 방문자 수 목록
	    */
	public static List<Integer> getMonthVisitorCounts(LocalDate givenStartDate, Map<LocalDate, Integer> visitorCounts) {
		List<Integer> monthVisitors = new ArrayList<>();
		List<String> monthLabels = getMonthLabels(givenStartDate);

		for (String label : monthLabels) {
			int totalVisitors = 0;
			String[] range = label.split("~");
			String[] startParts = range[0].split("-");
			String[] endParts = range[1].split("-");

			LocalDate startDate = LocalDate.of(LocalDate.now().getYear(), Integer.parseInt(startParts[0]), Integer.parseInt(startParts[1]));
			LocalDate endDate = LocalDate.of(LocalDate.now().getYear(), Integer.parseInt(endParts[0]), Integer.parseInt(endParts[1])).plusDays(1);

			for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
				totalVisitors += visitorCounts.getOrDefault(date, 0);
			}

			monthVisitors.add(totalVisitors);
		}

		return monthVisitors;
	}
	
	/**
	    * 현재 년도의 월별 방문자 수 목록을 반환합니다.
	    *
	    * @param visitorCounts 날짜별 방문자 수 맵
	    * @return 년도의 월별 방문자 수 목록
	    */
	public static List<Integer> getYearVisitorCounts(Map<LocalDate, Integer> visitorCounts) {
	    List<Integer> yearVisitors = new ArrayList<>();
	    LocalDate startOfYear = LocalDate.now().withDayOfYear(1);
	    LocalDate endOfYear = startOfYear.plusYears(1);

	    for (LocalDate date = startOfYear; date.isBefore(endOfYear); date = date.plusMonths(1)) {
	        int monthVisitor = 0;
	        int daysInMonth = date.lengthOfMonth();

	        for (int i = 1; i <= daysInMonth; i++) {
	            LocalDate currentDate = date.withDayOfMonth(i);
	            monthVisitor += visitorCounts.getOrDefault(currentDate, 0);
	        }

	        yearVisitors.add(monthVisitor);
	    }

	    return yearVisitors;
	}

}

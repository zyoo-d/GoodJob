package com.good.admin.visitor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VisitorStatisticsUtil {

	public static List<String> getMonthLabels(LocalDate startDate) {
		return DateUtil.getMonthLabels(startDate);
	}
	
	public static List<Integer> getRecentVisitorCounts(List<LocalDate> recentDays, Map<LocalDate, Integer> visitorCounts) {
	    List<Integer> recentVisitors = new ArrayList<>();
	    for (LocalDate date : recentDays) {
	        recentVisitors.add(visitorCounts.getOrDefault(date, 0));
	    }
	    return recentVisitors;
	}

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

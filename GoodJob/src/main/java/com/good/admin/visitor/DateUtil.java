package com.good.admin.visitor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DateUtil {

	public static List<LocalDate> getRecentDays(int count) {
		List<LocalDate> recentDays = new ArrayList<>();
		LocalDate today = LocalDate.now();
		for (int i = count - 1; i >= 0; i--) {
			recentDays.add(today.minusDays(i));
		}
		return recentDays;
	}

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

	public static List<String> getYearLabels(){
		List<String> labels = new ArrayList<>();
		for(int i=1; i<=12; i++) {
			labels.add(String.format("%02d", i));
		}
		return labels;
	}

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

package com.good.admin.visitor;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.good.admin.visitor.repository.VisitorDAO;

public class VisitorTracker {
	
	
	private static final VisitorTracker visitorTracker = new VisitorTracker();
	private Map<LocalDate, Integer> visitorCounts;
	
	private VisitorTracker() {
		VisitorDAO dao = new VisitorDAO();
		LocalDate currentDate = LocalDate.now();
		visitorCounts = dao.getVisitorsUntilDate(currentDate);
		
		if(!visitorCounts.containsKey(currentDate)) {
		    visitorCounts.put(currentDate, 0);
		    if(!dao.existsForDate(currentDate)) {
		        dao.insertToday(currentDate);
		    }
		}
		dao.close();
		
	}

	/**
	 * 싱글톤 객체 반환
	 * @return
	 */
	public static VisitorTracker getInstance() {
		return visitorTracker;
	}
	
	/**
	 * 방문자수 카운트 증가
	 */
	public synchronized void incrementVisitors() {
		LocalDate currentDate = LocalDate.now();
		int currentCount = visitorCounts.getOrDefault(currentDate, 0);
		
		visitorCounts.put(currentDate, currentCount+1);
		System.out.println("총 방문자수: "  +visitorCounts.get(currentDate));
	}
	
	/**
	 * 방문자수 들어있는 map 리턴
	 * @return
	 */
	public Map<LocalDate, Integer> getVisitors() {
		return visitorCounts;
	}
	
	
}

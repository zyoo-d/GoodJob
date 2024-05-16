package com.good.admin.visitor;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.good.admin.visitor.repository.VisitorDAO;

/**
* VisitorTracker 클래스는 방문자 수를 추적하고 관리하는 기능을 제공합니다.
* 싱글톤 패턴을 사용하여 단일 인스턴스를 생성합니다.
*/
public class VisitorTracker {
	
	/**
	* VisitorTracker 클래스는 방문자 수를 추적하고 관리하는 기능을 제공합니다.
	* 싱글톤 패턴을 사용하여 단일 인스턴스를 생성합니다.
	*/
	private static final VisitorTracker visitorTracker = new VisitorTracker();
	/**
	    * 날짜별 방문자 수를 저장하는 맵입니다.
	    */
	private Map<LocalDate, Integer> visitorCounts;
	
	 /**
	    * VisitorTracker 생성자입니다.
	    * 데이터베이스에서 현재까지의 방문자 수 데이터를 가져와 visitorCounts 맵을 초기화합니다.
	    */
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
	    * VisitorTracker 싱글톤 인스턴스를 반환합니다.
	    *
	    * @return VisitorTracker 인스턴스
	    */
	public static VisitorTracker getInstance() {
		return visitorTracker;
	}
	
	 /**
	    * 현재 날짜의 방문자 수를 증가시킵니다.
	    */
	public synchronized void incrementVisitors() {
		LocalDate currentDate = LocalDate.now();
		int currentCount = visitorCounts.getOrDefault(currentDate, 0);
		
		visitorCounts.put(currentDate, currentCount+1);
		System.out.println("총 방문자수: "  +visitorCounts.get(currentDate));
	}
	
	/**
	    * 날짜별 방문자 수 맵을 반환합니다.
	    *
	    * @return 날짜별 방문자 수 맵
	    */
	public Map<LocalDate, Integer> getVisitors() {
		return visitorCounts;
	}
	
	
}

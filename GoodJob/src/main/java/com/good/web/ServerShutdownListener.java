package com.good.web;

import java.time.LocalDate;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.good.admin.visitor.VisitorTracker;
import com.good.admin.visitor.repository.VisitorDAO;

/**
* ServerShutdownListener 클래스는 ServletContextListener 인터페이스를 구현합니다.
* @WebListener 애노테이션으로 리스너를 등록합니다.
*/
@WebListener
public class ServerShutdownListener implements ServletContextListener{
	
	/**
	    * 서버 종료 시 호출되는 메서드입니다.
	    * 현재 날짜의 방문자 수를 데이터베이스에 저장합니다.
	    *
	    * @param event ServletContextEvent 객체입니다.
	    */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
		VisitorTracker visitorTracker = VisitorTracker.getInstance();
		Map<LocalDate, Integer> visitorCounts = visitorTracker.getVisitors();
		
		LocalDate today = LocalDate.now();
		int todayVisitor = visitorCounts.get(today);
		
		VisitorDAO dao = new VisitorDAO();
//		System.out.println("서버종료 메서드");
//		System.out.println("today:" + today);
//		System.out.println("todayVisitor:" + todayVisitor);
		dao.saveVisitorsForDate(today, todayVisitor);
		
	}

}

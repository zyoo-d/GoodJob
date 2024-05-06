package com.good.listener;

import java.time.LocalDate;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.good.admin.VisitorTracker;
import com.good.admin.visitor.repository.VisitorDAO;

@WebListener
public class ServerShutdownListener implements ServletContextListener{
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
		VisitorTracker visitorTracker = VisitorTracker.getInstance();
		Map<LocalDate, Integer> visitorCounts = visitorTracker.getVisitors();
		
		LocalDate today = LocalDate.now();
		int todayVisitor = visitorCounts.get(today);
		
		VisitorDAO dao = new VisitorDAO();
		System.out.println("서버종료 메서드");
		System.out.println("today:" + today);
		System.out.println("todayVisitor:" + todayVisitor);
		dao.saveVisitorsForDate(today, todayVisitor);
		
	}

}

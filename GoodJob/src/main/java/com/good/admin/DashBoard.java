package com.good.admin;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.company.repository.CompanyDAO;

@WebServlet("/admin/main.do")
public class DashBoard extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		VisitorTracker visitorTracker = VisitorTracker.getInstance();
		
		Map<LocalDate, Integer> visitorCounts = visitorTracker.getVisitors();
		CompanyDAO dao = new CompanyDAO();
		
		
		req.setAttribute("visit_count", visitorCounts.get(LocalDate.now()));
		req.setAttribute("countCompanys",dao.countCompanys());
	
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/index.jsp");
		dispatcher.forward(req, resp);
		
		
		
	}
	
}

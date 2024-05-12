package com.good.admin.board;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.admin.visitor.DateUtil;
import com.good.board.repository.BoardCommonDAO;

@WebServlet("/admin/boardreport.do")
public class BoardReport extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		BoardCommonDAO boardCommonDAO = new BoardCommonDAO();
		
		String todayReportCount = boardCommonDAO.getTodayReportCount();
		int totalCompleteReport = boardCommonDAO.getTotalCompleteReport();
		
		List<LocalDate> recentDays = DateUtil.getRecentDays(10);
		
		
		
		req.setAttribute("recentDays", recentDays);
		req.setAttribute("todayReportCount", todayReportCount);
		req.setAttribute("totalCompleteReport", totalCompleteReport);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/board/boardreport.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	

}

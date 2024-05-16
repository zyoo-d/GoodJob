package com.good.admin.board;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.admin.PageUtil;
import com.good.admin.visitor.DateUtil;
import com.good.board.report.model.ReportCommonDTO;
import com.good.board.report.repository.ReportCommonDAO;
import com.good.board.repository.BoardCommonDAO;

@WebServlet("/admin/boardreport.do")
public class BoardReport extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		BoardCommonDAO boardCommonDAO = new BoardCommonDAO();
		ReportCommonDAO reportCommonDAO = new ReportCommonDAO();
		
		String todayReportCount = boardCommonDAO.getTodayReportCount();
		
		int totalCompleteReport = boardCommonDAO.getTotalCompleteReport();
		int totalCount = reportCommonDAO.getTotalReportBoardCount();
		int pageSize = 10;
		
		int currentPage = PageUtil.parseCurrentPage(req.getParameter("page"));
		
		PageUtil pageUtil = new PageUtil(totalCount, pageSize, currentPage);
		int startIndex = pageUtil.calculateStartIndex();
		int endIndex = pageUtil.calculateEndIndex();
		
		List<LocalDate> recentDays = DateUtil.getRecentDays(10);
		
		HashMap<String, HashMap<String,Integer>> recentReportCount = boardCommonDAO.getRecentReportCount(recentDays);
		
		ArrayList<ReportCommonDTO> allReportList = reportCommonDAO.getAllReportBoardList(startIndex,endIndex);
		
		
		req.setAttribute("recentDays", recentDays);
		req.setAttribute("todayReportCount", todayReportCount);
		req.setAttribute("totalCompleteReport", totalCompleteReport);
		req.setAttribute("pageUtil", pageUtil);
		req.setAttribute("allReportList", allReportList);
		req.setAttribute("recentReportCount", recentReportCount);
		
		boardCommonDAO.close();
		reportCommonDAO.close();
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/board/boardreport.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	

}

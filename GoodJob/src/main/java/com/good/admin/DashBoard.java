package com.good.admin;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.admin.visitor.DateUtil;
import com.good.admin.visitor.VisitorStatisticsUtil;
import com.good.admin.visitor.VisitorTracker;
import com.good.board.model.BoardCommonDTO;
import com.good.board.report.model.ReportCommonDTO;
import com.good.board.report.repository.ReportCommonDAO;
import com.good.board.repository.BoardCommonDAO;
import com.good.company.repository.CompanyDAO;
import com.good.company.repository.RecruitDAO;
import com.good.company.repository.ReviewDAO;
import com.good.user.repository.UserDAO;

@WebServlet("/admin/main.do")
public class DashBoard extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		VisitorTracker visitorTracker = VisitorTracker.getInstance();
		Map<LocalDate, Integer> visitorCounts = visitorTracker.getVisitors();

		CompanyDAO companyDAO = new CompanyDAO();
		RecruitDAO recruitDAO = new RecruitDAO();
		ReviewDAO reviewDAO = new ReviewDAO();
		BoardCommonDAO boardCommonDAO = new BoardCommonDAO();
		ReportCommonDAO reportCommonDAO = new ReportCommonDAO();
		UserDAO userDAO = new UserDAO();
		
		req.setAttribute("visit_count", visitorCounts.get(LocalDate.now()));
		req.setAttribute("countCompanys",companyDAO.countCompanys());
		req.setAttribute("jobPostings_count",recruitDAO.countOpenJobPostings());
		req.setAttribute("pendingReview", reviewDAO.getPendingReviewsCount());
		req.setAttribute("pendingInterview", reviewDAO.getPendingInterviewCount());
		
		List<LocalDate> recentDays = DateUtil.getRecentDays(10);
		List<Integer> recentVisitors = VisitorStatisticsUtil.getRecentVisitorCounts(recentDays, visitorCounts);
		LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1);
		List<String> monthLabels = VisitorStatisticsUtil.getMonthLabels(startOfMonth);
		List<Integer> monthVisitors = VisitorStatisticsUtil.getMonthVisitorCounts(startOfMonth, visitorCounts);
		List<String> yearLabels = DateUtil.getYearLabels();
		List<Integer> yearVisitors = VisitorStatisticsUtil.getYearVisitorCounts(visitorCounts);
		
		req.setAttribute("recentDays", recentDays);
		req.setAttribute("recentVisitors", recentVisitors);
		req.setAttribute("monthLabels", monthLabels);
	    req.setAttribute("monthVisitors", monthVisitors);
	    req.setAttribute("yearLabels", yearLabels);
	    req.setAttribute("yearVisitors", yearVisitors);
	    
	    ArrayList<BoardCommonDTO> boardList = boardCommonDAO.getWeekTopPosts();
	    HashMap<String, Double> countBoardList = boardCommonDAO.getPostRatioByBoard(); 
	    
	    System.out.println(countBoardList);
	    
	    req.setAttribute("boardList", boardList);
	    req.setAttribute("countBoardList", countBoardList);
	    
	    int getNewSubscriberCount = userDAO.getNewSubscriberCount();
	    
	    req.setAttribute("getNewSubscriberCount", getNewSubscriberCount);
	    
	    
	    ArrayList<ReportCommonDTO> recentReportList = reportCommonDAO.getRecentReportTitleList(4);
	    
	    req.setAttribute("recentReportList", recentReportList);
	    
	    req.setAttribute("updateDate", companyDAO.getUpdateDate());
	    
	    
	    companyDAO.close();
	    recruitDAO.close();
	    reviewDAO.close();
	    boardCommonDAO.close();
	    userDAO.close();
	    reportCommonDAO.close();
	    
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/index.jsp");
		dispatcher.forward(req, resp);
		
		
		
		
		
	}
	
}

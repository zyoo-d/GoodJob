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
import com.good.util.DateUtil;

/**
 * DashBoard 서블릿은 관리자 대시보드 페이지를 제공하며, 다양한 통계 데이터를 조회하고 JSP 페이지로 전달합니다.
 */
@WebServlet("/admin/main.do")
public class DashBoard extends HttpServlet{

	/**
	 * doGet 메서드는 관리자 대시보드 페이지에 필요한 데이터를 조회하고 JSP 페이지로 전달합니다.
	 *
	 * @param req  HttpServletRequest 객체
	 * @param resp HttpServletResponse 객체
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1. 데이터 조회
		VisitorTracker visitorTracker = VisitorTracker.getInstance();
		Map<LocalDate, Integer> visitorCounts = visitorTracker.getVisitors();

		CompanyDAO companyDAO = new CompanyDAO();
		RecruitDAO recruitDAO = new RecruitDAO();
		ReviewDAO reviewDAO = new ReviewDAO();
		BoardCommonDAO boardCommonDAO = new BoardCommonDAO();
		ReportCommonDAO reportCommonDAO = new ReportCommonDAO();
		UserDAO userDAO = new UserDAO();

		// 2. 데이터 가공
		List<LocalDate> recentDays = DateUtil.getRecentDays(10);
		List<Integer> recentVisitors = VisitorStatisticsUtil.getRecentVisitorCounts(recentDays, visitorCounts);

		LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1);
		List<String> monthLabels = VisitorStatisticsUtil.getMonthLabels(startOfMonth);
		List<Integer> monthVisitors = VisitorStatisticsUtil.getMonthVisitorCounts(startOfMonth, visitorCounts);

		List<String> yearLabels = DateUtil.getYearLabels();
		List<Integer> yearVisitors = VisitorStatisticsUtil.getYearVisitorCounts(visitorCounts);

		ArrayList<BoardCommonDTO> boardList = boardCommonDAO.getWeekTopPosts();
		HashMap<String, Double> countBoardList = boardCommonDAO.getPostRatioByBoard();

		int getNewSubscriberCount = userDAO.getNewSubscriberCount();

		ArrayList<ReportCommonDTO> recentReportList = reportCommonDAO.getRecentReportTitleList(4);

		// 3. JSP에 데이터 전달
		req.setAttribute("visit_count", visitorCounts.get(LocalDate.now()));
		req.setAttribute("countCompanys", companyDAO.countCompanys());
		req.setAttribute("jobPostings_count", recruitDAO.countOpenJobPostings());
		req.setAttribute("pendingReview", reviewDAO.getPendingReviewsCount());
		req.setAttribute("pendingInterview", reviewDAO.getPendingInterviewCount());
		req.setAttribute("recentDays", recentDays);
		req.setAttribute("recentVisitors", recentVisitors);
		req.setAttribute("monthLabels", monthLabels);
		req.setAttribute("monthVisitors", monthVisitors);
		req.setAttribute("yearLabels", yearLabels);
		req.setAttribute("yearVisitors", yearVisitors);
		req.setAttribute("boardList", boardList);
		req.setAttribute("countBoardList", countBoardList);
		req.setAttribute("getNewSubscriberCount", getNewSubscriberCount);
		req.setAttribute("recentReportList", recentReportList);
		req.setAttribute("updateDate", companyDAO.getUpdateDate());

		// 4. DAO 종료
		companyDAO.close();
		recruitDAO.close();
		reviewDAO.close();
		boardCommonDAO.close();
		userDAO.close();
		reportCommonDAO.close();

		// 5. JSP 페이지로 전달
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/index.jsp");
		dispatcher.forward(req, resp);

	}


}

package com.good.admin.usermanagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.board.report.model.ReportCommonDTO;
import com.good.board.report.repository.ReportCommonDAO;
import com.good.util.PageUtil;

/**
 * ListReportUser 서블릿은 관리자 페이지에서 신고된 사용자 목록을 조회하고 관리하는 역할을 합니다.
 */
@WebServlet("/admin/listreportuser.do")
public class ListReportUser extends HttpServlet {

	/**
	 * doGet 메서드는 신고된 사용자 목록을 조회하고 JSP 페이지로 전달합니다.
	 *
	 * @param req  HttpServletRequest 객체
	 * @param resp HttpServletResponse 객체
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ReportCommonDAO reportCommonDAO = new ReportCommonDAO();
		String reportType = req.getParameter("rp_seq");
		Integer rp_seq = (reportType != null && !reportType.isEmpty()) ? Integer.parseInt(reportType) : null;
		int totalCount = reportCommonDAO.getTotalReportCount(rp_seq);
		int pageSize = 10;

		int currentPage = PageUtil.parseCurrentPage(req.getParameter("page"));

		PageUtil pageUtil = new PageUtil(totalCount, pageSize, currentPage);
		int startIndex = pageUtil.calculateStartIndex();
		int endIndex = pageUtil.calculateEndIndex();

		ArrayList<ReportCommonDTO> reportList = reportCommonDAO.getAllReportList(rp_seq, startIndex, endIndex);

		req.setAttribute("reportList", reportList);
		req.setAttribute("pageUtil", pageUtil);

		reportCommonDAO.close();

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/user/listreportuser.jsp");
		dispatcher.forward(req, resp);

	}
	/**
	 * doPost 메서드는 신고된 사용자에 대한 조치(차단 등)를 처리합니다.
	 *
	 * @param req  HttpServletRequest 객체
	 * @param resp HttpServletResponse 객체
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ReportCommonDAO reportCommonDAO = new ReportCommonDAO();

		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");

		String userId = req.getParameter("user-id");
		String status = req.getParameter("status");
		String blockDate = req.getParameter("block-date");
		String releaseDate = req.getParameter("release-date");
		String blockReason = req.getParameter("block-reason");
		String report_seq  = req.getParameter("report_seq");
		String report_type = req.getParameter("report_type");
		String report_sub_type = req.getParameter("report_sub_type");


		List<String> userIds = Arrays.asList(userId.split(","));
		HashMap<String,String> blockStatus = reportCommonDAO.checkBlock(userIds);

		reportCommonDAO.blockuser(blockStatus,status,blockDate,releaseDate,blockReason);

		List<String> report_seqs = Arrays.asList(report_seq.split(","));
		List<String> report_types = Arrays.asList(report_type.split(","));
		List<String> report_sub_types = Arrays.asList(report_sub_type.split(","));

		reportCommonDAO.confirmReport(report_seqs, report_types, report_sub_types , id);

		reportCommonDAO.close();

		resp.sendRedirect("/good/admin/listreportuser.do");

	}
}



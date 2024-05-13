//package com.good.admin.interview;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.good.admin.PageUtil;
//import com.good.board.model.InterviewApprovalDTO;
//import com.good.board.report.model.ReportCommonDTO;
//import com.good.board.report.repository.ReportCommonDAO;
//import com.good.board.repository.InterviewDAO;
//
//@WebServlet("/board/interview/InterviewApproval.do")
//public class InterviewApproval extends HttpServlet {
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		ReportCommonDAO reportCommonDAO = new ReportCommonDAO();
//
//		int totalCount = reportCommonDAO.getTotalReportCount(rp_seq);
//		int pageSize = 10;
//
//		int currentPage = PageUtil.parseCurrentPage(req.getParameter("page"));
//
//		PageUtil pageUtil = new PageUtil(totalCount, pageSize, currentPage);
//		int startIndex = pageUtil.calculateStartIndex();
//		int endIndex = pageUtil.calculateEndIndex();
//
//		ArrayList<ReportCommonDTO> reportList = reportCommonDAO.getAllReportList(rp_seq, startIndex, endIndex);
//
//		req.setAttribute("reportList", reportList);
//		req.setAttribute("pageUtil", pageUtil);
//
//	
//		InterviewDAO dao = new InterviewDAO();
//		ArrayList<InterviewApprovalDTO> list = dao.getInterviewApproval();
//
//		req.setAttribute("list", list);
//		System.out.println(list);
//
//		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/review/interviewapproval.jsp");
//		dispatcher.forward(req, resp);
//
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String itvSeq = req.getParameter("itvSeq");
//		System.out.println(itvSeq);
//
//		if (itvSeq != null) { // null 체크
//			InterviewDAO dao = new InterviewDAO();
//			int result = dao.AddapproveInterview(itvSeq);
//			System.out.println(result);
//
//			if (result > 0) {
//				// 승인 처리 성공
//				int insertresult = dao.insertApproval(itvSeq);
//				if (insertresult > 0) {
//					resp.setContentType("text/plain");
//					resp.getWriter().write("success");
//				}else {
//					resp.setContentType("text/plain");
//					resp.getWriter().write("error");
//				}
//
//			} else {
//				resp.setContentType("text/plain");
//				resp.getWriter().write("error");
//			
//			}
//		} else {
//			// itvSeq가 null인 경우
//			resp.setContentType("text/plain");
//			resp.getWriter().write("error");
//		}
//	}
//
//}

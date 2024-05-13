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
//import com.good.board.model.InterviewCompanionDTO;
//import com.good.board.repository.InterviewDAO;
//
//@WebServlet("/board/interview/InterviewCompanion.do")
//public class InterviewCompanion extends HttpServlet {
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//		InterviewDAO dao = new InterviewDAO();
//		ArrayList<InterviewCompanionDTO> list = dao.getInterviewCompanion();
//
//		req.setAttribute("list", list);
//		System.out.println(list);
//
//		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/review/interviewcompanion.jsp");
//		dispatcher.forward(req, resp);
//
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String itvSeq = req.getParameter("itvSeq");
//		String reason = req.getParameter("reason");
//
//		System.out.println(itvSeq);
//		System.out.println(reason);
//
//
//		if (itvSeq != null) { // null 체크
//			InterviewDAO dao = new InterviewDAO();
//			int result = dao.AddInterviewCompanion(itvSeq);
//			System.out.println(result);
//
//			if (result > 0) {
//				// 승인 처리 성공
//				int insertresult = dao.insertCompanion(itvSeq,reason);
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
//		 
//	}
//
//}

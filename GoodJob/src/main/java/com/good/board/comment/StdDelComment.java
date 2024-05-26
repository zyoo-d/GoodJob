package com.good.board.comment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.board.repository.StudyDAO;
/*
  Servlet implementation class StdDelComment
 
  이 서블릿은 "/board/comment/stddelcomment.do" URL 요청을 처리합니다.
  스터디 게시판의 댓글을 삭제하는 기능을 제공합니다.

 */
@WebServlet("/board/comment/stddelcomment.do")
public class StdDelComment extends HttpServlet {
	
	/**
     * HTTP GET 요청을 처리합니다.
     * 이 메서드는 현재 아무 동작을 하지 않고 JSP 페이지로 포워딩합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException Servlet 관련 에러 발생 시
     * @throws IOException      I/O 에러 발생 시
     */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		// JSP 페이지로 포워딩
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/study/study.jsp");
		dispatcher.forward(req, resp);
	}
	  /**
     * HTTP POST 요청을 처리하여 스터디 게시판의 댓글을 삭제합니다.
     *
     * @param request  HttpServletRequest 객체
     * @param response HttpServletResponse 객체
     * @throws ServletException Servlet 관련 에러 발생 시
     * @throws IOException      I/O 에러 발생 시
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		  // 삭제할 댓글 ID 가져오기
		String seq = request.getParameter("seq");
		
		 // StudyDAO를 사용하여 댓글 삭제
		StudyDAO dao = new StudyDAO();
		
		int result = dao.delComment(seq);
		 // JSON 형식의 응답 데이터 생성
		response.setContentType("application/json");
		
		PrintWriter writer = response.getWriter();
		writer.print("{");
		writer.print("\"result\": " + result); //"result": 1
		writer.print("}");
		writer.close();
		
		
		
		
	}

}
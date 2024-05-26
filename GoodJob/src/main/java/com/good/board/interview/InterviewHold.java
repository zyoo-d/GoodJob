package com.good.board.interview;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.board.model.InterviewDTO;
import com.good.board.repository.InterviewDAO;


/*
  InterviewHold 서블릿 구현 클래스

  이 서블릿은 "/board/interview/InterviewHold.do" URL 요청을 처리합니다.
  보류 중인 면접 목록을 가져와 JSP 페이지로 전달합니다.

 */
	@WebServlet("/board/interview/InterviewHold.do")
	
	public class InterviewHold extends HttpServlet {
		  /**
	     * HTTP GET 요청을 처리하여 보류 중인 면접 목록을 표시합니다.
	     *
	     * @param req  HttpServletRequest 객체
	     * @param resp HttpServletResponse 객체
	     * @throws ServletException Servlet 관련 에러 발생 시
	     * @throws IOException      I/O 에러 발생 시
	     */

	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	            InterviewDAO dao = new InterviewDAO();
	            ArrayList<InterviewDTO> list = dao.getInterviewHold();
	            
	            req.setAttribute("list", list);

	            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/review/interviewhold.jsp");
	            dispatcher.forward(req, resp);
	  
	    }
	    
	}




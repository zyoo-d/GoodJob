package com.goodjob.test.mg;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/interview.do")
public class Interview extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  InterviewDAO dao = new InterviewDAO();
	        ArrayList<InterviewDTO> list = dao.list();
	        
	        

	        // request 객체에 데이터 저장
	        req.setAttribute("interviewList", list);

	        // JSP 페이지로 포워딩
	        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/interview/interview.jsp");
	        dispatcher.forward(req, resp);
	    }
	}
	

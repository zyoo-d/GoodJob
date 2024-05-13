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



	@WebServlet("/board/interview/InterviewHold.do")
	public class InterviewHold extends HttpServlet {

	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	            InterviewDAO dao = new InterviewDAO();
	            ArrayList<InterviewDTO> list = dao.getInterviewHold();
	            
	            req.setAttribute("list", list);

	            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/review/interviewhold.jsp");
	            dispatcher.forward(req, resp);
	  
	    }
	    
	}




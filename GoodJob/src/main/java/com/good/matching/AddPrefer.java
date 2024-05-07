package com.good.matching;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/matching/addprefer.do")
public class AddPrefer extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int salary1 = Integer.parseInt(req.getParameter("연봉1"));
		int salary3 = Integer.parseInt(req.getParameter("연봉2"));
		int salary2 = Integer.parseInt(req.getParameter("연봉3"));
		int salary4 = Integer.parseInt(req.getParameter("연봉4"));
		
		int welfare1 = Integer.parseInt(req.getParameter("복지1"));
		int welfare3 = Integer.parseInt(req.getParameter("복지2"));
		int welfare2 = Integer.parseInt(req.getParameter("복지3"));
		int welfare4 = Integer.parseInt(req.getParameter("복지4"));
		
		int stability1 = Integer.parseInt(req.getParameter("조직안정성1"));
		int stability3 = Integer.parseInt(req.getParameter("조직안정성2"));
		int stability2 = Integer.parseInt(req.getParameter("조직안정성3"));
		int stability4 = Integer.parseInt(req.getParameter("조직안정성4"));
		
		int culture1 = Integer.parseInt(req.getParameter("조직문화1"));
		int culture3 = Integer.parseInt(req.getParameter("조직문화2"));
		int culture2 = Integer.parseInt(req.getParameter("조직문화3"));
		int culture4 = Integer.parseInt(req.getParameter("조직문화4"));
		
		int potential1 = Integer.parseInt(req.getParameter("성장가능성1"));
		int potential3 = Integer.parseInt(req.getParameter("성장가능성2"));
		int potential2 = Integer.parseInt(req.getParameter("성장가능성3"));
		int potential4 = Integer.parseInt(req.getParameter("성장가능성4"));

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/matching/addprefer.jsp");
		dispatcher.forward(req, resp);

	}

}
package com.good.board.study;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.board.model.StudyDTO;
import com.good.board.repository.StudyDAO;

@WebServlet("/user/addstudy.do")
public class AddStudy extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/study/addstudy.jsp");
		dispatcher.forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String std_title = req.getParameter("std_title");
		String std_content = req.getParameter("std_content");
		String std_duedate = req.getParameter("std_duedate");
		String cp_seq = req.getParameter("cp_seq");
		
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		
		StudyDTO dto = new StudyDTO();
		dto.setCp_seq(cp_seq);
		dto.setId(id);
		dto.setStd_content(std_content);
		dto.setStd_duedate(std_duedate);
		dto.setStd_ing("N");
		dto.setStd_title(std_title);
		
		StudyDAO dao = new StudyDAO();
		
		int result = dao.addStudy(dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/study/addstudy.jsp");
		dispatcher.forward(req, resp);
		
	}

}
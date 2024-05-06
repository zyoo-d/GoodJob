package com.good.board.study;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.alert.Alert;
import com.good.board.model.StudyDTO;
import com.good.board.repository.StudyDAO;

@WebServlet("/user/editstudy.do")
public class EditStudy extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 인증받지 못한 사용자 or 권한이 없는 사용자 > 거부
		
		if(AuthStudy.check(req, resp)) { return; }
		String mypage = req.getParameter("mypage");
		
		if (mypage == null || mypage.equals("")) {
			mypage = "N";
		} 
		
		String std_seq = req.getParameter("std_seq");
		StudyDAO dao = new StudyDAO();
		StudyDTO dto = dao.getStudy(std_seq);

		String date = dto.getStd_duedate().substring(0, 10);
		dto.setStd_duedate(date);
		
		req.setAttribute("dto", dto);
		req.setAttribute("mypage", mypage);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/study/editstudy.jsp");
		dispatcher.forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String std_title = req.getParameter("std_title");
		String std_content = req.getParameter("std_content");
		String std_duedate = req.getParameter("std_duedate");
		String std_ing = req.getParameter("std_ing");
		String std_seq = req.getParameter("std_seq");
		String cp_seq = req.getParameter("cp_seq");
		
		StudyDTO dto = new StudyDTO();
		dto.setCp_seq(cp_seq);
		dto.setStd_seq(std_seq);
		dto.setStd_content(std_content);
		dto.setStd_duedate(std_duedate);
		dto.setStd_ing(std_ing);
		dto.setStd_title(std_title);
		
		StudyDAO dao = new StudyDAO();
		
		int result = dao.editStudy(dto);
		
		if(result==1) {
			resp.sendRedirect("/good/user/viewstudy.do?std_seq="+std_seq);
		} else {
			Alert.fail(resp);
		}
		
	}
}
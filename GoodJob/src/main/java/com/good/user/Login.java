package com.good.user;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.user.model.UserDTO;
import com.good.user.repository.UserDAO;

@WebServlet("/user/signin.do")
public class Login extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/signin.jsp");
		dispatcher.forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		UserDTO dto = new UserDTO();
		UserDAO dao = new UserDAO();
		
		dto.setId(id);
		dto.setPw(pw);
		
		UserDTO result = dao.login(dto);
		
		dao.close();
		
		if (result != null) {
			//인증 처리
			
			HttpSession session = req.getSession();
			
			session.setAttribute("id", id); //인증 티켓	
			session.setAttribute("name", result.getName());
			session.setAttribute("lv", result.getLv());
			session.setAttribute("wish", result.getWish());
			session.setAttribute("prefer", result.getPrefer());
			
			//접속 기록 추가하기
			
			
			resp.sendRedirect("/good/main.do");
			
		} else {
			//실패 처리
			
			resp.setContentType("text/html; charset=UTF-8");
		    resp.setCharacterEncoding("UTF-8");
		    PrintWriter writer = resp.getWriter();
		    writer.print("<script>alert('로그인을 실패했습니다.'); location.href='/good/user/signin.do';</script>");
		    writer.close();
			
		}
		
	}
}
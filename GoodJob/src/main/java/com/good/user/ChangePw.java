package com.good.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.user.model.UserDTO;
import com.good.user.repository.UserDAO;

@WebServlet("/user/changepw.do")
public class ChangePw extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");
		
		req.setAttribute("id", id);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/changepw.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
	    System.out.println("POST ID: " + id);
	    System.out.println("POST PW: " + pw);
		

		UserDAO dao = new UserDAO();
		UserDTO dto = new UserDTO();
		
		dto.setId(id);
		dto.setPw(pw);
		
		int result = dao.changePw(dto);
		
		
		if (result == 1) {
			resp.sendRedirect("/good/main.do");
			
		} else {
			resp.sendRedirect("/good/user/getaccount.do");
			
		}

	}

}

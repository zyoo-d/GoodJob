package com.good.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.user.model.UserDTO;
import com.good.user.repository.UserDAO;

@WebServlet("/user/editinfo.do")
public class Edit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		 HttpSession session = req.getSession();
		    
		    // 세션에서 사용자 ID 가져오기
		    String id = (String) session.getAttribute("id");
		    System.out.println(id);
		    
		    UserDAO dao = new UserDAO();
		    
		    UserDTO dto = dao.userInfo(id);
		    
		    req.setAttribute("email", dto.getEmail());
		    req.setAttribute("address", dto.getAddress());

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/editinfo.jsp");
		dispatcher.forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String id = req.getParameter("id");
		String pw = req.getParameter("password");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		
		UserDTO dto = new UserDTO();
		UserDAO dao = new UserDAO();
		
		dto.setId(id);
		dto.setPw(pw);
		dto.setEmail(email);
		dto.setAddress(address);
		
		int result = dao.edit(dto);
		
		if (result == 1) {
			resp.sendRedirect("/good/user/mypagemain.do");
			
		} else {
			resp.sendRedirect("/good/user/editinfo.do");
			
			
		}
		
	
	}

}

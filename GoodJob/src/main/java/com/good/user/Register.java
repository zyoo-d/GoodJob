package com.good.user;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.user.model.UserDTO;
import com.good.user.repository.UserDAO;

@WebServlet("/user/signup.do")
public class Register extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/signup.jsp");
		dispatcher.forward(req, resp);


	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String domain = req.getParameter("domain");
		String tel = req.getParameter("tel");
		String address = req.getParameter("address");
		String nickname = req.getParameter("nickname");
		
	    if ("직접입력".equals(domain)) {
	        domain = req.getParameter("domain");
	    }
		
		UserDTO dto = new UserDTO();
		UserDAO dao = new UserDAO();
		
		dto.setId(id);
		dto.setPw(pw);
		dto.setName(name);
		dto.setEmail(email);
		dto.setDomain(domain);
		dto.setTel(tel);
		dto.setAddress(address);
		dto.setNickname(nickname);
		
		System.out.println(domain);
		
		int result = dao.signUp(dto);
		
		dao.close();
		
		if (result == 1) {
		    resp.setContentType("application/json");
		    resp.setCharacterEncoding("UTF-8");
		    PrintWriter writer = resp.getWriter();
		    
		    // JSON 형식으로 응답 데이터 생성
		    String jsonResponse = "{\"success\": true, \"message\": \"회원가입이 완료되었습니다. It's GoodJob의 멤버가 되신 것을 환영합니다.\"}";
		    
		    // JSON 응답 전송
		    writer.print(jsonResponse);
		    writer.flush();
		} else {
		    resp.setContentType("application/json");
		    resp.setCharacterEncoding("UTF-8");
		    PrintWriter writer = resp.getWriter();
		    
		    // JSON 형식으로 응답 데이터 생성
		    String jsonResponse = "{\"success\": false, \"message\": \"회원가입에 실패했습니다. 다시 시도해주세요.\"}";
		    
		    // JSON 응답 전송
		    writer.print(jsonResponse);
		    writer.flush();
		}
	
	}
	
}


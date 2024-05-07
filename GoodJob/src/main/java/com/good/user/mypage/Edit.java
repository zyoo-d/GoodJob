package com.good.user.mypage;

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

@WebServlet("/user/mypage/editinfo.do")
public class Edit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		 HttpSession session = req.getSession();
		    
		    // 세션에서 사용자 ID 가져오기
		    String id = (String) session.getAttribute("id");
		    System.out.println(id);
		    
		    UserDAO dao = new UserDAO();
		    
		    UserDTO dto = dao.userInfo(id);
		    dao.close();
		    
		    req.setAttribute("email", dto.getEmail());
		    req.setAttribute("address", dto.getAddress());

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/mypage/editinfo.jsp");
		dispatcher.forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");

	    String id = req.getParameter("id");
	    String pw = req.getParameter("password");
	    String email = req.getParameter("email");
	    String address = req.getParameter("address");

	    String previousEmail = req.getParameter("previousEmail");
	    String previousAddress = req.getParameter("previousAddress");

	    // 이메일이 비어 있는 경우 이전 이메일 값을 사용
	    if (email == null || email.equals("")) {
	        email = previousEmail;
	    }
	    if (address == null || address.equals("")) {
	    	address = previousAddress;
	    }

	    UserDTO dto = new UserDTO();
	    UserDAO dao = new UserDAO();

	    dto.setId(id);
	    dto.setPw(pw);
	    dto.setEmail(email);
	    dto.setAddress(address);

	    int result = dao.edit(dto);

	    dao.close();

	    if (result == 1) {
	        resp.sendRedirect("/good/user/mypage/main.do");
	    } else {
			PrintWriter writer = resp.getWriter();
			writer.println("<html><head><meta charset=\"UTF-8\"><title>Access Denied</title></head><body>");
			writer.println("<script type='text/javascript'>");
			writer.println("alert('수정을 실패했습니다.');");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();
	    	resp.sendRedirect("/good/user/mypage/editinfo.do");
	    }
	}


}

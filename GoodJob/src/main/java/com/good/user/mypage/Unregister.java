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

import com.good.user.repository.UserDAO;

@WebServlet("/user/mypage/unregister.do")
public class Unregister extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/mypage/unregister.jsp");
		dispatcher.forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		String id = (String)session.getAttribute("id");
		
		System.out.println(id);
		
		UserDAO dao = new UserDAO();
		
		
		int result = dao.unregister(id);
		
		if (result == 1) {
			session.invalidate();
			resp.sendRedirect("/good/main.do");
		} else {
			PrintWriter writer = resp.getWriter();
			writer.println("<html><head><meta charset=\"UTF-8\"><title>Access Denied</title></head><body>");
			writer.println("<script type='text/javascript'>");
			writer.println("alert('탈퇴를 실패했습니다.');");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();

		}
		
	}

}
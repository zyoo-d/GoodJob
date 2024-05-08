package com.good.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.user.repository.UserDAO;

@WebServlet("/user/checknick.do")
public class CheckNick extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		UserDAO dao = new UserDAO();
		String nickname = req.getParameter("nickname");
			
		int result = dao.checkNick(nickname);
		
		dao.close();
		PrintWriter writer = resp.getWriter();
		writer.print(result);
		writer.close();

	}
}
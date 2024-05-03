package com.good.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.user.repository.UserDAO;

@WebServlet("/user/checkid.do")
public class CheckID extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		UserDAO dao = new UserDAO();
		String id = req.getParameter("id");
			
		int result = dao.checkId(id);
		
		PrintWriter writer = resp.getWriter();
		writer.print(result);
		writer.close();

	}
}

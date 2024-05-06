package com.good.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.good.user.model.UserDTO;
import com.good.user.repository.UserDAO;

@WebServlet("/user/getpw.do")
public class GetPw extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");
		String tel = req.getParameter("tel");
		
		System.out.println("id :" + id);
		System.out.println("tel :" + tel);
		
		UserDTO dto = new UserDTO();
		UserDAO dao = new UserDAO();
		
		dto.setId(id);
		dto.setTel(tel);
		
		int result = dao.getPw(dto);
		
		JSONObject obj = new JSONObject();
		
		obj.put("result", result);
		
		PrintWriter writer = resp.getWriter();
		writer.print(obj);
		writer.close();
	}
}

package com.good.board.comment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.board.repository.StudyDAO;
import com.good.company.repository.CompanyDAO;

@WebServlet("/board/comment/livedelcomment.do")
public class LiveDelComment extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
		String seq = request.getParameter("seq");
		System.out.println(seq);
		
	
		CompanyDAO cmdao = new CompanyDAO();
		
		int result = cmdao.delComment(seq);
		System.out.println(result);
		response.setContentType("application/json");
		
		PrintWriter writer = response.getWriter();
		writer.print("{");
		writer.print("\"result\": " + result); //"result": 1
		writer.print("}");
		writer.close();
		
		
		
		
	}
}

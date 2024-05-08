package com.good.board.comment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.board.model.CommentDTO;
import com.good.board.repository.StudyDAO;

@WebServlet("/board/comment/stdeditcomment.do")
public class StdEditComment extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/study/study.jsp");
		dispatcher.forward(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String content = request.getParameter("content");
		String seq = request.getParameter("seq");
		
		System.out.println(content);
		System.out.println(seq);
		
		
		
		StudyDAO dao = new StudyDAO();
		
		CommentDTO dto = new CommentDTO();
		dto.setCm_seq(seq);
		dto.setContent(content);
		
		int result = dao.editComment(dto);
		
		response.setContentType("application/json");
		
		PrintWriter writer = response.getWriter();
		writer.print("{");
		writer.print("\"result\": " + result); //"result": 1
		writer.print("}");
		writer.close();
		
	}

}
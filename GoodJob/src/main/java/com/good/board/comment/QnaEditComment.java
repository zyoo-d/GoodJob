package com.good.board.comment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.board.model.CommentDTO;
import com.good.board.repository.QnaBoardDAO;

@WebServlet("/board/comment/qnaeditcomment.do")
public class QnaEditComment extends HttpServlet {



	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String seq = request.getParameter("seq");
		String content  =request.getParameter("content");
		
		
		QnaBoardDAO dao = new QnaBoardDAO();
		
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
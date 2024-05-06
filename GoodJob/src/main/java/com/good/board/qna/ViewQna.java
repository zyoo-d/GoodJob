package com.good.board.qna;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.board.model.QnaBoardDTO;
import com.good.board.repository.QnaBoardDAO;

@WebServlet("/user/qna/qnaview.do")
public class ViewQna extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int qna_seq = Integer.parseInt(req.getParameter("qna_seq"));
		
		QnaBoardDAO dao = new QnaBoardDAO();
		QnaBoardDTO dto = dao.viewQna(qna_seq);
		
		req.setAttribute("dto", dto);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/qna/qnaview.jsp");
		dispatcher.forward(req, resp);
		
	}

}

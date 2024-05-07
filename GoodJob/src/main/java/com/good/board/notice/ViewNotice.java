package com.good.board.notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.board.model.NoticeDTO;
import com.good.board.repository.NoticeDAO;

@WebServlet("/board/notice/notice.do")
public class ViewNotice extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String nt_seq = req.getParameter("nt_seq");
		
		NoticeDTO dto = new NoticeDTO();
		NoticeDAO dao = new NoticeDAO();
		
		dto = dao.viewNotice(nt_seq);
		
		
		req.setAttribute("dto", dto);
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/notice/notice.jsp");
		dispatcher.forward(req, resp);

	}

}
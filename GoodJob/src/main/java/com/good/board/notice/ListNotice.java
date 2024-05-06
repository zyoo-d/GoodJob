package com.good.board.notice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.board.model.NoticeDTO;
import com.good.board.repository.NoticeDAO;

@WebServlet("/board/notice.do")
public class ListNotice extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		NoticeDAO dao = new NoticeDAO();
		
		ArrayList<NoticeDTO> list = dao.list();
		
		req.setAttribute("list", list);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/notice/noticeBoard.jsp");
		dispatcher.forward(req, resp);

	}

}

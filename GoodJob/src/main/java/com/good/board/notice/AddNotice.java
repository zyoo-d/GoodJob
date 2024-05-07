package com.good.board.notice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.board.model.BoardDTO;
import com.good.board.repository.NoticeDAO;

@WebServlet("/board/noticewrite.do")
public class AddNotice extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		if (!"2".equals((String) session.getAttribute("lv"))) {
			resp.setContentType("text/html; charset=UTF-8");
		    resp.setCharacterEncoding("UTF-8");
		    PrintWriter writer = resp.getWriter();
		    writer.print("<script>alert('접근권한이 없습니다.');location.href='/good/main.do';</script>");
		    writer.close();
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/notice/noticeWrite.jsp");
		dispatcher.forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	HttpSession session = req.getSession();
	
	String id = (String)session.getAttribute("id");
	String title= req.getParameter("title");
	String content = req.getParameter("content");
	
	BoardDTO dto = new BoardDTO();
	NoticeDAO dao = new NoticeDAO();
	
	dto.setId(id);
	dto.setTitle(title);
	dto.setContent(content);
	
	int result = dao.addNotice(dto);
	
	if (result == 1) {
		resp.sendRedirect("/good/board/notice");
		
	} else {
		resp.setContentType("text/html; charset=UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    PrintWriter writer = resp.getWriter();
	    writer.print("<script>alert('등록을 실패했습니다.'); location.href='/good/board/noticeWrite.do';</script>");
	    writer.close();
	}
	
	}

}

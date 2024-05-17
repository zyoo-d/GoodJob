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

import com.good.board.model.BoardCommonDTO;
import com.good.board.repository.NoticeDAO;
/**
 * 공지사항을 추가하는 서블릿입니다.
 */
@WebServlet("/board/noticewrite.do")
public class AddNotice extends HttpServlet {
	/**
     * 공지사항 작성 페이지로 이동합니다.
     * 접근 권한이 없는 경우 메인 페이지로 리다이렉트됩니다.
     */
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
	 /**
     * 공지사항을 추가합니다.
     * 추가가 성공하면 공지사항 목록 페이지로 리다이렉트됩니다.
     * 추가에 실패하면 에러 메시지가 표시되고 공지사항 작성 페이지로 리다이렉트됩니다.
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	HttpSession session = req.getSession();
	
	String id = (String)session.getAttribute("id");
	String title= req.getParameter("title");
	String content = req.getParameter("content");
	
	BoardCommonDTO dto = new BoardCommonDTO();
	NoticeDAO dao = new NoticeDAO();
	
	dto.setId(id);
	dto.setTitle(title);
	dto.setContent(content);
	
	int result = dao.addNotice(dto);
	
	if (result == 1) {
		resp.sendRedirect("/good/board/notice.do");
		
	} else {
		resp.setContentType("text/html; charset=UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    PrintWriter writer = resp.getWriter();
	    writer.print("<script>alert('등록을 실패했습니다.'); location.href='/good/board/noticeWrite.do';</script>");
	    writer.close();
	}
	
	}

}

package com.good.board.notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.board.model.NoticeDTO;
import com.good.board.repository.NoticeDAO;
/**
 * 공지사항을 조회하는 서블릿입니다.
 */
@WebServlet("/board/notice/viewnotice.do")
public class ViewNotice extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String nt_seq = req.getParameter("seq");
		
		HttpSession session = req.getSession();
		NoticeDTO dto = new NoticeDTO();
		NoticeDAO dao = new NoticeDAO();
		
		String viewedKey = "nt_" + nt_seq + "viewed";
		if(session.getAttribute(viewedKey) == null) {
			dao.updateReadcount(nt_seq);
			session.setAttribute(viewedKey, true);
		}
		
		dto = dao.viewNotice(nt_seq);
		
		String content = dto.getNt_content();
		
		content = content.replace(">", "&gt;").replace("<", "&lt;");
		
		dto.setNt_content(content);
		
		req.setAttribute("dto", dto);
		
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/notice/notice.jsp");
		dispatcher.forward(req, resp);

	}

}
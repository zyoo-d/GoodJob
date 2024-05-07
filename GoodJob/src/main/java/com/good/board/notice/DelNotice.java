package com.good.board.notice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.board.repository.NoticeDAO;

@WebServlet("/board/notice/delnotice.do")
public class DelNotice extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = (String) req.getAttribute("nt_seq");
		System.out.println(seq);
		NoticeDAO dao = new NoticeDAO();
		
		int result = dao.delNotice(seq);
		
		if(result == 1) {
			resp.sendRedirect("/good/board/notice.do");
			
		} else {
			resp.setContentType("text/html; charset=UTF-8");
		    resp.setCharacterEncoding("UTF-8");
		    PrintWriter writer = resp.getWriter();
		    writer.print("<script>alert('삭제를 실패했습니다.'); location.href='/good/board/notice/notice.do?nt_seq=seq';</script>");
		    writer.close();
		}


	}

}

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

import org.json.simple.JSONObject;

import com.good.board.model.NoticeDTO;
import com.good.board.qna.AuthQna;
import com.good.board.repository.NoticeDAO;

/**
 * 공지사항을 수정하는 서블릿입니다.
 */
@WebServlet("/board/notice/editnotice.do")
public class EditNotice extends HttpServlet {

    /**
     * 공지사항 수정 폼을 가져옵니다.
     * 관리자만 접근할 수 있습니다.
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		HttpSession session = req.getSession();
		//접근 권한
		if (!"2".equals((String) session.getAttribute("lv"))) {
			resp.setContentType("text/html; charset=UTF-8");
		    resp.setCharacterEncoding("UTF-8");
		    PrintWriter writer = resp.getWriter();
		    writer.print("<script>alert('접근권한이 없습니다.');location.href='/good/notice.do';</script>");
		    writer.close();
		}
		
		String qna_seq = req.getParameter("qna_seq");
		
		//인증
		if(AuthQna.check(req, resp)) {
			return;
		}
		
		NoticeDAO dao = new NoticeDAO();
		NoticeDTO dto = new NoticeDTO();
		
		String seq = req.getParameter("seq");
		
		dto = dao.viewNotice(seq);
		
		String content = dto.getNt_content();
		
		content = content.replace(">", "&gt;").replace("<", "&lt;");
		
		dto.setNt_content(content);
		
		req.setAttribute("dto", dto);
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/notice/editNotice.jsp");
		dispatcher.forward(req, resp);

	}
	
    /**
     * 공지사항을 수정합니다.
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String seq = req.getParameter("seq");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		System.out.println("seq = " + seq + " title = " + title + " content = " + content);
		
		NoticeDTO dto = new NoticeDTO();
		NoticeDAO dao = new NoticeDAO();
		
		dto.setNt_seq(seq);
		dto.setNt_title(title);
		dto.setNt_content(content);
		
		int result = dao.edit(dto);
		
		JSONObject obj = new JSONObject();
		
		obj.put("result", result);
		
		PrintWriter writer = resp.getWriter();
		
		writer.print(obj);
		writer.close();
		
		dao.close();

	}

}
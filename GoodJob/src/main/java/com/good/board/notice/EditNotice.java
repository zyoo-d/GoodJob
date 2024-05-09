package com.good.board.notice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.good.board.model.NoticeDTO;
import com.good.board.repository.NoticeDAO;

@WebServlet("/board/notice/editnotice.do")
public class EditNotice extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
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
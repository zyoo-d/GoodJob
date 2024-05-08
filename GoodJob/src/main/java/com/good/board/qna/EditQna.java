package com.good.board.qna;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.board.model.QnaBoardDTO;
import com.good.board.repository.QnaBoardDAO;

@WebServlet("/user/qna/editqna.do")
public class EditQna extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		//접근 권한
		if (!"2".equals((String) session.getAttribute("lv"))) {
			resp.setContentType("text/html; charset=UTF-8");
		    resp.setCharacterEncoding("UTF-8");
		    PrintWriter writer = resp.getWriter();
		    writer.print("<script>alert('접근권한이 없습니다.');location.href='/good/main.do';</script>");
		    writer.close();
		}
		
		String qna_seq = req.getParameter("qna_seq");
		
		QnaBoardDAO dao = new QnaBoardDAO();
		
		QnaBoardDTO dto = dao.get(qna_seq);
		
		req.setAttribute("dto", dto);
		
		dao.close();
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/qna/editqna.jsp");
		dispatcher.forward(req, resp);
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		String id = (String) session.getAttribute("id");
		String qna_title = req.getParameter("qna_title");
		String cp_name = req.getParameter("cp_name");
		String qna_content = req.getParameter("qna_content");
		String qna_seq = req.getParameter("qna_seq");
		
		System.out.println(cp_name);
		
		QnaBoardDTO dto = new QnaBoardDTO();
		dto.setId(id);
		dto.setQna_title(qna_title);
		dto.setCp_name(cp_name);
		dto.setQna_content(qna_content);
		dto.setQna_seq(Integer.parseInt(qna_seq));
		
		
		QnaBoardDAO dao = new QnaBoardDAO();
		int result = dao.edit(dto);
		
		if(result == 1) {
			
			resp.sendRedirect("/good/user/qna/qnaview.do?qna_seq="+qna_seq);
			
		} else {
			
			PrintWriter writer = resp.getWriter();
			 writer.println("<script>");
		     writer.println("alert('게시글 작성에 실패했습니다.');");
		     writer.println("history.back();"); 
		     writer.println("</script>");
			 writer.flush();
			 writer.close();
			
		}
		
		dao.close();
		
		
		
		
	}

}

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

@WebServlet("/user/qna/addqna.do")
public class AddQna extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		
		if(id== null || id.equals("")) {
			
			
			
		} else {
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/qna/addqna.jsp");
			dispatcher.forward(req, resp);
			
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		String id = (String) session.getAttribute("id");
		String qna_title = req.getParameter("qna_title");
		String cp_name = req.getParameter("cp_name");
		String qna_content = req.getParameter("qna_content");
		
		System.out.println(cp_name);
		
		QnaBoardDTO dto = new QnaBoardDTO();
		dto.setId(id);
		dto.setQna_title(qna_title);
		dto.setCp_name(cp_name);
		dto.setQna_content(qna_content);
		
		QnaBoardDAO dao = new QnaBoardDAO();
		int qna_seq = dao.create(dto);
		
		if(qna_seq > 0) {
			
			resp.sendRedirect("/good/user/qna/viewqna.do?qna_seq="+qna_seq);
			
		} else {
			resp.setCharacterEncoding("UTF-8");
			
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

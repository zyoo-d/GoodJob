package com.good.board.study;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.board.model.CommentDTO;
import com.good.board.model.StudyDTO;
import com.good.board.repository.StudyDAO;

@WebServlet("/user/study/viewstudy.do")
public class ViewStudy extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String std_seq = req.getParameter("std_seq");
		String column = req.getParameter("column");
		String word = req.getParameter("word");
		String search = req.getParameter("search");
		String page = req.getParameter("page");
		
		StudyDAO dao = new StudyDAO();
		
		//조회수 증가
		if(session.getAttribute("read")!=null&& session.getAttribute("read").toString().equals("n")) {
			dao.updateReadcount(std_seq);
			session.setAttribute("read", "y");
		}
		
		StudyDTO dto = dao.getStudy(std_seq);
		
		String subject = dto.getStd_title();
		subject = subject.replace(">", "&gt;").replace("<", "&lt;");
		dto.setStd_title(subject);
		
		String content = dto.getStd_content();
		//글내용 > 태그 > 이스케이프
		content = content.replace(">", "&gt;").replace("<", "&lt;");
		dto.setStd_content(content);
		
		//날짜 자르기
		String duedate = dto.getStd_duedate().substring(5, 10);
		dto.setStd_duedate(duedate);
		String regdate = dto.getStd_regdate().substring(0, 10);
		dto.setStd_regdate(regdate);
		
		//댓글 목록 가져오기
		ArrayList<CommentDTO> clist = dao.listComment(std_seq);
	
		req.setAttribute("dto", dto);
		req.setAttribute("column", column);
		req.setAttribute("word", word);
		req.setAttribute("page", page);
		req.setAttribute("clist", clist);

		dao.close();
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/study/viewstudy.jsp");
		dispatcher.forward(req, resp);

	}

}
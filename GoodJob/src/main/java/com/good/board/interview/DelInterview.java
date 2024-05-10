package com.good.board.interview;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.alert.Alert;
import com.good.board.qna.AuthQna;
import com.good.board.repository.InterviewDAO;

@WebServlet("/board/interview/itvDel.do")
public class DelInterview extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
		
		String itv_seq = req.getParameter("itv_seq");
		System.out.println(itv_seq);
		InterviewDAO dao = new InterviewDAO();
		int result = dao.delQna(itv_seq);
		
		if(result == 1) {
			System.out.println("삭제 성공");
			resp.sendRedirect("/good/board/interview/interview.do");
			
			
		} else {
			
			Alert.fail(resp);
			resp.sendRedirect("/itvWrite.do?itv_seq=" + itv_seq);
			
		}
		
		
		
	}
	
}

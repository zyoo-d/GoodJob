package com.good.board.study;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.alert.Alert;
import com.good.board.repository.StudyDAO;

@WebServlet("/user/delstudy.do")
public class DelStudy extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 인증받지 못한 사용자 or 권한이 없는 사용자 > 거부
		if (AuthStudy.check(req, resp)) {
			return;
		}
		
		
		String mypage = req.getParameter("mypage");
		String seq = req.getParameter("std_seq");
		
		if (mypage == null || mypage.equals("")) {
			mypage = "N";
		} 
		
		StudyDAO dao = new StudyDAO(); 

		int result = dao.delStudy(seq);
		
		if(result==1 && mypage.equals("Y")) {
			resp.sendRedirect("/good/user/mypage/mystudy.do");
		} else if (result==1&& mypage.equals("N")) {
			resp.sendRedirect("/good/user/liststudy.do");
		} else {
			Alert.fail(resp);
		}

	}

}
package com.good.user.mypage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.board.repository.InterviewDAO;
import com.good.board.repository.QnaBoardDAO;
import com.good.board.repository.StudyDAO;
import com.good.company.repository.ReviewDAO;
import com.good.matching.model.MatchingDTO;
import com.good.matching.repository.MatchingDAO;

@WebServlet("/user/mypage/main.do")
public class Mypage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		
		StudyDAO stddao = new StudyDAO();
		int stdCnt = stddao.getCount(id);
		
		ReviewDAO rvdao = new ReviewDAO();
		int rvCnt = rvdao.getCount(id);
		
		InterviewDAO itvdao = new InterviewDAO();
		int itvCnt = itvdao.getCount(id);
		
		QnaBoardDAO qnadao = new QnaBoardDAO();
		int qnaCnt = qnadao.getMyCount(id);
		
		MatchingDAO matdao = new MatchingDAO();
		MatchingDTO dto = matdao.getScore(id);
		
		int totalCnt = stdCnt+rvCnt+itvCnt+qnaCnt;
		
		rvdao.close();
		itvdao.close();
		qnadao.close();
		stddao.close();
		matdao.close();
		
		req.setAttribute("totalCnt", totalCnt);
		req.setAttribute("rvCnt", rvCnt);
		req.setAttribute("itvCnt", itvCnt);
		req.setAttribute("qnaCnt", qnaCnt);
		req.setAttribute("stdCnt", stdCnt);
		req.setAttribute("dto", dto);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/mypage/mymain.jsp");
		dispatcher.forward(req, resp);

	}

}
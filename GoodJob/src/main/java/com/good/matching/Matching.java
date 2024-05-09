package com.good.matching;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.matching.model.MatchingDTO;
import com.good.matching.repository.MatchingDAO;

@WebServlet("/user/matching.do")
public class Matching extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		
		MatchingDAO dao = new MatchingDAO();
		HashMap<Integer, String> scoreMap = dao.getScore(id);
		
		MatchingAlgo algo = new MatchingAlgo();
		
		String[] columnNames = algo.sort(scoreMap);
		
		ArrayList<MatchingDTO> list = dao.getMatching(columnNames, id);
		
		
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/matching/matching.jsp");
		dispatcher.forward(req, resp);
		
		
	}

}

package com.good.matching;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.company.repository.CompanyDAO;
import com.good.matching.model.MatchingDTO;
import com.good.matching.repository.MatchingDAO;
import com.good.user.repository.UserDAO;

@WebServlet("/user/matching.do")
public class Matching extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		
		MatchingDAO dao = new MatchingDAO();
		UserDAO userDAO = new UserDAO();
		
		String name = userDAO.getName(id);
		
		HashMap<Integer, String> scoreMap = dao.getColumn(id);
		
		MatchingAlgo algo = new MatchingAlgo();
		
		String[] columnNames = algo.sort(scoreMap);
		
		ArrayList<MatchingDTO> list = dao.getMatching(columnNames, id);
		
		CompanyDAO companyDAO = new CompanyDAO();
		
		for (MatchingDTO mdto : list) {
			
			 mdto.setDto(companyDAO.getMatchingInfo(mdto));
			 mdto.getDto().setTag_list(companyDAO.getTaglist(mdto.getCp_seq()));
			
		}
		
		
		
 		
		
		MatchingDTO dto = dao.getScore(id);
		ScoreMatcher.calculateMatchingRate(list, dto);
		Collections.sort(list, Comparator.reverseOrder());
		
		for (MatchingDTO dto1 : list) {
			
			System.out.println(dto1.toString());
			
		}
		
		ArrayList<MatchingDTO> top3 = new ArrayList<>(list.subList(0, 3));
		list.subList(0, 3).clear();
		
		req.setAttribute("name", name);
		req.setAttribute("top3", top3);
		req.setAttribute("list", list);
		req.setAttribute("dto", dto);
		
		
		
		RequestDispatcher dispatcher =
		req.getRequestDispatcher("/WEB-INF/views/user/matching/matching.jsp");
		dispatcher.forward(req, resp);
		 
		
		
	}

}

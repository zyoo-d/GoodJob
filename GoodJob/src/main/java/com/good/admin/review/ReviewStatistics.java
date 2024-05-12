package com.good.admin.review;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.company.repository.CompanyDAO;

@WebServlet("/admin/review/reviewstatistics.do")
public class ReviewStatistics extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		CompanyDAO companyDAO = new CompanyDAO();
		
		LinkedHashMap<String, Integer> top5ReviewCountCompany = companyDAO.getTop5CompaniesByReviewCount();
		LinkedHashMap<String, Double> top5ReviewScoreCompany = companyDAO.getTop5CompaniesByReviewScore();
		
		LinkedHashMap<String, Integer> yearlyReviewStats = companyDAO.getYearlyReviewStats();
		
		System.out.println(yearlyReviewStats);
 		
		req.setAttribute("top5ReviewCountCompany", top5ReviewCountCompany);
		req.setAttribute("top5ReviewScoreCompany", top5ReviewScoreCompany);
		req.setAttribute("yearlyReviewStats", yearlyReviewStats);
		
		companyDAO.close();
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/review/reviewstatistics.jsp");
		dispatcher.forward(req, resp);
	}

}

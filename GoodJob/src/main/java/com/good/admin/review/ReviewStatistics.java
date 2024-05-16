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

/**
 * ReviewStatistics 서블릿은 관리자 페이지에서 회사 리뷰 통계를 조회하고 JSP 페이지로 전달하는 역할을 합니다.
 */
@WebServlet("/admin/review/reviewstatistics.do")
public class ReviewStatistics extends HttpServlet{

	/**
	 * doGet 메서드는 회사 리뷰 통계 데이터를 조회하고 JSP 페이지로 전달합니다.
	 *
	 * @param req  HttpServletRequest 객체
	 * @param resp HttpServletResponse 객체
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		CompanyDAO companyDAO = new CompanyDAO();

		LinkedHashMap<String, Integer> top5ReviewCountCompany = companyDAO.getTop5CompaniesByReviewCount();
		LinkedHashMap<String, Double> top5ReviewScoreCompany = companyDAO.getTop5CompaniesByReviewScore();

		LinkedHashMap<String, Integer> yearlyReviewStats = companyDAO.getYearlyReviewStats();


		req.setAttribute("top5ReviewCountCompany", top5ReviewCountCompany);
		req.setAttribute("top5ReviewScoreCompany", top5ReviewScoreCompany);
		req.setAttribute("yearlyReviewStats", yearlyReviewStats);

		companyDAO.close();

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/review/reviewstatistics.jsp");
		dispatcher.forward(req, resp);
	}

}

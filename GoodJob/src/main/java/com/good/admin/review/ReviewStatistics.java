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
 * 리뷰 통계 서블릿
 * 
 * 이 서블릿은 관리자 페이지에서 리뷰 통계 정보를 조회하고 표시하기 위한 기능을 제공합니다.
 * 리뷰 수 기준 상위 5개 기업, 리뷰 점수 기준 상위 5개 기업, 연도별 리뷰 통계 정보를 조회하여
 * JSP 페이지로 전달합니다.
 */
@WebServlet("/admin/review/reviewstatistics.do")
public class ReviewStatistics extends HttpServlet{
	
    /**
     * GET 요청 처리 메소드
     * 
     * 리뷰 통계 정보를 조회하고 JSP 페이지로 전달합니다.
     * 리뷰 수 기준 상위 5개 기업, 리뷰 점수 기준 상위 5개 기업, 연도별 리뷰 통계 정보를 조회합니다.
     * 
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외 발생 시
     * @throws IOException      입출력 예외 발생 시
     */
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

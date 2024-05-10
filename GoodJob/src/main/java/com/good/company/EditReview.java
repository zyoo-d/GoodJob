package com.good.company;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.company.model.CompanyDTO;
import com.good.company.model.ReviewDTO;
import com.good.company.repository.CompanyDAO;
import com.good.company.repository.ReviewDAO;

@WebServlet("/user/company/review/editreview.do")
public class EditReview extends HttpServlet {

	  @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        HttpSession session = req.getSession();
	        String id = (String) session.getAttribute("id");

	        String cp_rv_seq = req.getParameter("cp_rv_seq");

	        ReviewDAO rdao = new ReviewDAO();
	        ReviewDTO rdto = rdao.getReviewByCpRvSeq(cp_rv_seq);
	        
	        CompanyDAO cdao = new CompanyDAO();
	        CompanyDTO cdto = cdao.getCompanyBySeq(rdto.getCp_seq());
	        
	        boolean isAdmin = id != null && id.equals("admin"); // 관리자 여부 확인 로직
	        boolean isAuthor = id != null && id.equals(rdto.getId()); // 작성자 여부 확인 로직 추가

	        if (rdto != null && cdto !=null) {
	            req.setAttribute("isAdmin", isAdmin);
	            req.setAttribute("isAuthor", isAuthor);
	            req.setAttribute("rdto", rdto);
	            req.setAttribute("cdto", cdto);
	            
	            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/company/review/editreview.jsp");
	            dispatcher.forward(req, resp);
	        } else {
	            resp.sendRedirect("/good/user/mypage/myreview.do");
	        }
	    }

	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        HttpSession session = req.getSession();
	        String id = (String) session.getAttribute("id");
	        String action = req.getParameter("action");
	        String cp_rv_seq = req.getParameter("cp_rv_seq");
	        ReviewDAO rdao = new ReviewDAO();
	        ReviewDTO rdto = rdao.getReviewByCpRvSeq(cp_rv_seq);
	        boolean isAdmin = id != null && id.equals("admin"); // 관리자 여부 확인 로직
	        if (rdto != null) {
	            if (isAdmin) {
	                // 관리자일 경우 리뷰 승인/거절 로직 수행
	                if (action != null && action.equals("approve")) {
	                    rdao.updateReviewConfirm(cp_rv_seq, 1); // 리뷰 승인
	                } else if (action != null && action.equals("reject")) {
	                    rdao.updateReviewConfirm(cp_rv_seq, 2); // 리뷰 거절
	                }
	            } else if (action != null && action.equals("update")) {
	                // 리뷰 수정 로직
	                rdto.setLinereview(req.getParameter("linereview"));
	                rdto.setGood(req.getParameter("good"));
	                rdto.setBad(req.getParameter("bad"));
	                // 수정된 별점 설정
	                rdto.setSalary_score(Double.parseDouble(req.getParameter("salary_score")));
	                rdto.setWelfare_score(Double.parseDouble(req.getParameter("welfare_score")));
	                rdto.setStability_score(Double.parseDouble(req.getParameter("stability_score")));
	                rdto.setCulture_score(Double.parseDouble(req.getParameter("culture_score")));
	                rdto.setGrowth_score(Double.parseDouble(req.getParameter("growth_score")));
	                rdao.updateReviewByCpRvSeq(rdto);
	            }
	            resp.sendRedirect("/good/user/mypage/myreview.do");
	        }
	        }
}

package com.good.company;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.company.model.ReviewDTO;
import com.good.company.repository.ReviewDAO;

@WebServlet("/user/company/review/editreview.do")
public class EditReview extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");
		
		String action = req.getParameter("action");
		String cp_seq = req.getParameter("cp_seq");
		String cp_rv_seq = req.getParameter("cp_rv_seq");

		ReviewDAO rdao = new ReviewDAO();

		boolean isAdmin = id.equals("admin"); // 관리자 여부 확인 로직
		boolean isAuthor = false; // 작성자 여부 확인 변수 추가

		ReviewDAO rrdao = new ReviewDAO();
		ArrayList<ReviewDTO> listReview = rrdao.listReview(cp_seq);

		if (isAdmin) {
			// 관리자일 경우 리뷰 승인/거절 로직 수행
			if (action != null && action.equals("approve")) {
				rrdao.updateReviewConfirm(cp_seq, 1); // 리뷰 승인
			} else if (action != null && action.equals("reject")) {
				rrdao.updateReviewConfirm(cp_seq, 2); // 리뷰 거절
			}
		} else {
			// 작성자 여부 확인 로직 추가
			for (ReviewDTO rdto : listReview) {
				if (rdto.getId().equals(id) && rdto.getCp_rv_confirm() == 0) {
					isAuthor = true;
					break;
				}
			}
		}

		if (isAuthor && action != null && action.equals("delete")) {
			rrdao.deleteReview(cp_seq, id);
			resp.sendRedirect("/good/user/company/companyview.do?cp_seq=" + cp_seq);
			return;
		}
		if (action.equals("update")) {
			
			// 리뷰 수정 로직
			ReviewDTO rdto = new ReviewDTO();
			rdto.setCp_seq(cp_seq);
			rdto.setCp_rv_seq(cp_rv_seq);
			// 수정된 리뷰 내용 설정
			rdto.setLinereview(req.getParameter("linereview"));
			rdto.setGood(req.getParameter("good"));
			rdto.setBad(req.getParameter("bad"));
			// 수정된 별점 설정
			rdto.setSalary_score(Double.parseDouble(req.getParameter("salary_score")));
			rdto.setWelfare_score(Double.parseDouble(req.getParameter("welfare_score")));
			rdto.setStability_score(Double.parseDouble(req.getParameter("stability_score")));
			rdto.setCulture_score(Double.parseDouble(req.getParameter("culture_score")));
			rdto.setGrowth_score(Double.parseDouble(req.getParameter("growth_score")));

			rdao.updateReview(rdto);
		}
		req.setAttribute("isAdmin", isAdmin);
		req.setAttribute("isAuthor", isAuthor);
		req.setAttribute("listReview", listReview);

		resp.sendRedirect("/good/user/company/companyview.do?cp_seq=" + cp_seq);
	}
}

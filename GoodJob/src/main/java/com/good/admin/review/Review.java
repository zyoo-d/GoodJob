package com.good.admin.review;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.company.model.ReviewDTO;
import com.good.company.repository.ReviewDAO;
import com.good.util.PageUtil;


/**
 * Review 서블릿은 관리자 페이지에서 사용자가 작성한 리뷰를 조회하고 승인 또는 거절하는 기능을 제공합니다.
 */
@WebServlet("/admin/review/review.do")
public class Review extends HttpServlet {
	/**
	 * doGet 메서드는 승인 대기 중인 리뷰 목록을 조회하고 JSP 페이지로 전달합니다.
	 *
	 * @param req HttpServletRequest 객체
	 * @param resp HttpServletResponse 객체
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String lv = (String) session.getAttribute("lv");

		ReviewDAO rdao = new ReviewDAO();
		int totalCount = rdao.getPendingReviewsCount();
		int pageSize = 10;

		int currentPage = PageUtil.parseCurrentPage(req.getParameter("page"));

		PageUtil pageUtil = new PageUtil(totalCount, pageSize, currentPage);
		int startIndex = pageUtil.calculateStartIndex();
		int endIndex = pageUtil.calculateEndIndex();

		ArrayList<ReviewDTO> list = rdao.getAllReviews(startIndex, endIndex);

		req.setAttribute("list", list);
		req.setAttribute("pageUtil", pageUtil);

		rdao.close();

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/review/review.jsp");
		dispatcher.forward(req, resp);
	}
	/**
	 * doPost 메서드는 리뷰 승인 또는 거절 요청을 처리합니다.
	 *
	 * @param req HttpServletRequest 객체
	 * @param resp HttpServletResponse 객체
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] cp_rv_seqs = req.getParameterValues("cp_rv_seqs");
		String action = req.getParameter("action");
		String cp_rv_seq = req.getParameter("cp_rv_seq");
		String rj_reason = req.getParameter("rj_reason");

		ReviewDAO rdao = new ReviewDAO();

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		if (action == null) {
			boolean success = rdao.allApprove(cp_rv_seqs);
			resp.getWriter().write("{\"success\": " + success + "}");
		} else {
			boolean success = false;
			if (action.equals("approve")) {
				success = rdao.updateReviewConfirm(cp_rv_seq, 1); // 리뷰 승인
			} else if (action.equals("reject")) {
				success = rdao.updateReviewConfirm(cp_rv_seq, 2); // 리뷰 거절
				if (success) {
					try {
						success = rdao.insertRejectReview(cp_rv_seq, rj_reason); // 거절 사유
					} catch (Exception e) {
						success = false;
						e.printStackTrace();
					}
				}
			}
			resp.getWriter().write("{\"success\": " + success + "}");
		}

		rdao.close();

	}

}


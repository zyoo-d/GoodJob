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

import com.good.admin.PageUtil;
import com.good.company.model.ReviewDTO;
import com.good.company.repository.ReviewDAO;

/**
 * 관리자 리뷰 관리 서블릿
 * 
 * 이 서블릿은 관리자가 리뷰를 관리하기 위한 기능을 제공합니다. 리뷰 목록을 조회하고, 리뷰 승인 및 거절 처리를 수행합니다. GET 요청
 * 시 리뷰 목록을 조회하여 JSP 페이지로 전달하고, POST 요청 시 리뷰 승인 및 거절 처리를 수행합니다.
 */
@WebServlet("/admin/review/review.do")
public class Review extends HttpServlet {

	/**
	 * GET 요청 처리 메소드
	 * 
	 * 리뷰 목록을 조회하여 JSP 페이지로 전달합니다. 페이징 처리를 위해 PageUtil 클래스를 사용합니다.
	 * 
	 * @param req  HttpServletRequest 객체
	 * @param resp HttpServletResponse 객체
	 * @throws ServletException 서블릿 예외 발생 시
	 * @throws IOException      입출력 예외 발생 시
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
	 * POST 요청 처리 메소드
	 * 
	 * 리뷰 승인 및 거절 처리를 수행합니다. 요청 파라미터에 따라 일괄 승인, 개별 승인, 개별 거절 처리를 수행합니다.
	 * 
	 * @param req  HttpServletRequest 객체
	 * @param resp HttpServletResponse 객체
	 * @throws ServletException 서블릿 예외 발생 시
	 * @throws IOException      입출력 예외 발생 시
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
			// 일괄 승인 처리
			boolean success = rdao.allApprove(cp_rv_seqs);
			resp.getWriter().write("{\"success\": " + success + "}");
		} else {
			boolean success = false;
			if (action.equals("approve")) {
				// 개별 승인 처리
				success = rdao.updateReviewConfirm(cp_rv_seq, 1);
			} else if (action.equals("reject")) {
				// 개별 거절 처리
				success = rdao.updateReviewConfirm(cp_rv_seq, 2);
				if (success) {
					try {
						// 거절 사유 등록
						success = rdao.insertRejectReview(cp_rv_seq, rj_reason);
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

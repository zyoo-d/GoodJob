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

import com.good.company.model.CompanyDTO;
import com.good.company.model.ReviewDTO;
import com.good.company.repository.CompanyDAO;
import com.good.company.repository.ReviewDAO;

/**
* CheckReview 서블릿은 관리자가 회사 리뷰를 조회하고 승인 또는 거부할 수 있는 기능을 제공합니다.
*/
@WebServlet("/admin/review/checkreview.do")
public class CheckReview extends HttpServlet {

	 /**
	    * doGet 메서드는 전체 리뷰 목록을 조회하여 JSP 페이지로 전달합니다.
	    *
	    * @param req  HttpServletRequest 객체
	    * @param resp HttpServletResponse 객체
	    * @throws ServletException
	    * @throws IOException
	    */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String lv = (String) session.getAttribute("lv");

        ReviewDAO rdao = new ReviewDAO();
        ArrayList<ReviewDTO> list = rdao.getAllReviews();

        boolean isAdmin = lv != null && lv.equals("2"); // 관리자 여부 확인 로직

        if (isAdmin) {
            req.setAttribute("list", list);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/review/review.jsp");
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("/main.do");
        }
        
        rdao.close();
    }

    /**
     * doPost 메서드는 리뷰 승인 또는 거부 요청을 처리합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String lv = (String) session.getAttribute("lv");

        String action = req.getParameter("action");
        String cp_rv_seq = req.getParameter("cp_rv_seq");

        ReviewDAO rdao = new ReviewDAO();
        ReviewDTO rdto = rdao.getReviewByCpRvSeq(cp_rv_seq);
        ArrayList<ReviewDTO> list = rdao.getAllReviews();
        boolean success = false;
        boolean isAdmin = lv != null && lv.equals("2"); // 관리자 여부 확인 로직

        if (rdto != null && isAdmin) {
        	 if (action != null && action.equals("approve")) {
                 success = rdao.updateReviewConfirm(cp_rv_seq, 1); // 리뷰 승인
             } else if (action != null && action.equals("reject")) {
                 String rejectReason = req.getParameter("rj_reason");
                 success = rdao.insertRejectReview(cp_rv_seq, rejectReason) && rdao.updateReviewConfirm(cp_rv_seq, 2);
             }
         }
        
        rdao.close();

         resp.setContentType("application/json");
         resp.setCharacterEncoding("UTF-8");
         resp.getWriter().print("{\"success\": " + success + "}");
     }
    
}
package com.good.admin;

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

@WebServlet("/admin/review/checkreview.do")
public class CheckReview extends HttpServlet {

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
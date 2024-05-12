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



@WebServlet("/admin/review/review.do")
public class Review extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String lv = (String) session.getAttribute("lv");

        ReviewDAO rdao = new ReviewDAO();
        ArrayList<ReviewDTO> list = rdao.getAllReviews();

        req.setAttribute("list", list);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/review/review.jsp");
        dispatcher.forward(req, resp);
    }
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
    }
}


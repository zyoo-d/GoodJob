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

import com.good.company.model.ReviewDTO;
import com.good.company.repository.ReviewDAO;



@WebServlet("/admin/review/review.do")
public class Review extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String lv = (String) session.getAttribute("lv");
        String action = req.getParameter("action");
        String cp_rv_seq = req.getParameter("cp_rv_seq");
        

        //if (lv != null && lv.equals("2")) {
            ReviewDAO rdao = new ReviewDAO();
            ArrayList<ReviewDTO> list = rdao.getAllReviews();
            if (action != null && action.equals("approve")) {
                rdao.updateReviewConfirm(cp_rv_seq, 1); // 리뷰 승인
            } else if (action != null && action.equals("reject")) {
                rdao.updateReviewConfirm(cp_rv_seq, 2); // 리뷰 거절
            }
            req.setAttribute("list", list);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/review/review.jsp");
            dispatcher.forward(req, resp);
        //}
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] cp_rv_seqs = req.getParameterValues("cp_rv_seqs");
        
        ReviewDAO rdao = new ReviewDAO();
        boolean success = rdao.allApprove(cp_rv_seqs);
        
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("{\"success\": " + success + "}");
    }
}


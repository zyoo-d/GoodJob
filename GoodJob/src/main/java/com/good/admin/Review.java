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
//
        
 //       
        //if (lv != null && lv.equals("2")) {
            ReviewDAO rdao = new ReviewDAO();
            ArrayList<ReviewDTO> list = rdao.getAllReviews();

            req.setAttribute("list", list);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/review/review.jsp");
            dispatcher.forward(req, resp);
        //}
    }
}


package com.good.company;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.company.repository.ReviewDAO;


   
@WebServlet("/user/company/review/delreview.do")
public class DelReview extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cp_seq = req.getParameter("cp_seq");
        String cp_rv_seq = req.getParameter("cp_rv_seq");
        
        ReviewDAO rdao = new ReviewDAO();
        rdao.deleteReview(cp_seq, cp_rv_seq);
        
        resp.sendRedirect("/good/user/company/companyview.do?cp_seq=" + cp_seq);
    }
}

		
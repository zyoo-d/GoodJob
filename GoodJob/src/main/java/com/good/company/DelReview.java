package com.good.company;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.company.repository.ReviewDAO;


/**
 * 리뷰 삭제 서블릿
 * 
 * 이 서블릿은 사용자가 작성한 리뷰를 삭제하는 기능을 제공합니다.
 * 요청 파라미터로 전달된 리뷰 시퀀스(cp_rv_seq)를 사용하여 해당 리뷰를 데이터베이스에서 삭제합니다.
 * 삭제 후에는 사용자의 리뷰 목록 페이지로 리다이렉트합니다.
 */   
@WebServlet("/user/company/review/delreview.do")
public class DelReview extends HttpServlet {

	/**
	 * 리뷰 삭제 서블릿
	 * 
	 * 이 서블릿은 사용자가 작성한 리뷰를 삭제하는 기능을 제공합니다.
	 * 요청 파라미터로 전달된 리뷰 시퀀스(cp_rv_seq)를 사용하여 해당 리뷰를 데이터베이스에서 삭제합니다.
	 * 삭제 후에는 사용자의 리뷰 목록 페이지로 리다이렉트합니다.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
        String cp_rv_seq = req.getParameter("cp_rv_seq");
        
        ReviewDAO rdao = new ReviewDAO();
        rdao.deleteReview(cp_rv_seq);
        
        resp.sendRedirect("/good/user/mypage/myreview.do");
    }
	
}

		
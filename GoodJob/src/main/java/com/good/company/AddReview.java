package com.good.company;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.board.repository.BoardDAO;
import com.good.company.model.CompanyDTO;
import com.good.company.model.ReviewDTO;
import com.good.company.repository.CompanyDAO;
import com.good.company.repository.ReviewDAO;

@WebServlet("/user/company/review/addreview.do")
public class AddReview extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		HttpSession session = req.getSession();
		
		String cp_seq = req.getParameter("cp_seq");
		String page = req.getParameter("page");
	
		CompanyDAO dao = new CompanyDAO();
		CompanyDTO dto = dao.get(cp_seq);
		
		req.setAttribute("cp_seq",cp_seq);
		req.setAttribute("page",page);
		req.setAttribute("dto", dto);
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/company/review/addreview.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		HttpSession session = req.getSession();
		
		String id = (String)session.getAttribute("id");
		
		double salary_score = Double.parseDouble(req.getParameter("salary_score"));
		double welfare_score= Double.parseDouble(req.getParameter("welfare_score"));
		double lngvt_score = Double.parseDouble(req.getParameter("lngvt_score"));
		double culture_score= Double.parseDouble(req.getParameter("culture_score"));
		double growth_score=  Double.parseDouble(req.getParameter("growth_score"));
		
		String linereview = req.getParameter("linereview");
		String good = req.getParameter("good");
		String bad = req.getParameter("bad");
		String tag = req.getParameter("tag_keyword");
		int cp_rv_confirm = Integer.parseInt(req.getParameter("cp_rv_confirm"));
		String cp_seq = req.getParameter("cp_seq");
		
		
		ReviewDAO dao = new ReviewDAO();
		ReviewDTO dto = new ReviewDTO();
		
		dto.setSalary_score(salary_score);
		dto.setWelfare_score(welfare_score);
		dto.setLngvt_score(lngvt_score);
		dto.setCulture_score(culture_score);
		dto.setGrowth_score(growth_score);
		dto.setLinereview(linereview);
		dto.setGood(good);
		dto.setBad(bad);
		dto.setId(id);
		dto.setCp_rv_confirm(cp_rv_confirm);
		dto.setCp_seq(cp_seq);
		
		
		
		
		
		///TODO 해시태그작업
				
		
		
		
	}
}

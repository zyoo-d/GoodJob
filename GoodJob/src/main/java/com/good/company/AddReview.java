package com.good.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.good.alert.Alert;
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
		String id = (String)session.getAttribute("id");
		
		if(id == null || id.equals("")) {
			// 로그인되어 있지 않은 경우 로그인 페이지로 이동
			resp.sendRedirect("/good/user/signin.do"); 
			return;
		}
		
		String cp_seq = req.getParameter("cp_seq");
		String page = req.getParameter("page");

		CompanyDAO dao = new CompanyDAO();
		CompanyDTO dto = dao.get(cp_seq);

		ReviewDAO rdao = new ReviewDAO();
		ArrayList<String> showTagList = rdao.showTagList();
		ArrayList<String> showComTagList = rdao.showComTagList();


		req.setAttribute("cp_seq", cp_seq);
		req.setAttribute("page", page);
		req.setAttribute("dto", dto);
		req.setAttribute("showTagList", showTagList);
		req.setAttribute("showComTagList", showComTagList);


		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/company/review/addreview.jsp");
		dispatcher.forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");
		
		double salary_score = Double.parseDouble(req.getParameter("salary_score"));
		double welfare_score = Double.parseDouble(req.getParameter("welfare_score"));
		double stability_score = Double.parseDouble(req.getParameter("stability_score"));
		double culture_score = Double.parseDouble(req.getParameter("culture_score"));
		double growth_score = Double.parseDouble(req.getParameter("growth_score"));

		String linereview = req.getParameter("linereview");
		String good = req.getParameter("good");
		String bad = req.getParameter("bad");
		
		ReviewDAO rdao = new ReviewDAO();
		
		ReviewDTO dto = new ReviewDTO();
		
		//String cp_rv_seq = rdao.getCp_rv_seq();
		String cp_seq = req.getParameter("cp_seq");
		if (cp_seq == null || cp_seq.isEmpty()) {
		    
		    resp.setContentType("text/html; charset=UTF-8");
		    PrintWriter out = resp.getWriter();
		    out.println("<script>alert('잘못된 접근입니다. 기업 정보가 선택되지 않았습니다.'); location.href='/good/user/company/review/addreview.do';</script>");
		    out.flush();
		    return;
		}
		

		//dto.setCp_rv_seq(cp_rv_seq+1);
		dto.setSalary_score(salary_score);
		dto.setWelfare_score(welfare_score);
		dto.setStability_score(stability_score);
		dto.setCulture_score(culture_score);
		dto.setGrowth_score(growth_score);
		dto.setLinereview(linereview);
		dto.setGood(good);
		dto.setBad(bad);
		dto.setId(id);
		dto.setCp_rv_confirm(0);
		dto.setCp_seq(cp_seq);

		// 리뷰 등록
		int result = rdao.addReview(dto);
		
		if (result == 1) {
			resp.sendRedirect("/good/user/company/companyview.do?cp_seq=" + cp_seq);
		} else {
			resp.setCharacterEncoding("UTF-8");
			PrintWriter writer = resp.getWriter();
			writer.println("<script>alert('리뷰 등록에 실패했습니다.'); location.href='/good/user/company/review/addreview.do?cp_seq=" + cp_seq + "';</script>");
			writer.close();
		}
	}
}

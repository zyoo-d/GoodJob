package com.good.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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

@WebServlet("/user/company/companyview.do")
public class DetailCompany extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		String cp_seq = req.getParameter("cp_seq");
		String word = req.getParameter("word");
		String page = req.getParameter("page");
		String search = req.getParameter("search");
		String hiring = req.getParameter("hiring");
		
		CompanyDAO dao = new CompanyDAO();
		CompanyDTO dto = dao.get(cp_seq);
		
/*		
		//총매출액
	    long sales = dto.getFnc_sales();
	    if (sales >= 100000000) { //(단위:억)
	    	dto.setFnc_sales((long)(Math.round((double)sales/100000000)));
	    }else if(sales >= 10000000) { //(단위:천만)
	    	dto.setFnc_sales((long)(Math.round((double)sales/10000000)));
	    }else if(sales >= 1000000) { // (단위:백만)
	    	dto.setFnc_sales((long)(Math.round((double)sales/1000000)));
	    }else if(sales >= 100000) { // (단위:십만)
	    	dto.setFnc_sales((long)(Math.round((double)sales/100000)));
	    }else if(sales >= 10000) { // (단위:만)
	    	dto.setFnc_sales((long)(Math.round((double)sales/10000)));
	    }else {
	    	dto.setFnc_sales(sales); //(원)
	    }
		
	    //당기순이익
	    long ebit = dto.getFnc_ebit();
	    if (Math.abs(ebit) >= 100000000) { //(단위:억)
	    	dto.setFnc_sales((long)(Math.round((double)ebit/100000000)));
	    }else if(Math.abs(ebit) >= 10000000) { //(단위:천만)
	    	dto.setFnc_sales((long)(Math.round((double)ebit/10000000)));
	    }else if(Math.abs(ebit) >= 1000000) { // (단위:백만)
	    	dto.setFnc_sales((long)(Math.round((double)ebit/1000000)));
	    }else if(Math.abs(ebit) >= 100000) { // (단위:십만)
	    	dto.setFnc_sales((long)(Math.round((double)ebit/100000)));
	    }else if(Math.abs(ebit) >= 10000) { // (단위:만)
	    	dto.setFnc_sales((long)(Math.round((double)ebit/10000)));
	    }else {
	    	dto.setFnc_sales(sales); //(원)
	    }
*/	    
	    
		//평균연봉
	    int avg_salary = dto.getHire_avr_salary();
	    if(avg_salary==0) {
	    	dto.setHire_avr_salary(0);
	    }
	    dto.setHire_avr_salary((int)Math.round((float)avg_salary/10000));//(단위:만원)  
		
	    
	    //태그리스트출력
	    ReviewDAO rdao =  new ReviewDAO();
	    
	    ArrayList<ReviewDTO> listReview = rdao.listReview(cp_seq);
	    String cp_rv_seq ="";
    	String id ="";
    	
	    HashMap<String, String> tmap = new HashMap<>();
	    for(ReviewDTO rdto : listReview) {
	    	
	    	cp_rv_seq = rdto.getCp_rv_seq();
	    	id = rdto.getId();

	    	tmap.put("cp_seq",cp_seq );
	    	
	    	
	    }
	    
		
		//ArrayList<ReviewDTO> tagList = rdao.tagList(tmap);
		
		
		
	    
		req.setAttribute("dto", dto);
		req.setAttribute("word", word);
		req.setAttribute("page", page);
		req.setAttribute("listReview", listReview);
		//req.setAttribute("tagList", tagList);


		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/company/companyview.jsp");
		dispatcher.forward(req, resp);

	}
}
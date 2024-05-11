package com.good.company;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.company.model.CompanyDTO;
import com.good.company.model.ReviewDTO;
import com.good.company.repository.CompareDAO;

@WebServlet("/user/company/comparecompany.do")
public class CompareCompany extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String[] cp = req.getParameterValues("compareCp");
		String tag1 = cp[0];
		String tag2 = cp[1];
		String tag3 = cp[2];
		
		System.out.println(tag1 + ":" + tag2 + ":" + tag3);
		
		CompareDAO dao = new CompareDAO();
		
		
		HashMap<String, String> map = new HashMap<>();
		
		map.put("tag1", tag1);
		map.put("tag2", tag2);
		map.put("tag3", tag3);
		
		ArrayList<CompanyDTO> list = dao.getCompareInfo(map);

		for(CompanyDTO dto : list) {
			String date = dto.getFounded();
			if(date.equals("0000-00-00")) {
				date = "-";
				dto.setFounded(date);
			}
			
			 	int hireAvrSalary = dto.getHire_avr_salary();
	            int formattedHireAvrSalary = (int) Math.round((double) hireAvrSalary / 10000); 
	            dto.setHire_avr_salary(formattedHireAvrSalary);

	            long fncEbit = dto.getFnc_ebit();
	            long formattedFncEbit = Math.round((double) fncEbit / 10000000); 
	            dto.setFnc_ebit(formattedFncEbit); 
	            
	            long fncSales = dto.getFnc_sales();
	            long formattedFncSales = Math.round((double) fncSales / 10000000);
	            dto.setFnc_sales(formattedFncSales);
	            
	            long fncIncome = dto.getFnc_income();
	            long formattedFncIncome = Math.round((double) fncIncome / 10000000);
	            dto.setFnc_income(formattedFncIncome);

		}
		
		//리뷰 점수
		ArrayList<ReviewDTO> rvlist = dao.getReviewscore(map);
		//재무 정보
		ArrayList<Long>[] flist = dao.getCompanyFinance(map);
		System.out.println(list.size());
		dao.close();
		

		req.setAttribute("list", list);
		req.setAttribute("flist", flist);
		req.setAttribute("map", map);
		req.setAttribute("rvlist", rvlist);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/company/comparecompany.jsp");
		dispatcher.forward(req, resp);

	}
	


}
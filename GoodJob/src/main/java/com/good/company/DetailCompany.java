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


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.good.company.model.CompanyDTO;
import com.good.company.model.RecruitDTO;
import com.good.company.model.ReviewDTO;
import com.good.company.repository.CompanyDAO;
import com.good.company.repository.RecruitDAO;
import com.good.company.repository.ReviewDAO;

@WebServlet("/user/company/companyview.do")
public class DetailCompany extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//HttpSession session = req.getSession();
		
		String cp_seq = req.getParameter("cp_seq");
		String word = req.getParameter("word");
		String page = req.getParameter("page");
		String search = req.getParameter("search");
		String hiring = req.getParameter("hiring");
		
		CompanyDAO dao = new CompanyDAO();
		CompanyDTO dto = dao.get(cp_seq);
		
		
		
		//총매출액
	    /*long sales = dto.getFnc_sales();
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
			    	dto.setFnc_ebit((long)(Math.round((double)ebit/100000000)));
			    }else if(Math.abs(ebit) >= 10000000) { //(단위:천만)
			    	dto.setFnc_ebit((long)(Math.round((double)ebit/10000000)));
			    }else if(Math.abs(ebit) >= 1000000) { // (단위:백만)
			    	dto.setFnc_ebit((long)(Math.round((double)ebit/1000000)));
			    }else if(Math.abs(ebit) >= 100000) { // (단위:십만)
			    	dto.setFnc_ebit((long)(Math.round((double)ebit/100000)));
			    }else if(Math.abs(ebit) >= 10000) { // (단위:만)
			    	dto.setFnc_ebit((long)(Math.round((double)ebit/10000)));
			    }else {
			    	dto.setFnc_ebit(ebit); //(원)
			    }
    */
	    
		//평균연봉
	    int avg_salary = dto.getHire_avr_salary();
	    
	    if(avg_salary==0) {
	    	dto.setHire_avr_salary(0);
	    }
	    
	    dto.setHire_avr_salary((int)Math.round((float)avg_salary/10000));//(단위:만원)  
		
	    //업계평균연봉
	    int idst_avg_salary = dao.getIdstSalary(dto.getIdst_code());
	    dto.setIdst_avg_salary(idst_avg_salary);
	    
	    //기업재무정보
	    
	    //리뷰조회
	    ReviewDAO rdao =  new ReviewDAO();
	    ArrayList<ReviewDTO> listReview = rdao.listReview(cp_seq);
	    //System.out.println("Number of reviews fetched: " + listReview.size());
	    
	    //태그출력
	    ReviewDAO tdao =  new ReviewDAO();
	    //ArrayList<ReviewDTO> ComTaglist = tdao.tagList(cp_seq);
    	
	    //기업직무정보
	    RecruitDAO jdao = new RecruitDAO();
	    ArrayList<String> comJobList = jdao.comJob(cp_seq);
	   
	    
	    //채용공고목록
	    RecruitDAO rcdao = new RecruitDAO(); 
	    ArrayList<RecruitDTO> comRecruitList = rcdao.comRecruitList(cp_seq);
	    
	    for (RecruitDTO rdto : comRecruitList) {
	    	//날짜 조작
	    	rdto.setEnddate(rdto.getEnddate().substring(0, 10));
	    	//주소 조작
	    	String address = rdto.getCp_address();
			if(address.contains("서울특별시")) {
				address = address.replaceAll("서울특별시", "서울");
			}
			int firstSpaceIndex = address.indexOf(' '); // 첫 번째 공백의 위치
		    int secondSpaceIndex = address.indexOf(' ', firstSpaceIndex + 1); // 두 번째 공백의 위치
		    address = address.substring(0, secondSpaceIndex);
		    rdto.setCp_address(address);

	    }
	    
	    ArrayList<CompanyDTO> fnc_list = dao.getCompanyFinance(cp_seq);
	    dto.setFnc_list(dao.getCompanyFinance(cp_seq));
	    if (fnc_list != null && !fnc_list.equals("")&& !fnc_list.equals("[]")) {
			try {
				
				
				JSONParser parser = new JSONParser();				
				JSONArray arr = (JSONArray)fnc_list; //배열 > JSONArray
				
				for (Object obj : arr) { //arr(JSONObject)이지만 바로 가져오면 오류나서 일단 Object로 가져옴
					JSONObject fncObj = (JSONObject)obj;
					String finance = (String)fncObj.get("value");
				
			        
				}
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
	  
		req.setAttribute("dto", dto);
		req.setAttribute("word", word);
		req.setAttribute("page", page);
		req.setAttribute("listReview", listReview);
		req.setAttribute("comRecruitList",comRecruitList);
		req.setAttribute("search", search);
		req.setAttribute("hiring", hiring);
		//req.setAttribute("ComTaglist", ComTaglist);
		req.setAttribute("comJobList",comJobList);
		
		
		
		
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/company/companyview.jsp");
		dispatcher.forward(req, resp);

	}
	
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		String cp_seq = req.getParameter("cp_seq");
		RecruitDAO dao =  new RecruitDAO();
		
	
	
	}
}
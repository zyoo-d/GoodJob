package com.good.company;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.test.util.DBUtil;

	@WebServlet("/user/company/companylist.do")
	public class ListCompany extends HttpServlet {

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			

			//페이징
			String page = req.getParameter("page");
			
			int nowPage = 0;//현재페이지
			int totalCount=0;//총 게시물 수
			int pageSize=10;//출력 게시물 수
			int totalPage=0;//총페이지
			int begin=0; //페이지 시작
			int end=0;	//페이지 끝
			int n = 0; //페이지바
			int loop = 0;
			int blockSize = 5;
			
			if (page == null || page.equals("")) {
				nowPage = 1;
			} else {
				nowPage = Integer.parseInt(page);
			}
			
			begin = ((nowPage-1)*pageSize)+1;
			end = begin + pageSize -1;
			
			

			// 검색 기록 가져오기
			String column = (req.getParameter("column") != null ? req.getParameter("column") : "");
			String word = (req.getParameter("word") != null ? req.getParameter("word") : "");
			String search = "n"; // 목록보기(n), 검색하기(y)

			if ((column == null && word == null) || word.equals("")) {
				search = "n";
			} else {
				search = "y";
			}
			
			
			String hiring = req.getParameter("hiring");
			if(hiring != null) {
				hiring = "y";
			}else {
				hiring = "n";
			}
			
			
			HashMap<String, String> map = new HashMap<>();

			map.put("search", search);
			map.put("column", column);
			map.put("word", word);
			map.put("begin", begin + "");
			map.put("end", end + "");
			map.put("hiring",hiring);
			
			
			HttpSession session = req.getSession();
			
			//목록 출력
			CompanyDAO dao = new CompanyDAO();
			ArrayList<CompanyDTO> comListInfo = dao.comListInfo(map);
			
			String unit="";
			
			for (CompanyDTO dto : comListInfo) {
				
				//주소 
				String address = dto.getCp_address();
				if(address.contains("서울특별시")) {
					address = address.replaceAll("서울특별시", "서울");
				}
				int firstSpaceIndex = address.indexOf(' '); // 첫 번째 공백의 위치
			    int secondSpaceIndex = address.indexOf(' ', firstSpaceIndex + 1); // 두 번째 공백의 위치
			    address = address.substring(0, secondSpaceIndex);
			    dto.setCp_address(address);
				
			    //총매출액
			    long sales = dto.getFnc_sales();
			    if(sales >= 10000000) { //(단위:천만)
			    	sales = (long)(Math.round((double)sales/10000000));
			    	unit="천만원";
			    }else if(sales >= 1000000) { // (단위:백만)
			    	sales = (long)(Math.round((double)sales/1000000));
			    	unit="백만원";
			    }else if(sales >= 100000) { // (단위:십만)
			    	sales = (long)(Math.round((double)sales/100000));
			    	unit="십만원";
			    }else if(sales >= 10000) { // (단위:만)
			    	sales = (long)(Math.round((double)sales/10000));
			    	unit="만원";
			    }else {
			    	unit="원";//(원)
			    }
			    dto.setFnc_sales(sales);
			    dto.setUnit(unit);
			    
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
			    
			    
				//평균연봉
			    int avg_salary = dto.getHire_avr_salary();
			    dto.setHire_avr_salary((int)Math.round((float)avg_salary/10000));//(단위:만원)  

			}
			
			//총게시물수
			totalCount = dao.countCompanys();
			int searchTotalCount = dao.searchCompanyCount(map);			
			totalPage = (int) Math.ceil((double) searchTotalCount / pageSize);

		    //태그리스트출력
		    ReviewDAO rdao =  new ReviewDAO();
		    ArrayList<ReviewDTO> ComTaglist = rdao.tagList();
			
			
			// 페이지 바 작업
			StringBuilder sb = new StringBuilder();


			loop = 1; // 루프 변수(10바퀴)
			n = ((nowPage - 1) / blockSize) * blockSize + 1; // 페이지 번호 역할

			// 이전 5페이지
			if (n == 1) {
				sb.append(
						"<li class='page-item z-custom'><a class='page-link' href='#!'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_left</span></a></li>");
				sb.append(
						"<li class='page-item z-custom'><a class='page-link' href='#!'><span class='material-symbols-outlined paging-icon z-custom'>navigate_before</span></a></li>");
			} else if (n <= 5) {
				sb.append(
						"<li class='page-item z-custom'><a class='page-link' href='#!'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_left</span></a></li>");
				sb.append(String.format(
						"<li class='page-item z-custom'><a class='page-link' href='/good/user/company/companylist.do?page=%d&hiring=%s&word=%s'><span class='material-symbols-outlined paging-icon z-custom'>navigate_before</span></a></li>",
						n - 1, hiring, word));
			} else if (n > 5) {
				sb.append(String.format(
						"<li class='page-item z-custom'><a class='page-link' href='/good/user/company/companylist.do?page=%d&hiring=%s&word=%s'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_left</span></a></li>",
						n - 5, hiring, word));
				sb.append(String.format(
						"<li class='page-item z-custom'><a class='page-link' href='/good/user/company/companylist.do?page=%d&hiring=%s&word=%s'><span class='material-symbols-outlined paging-icon z-custom'>navigate_before</span></a></li>",
						n - 1, hiring, word));
			}

			while (!(loop > blockSize || n > totalPage)) {
				if (n == nowPage) {

					sb.append(String.format(
							"<li class='page-item z-custom'><a class='page-link' href='#!' style='background-color: #6777EE; color: #FFF; border-color: #6777EE;'>%d</a></li>",
							n));
				} else {
					sb.append(String.format(
							"<li class='page-item z-custom'><a class='page-link' href='/good/user/company/companylist.do?page=%d&hiring=%s&word=%s'>%d</a></li>",
							n, hiring, word, n));
				}
				loop++;
				n++;
			}

			// 다음 5페이지
			if (n >= totalPage) {
				sb.append(
						"<li class='page-item z-custom'><a class='page-link' href='#!'><span class='material-symbols-outlined paging-icon z-custom'>navigate_next</span></a></li>");
				sb.append(
						"<li class='page-item z-custom'><a class='page-link' href='#!'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_right</span></a></li>");
			} else if (n >= totalPage - 5) {
				sb.append(String.format(
						"<li class='page-item z-custom'><a class='page-link' href='/good/user/company/companylist.do?page=%d&hiring=%s&word=%s'><span class='material-symbols-outlined paging-icon z-custom'>navigate_next</span></a></li>",
						n, hiring, word));
				sb.append(
						"<li class='page-item z-custom'><a class='page-link' href='#!'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_right</span></a></li>");
			} else {
				sb.append(String.format(
						"<li class='page-item z-custom'><a class='page-link' href='/good/user/company/companylist.do?page=%d&hiring=%s&word=%s'><span class='material-symbols-outlined paging-icon z-custom'>navigate_next</span></a></li>",
						n, hiring, word));
				sb.append(String.format(
						"<li class='page-item z-custom'><a class='page-link' href='/good/user/company/companylist.do?page=%d&hiring=%s&word=%s'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_right</span></a></li>",
						n + 5, hiring, word));
			}
			
			
			req.setAttribute("comListInfo" , comListInfo);
			req.setAttribute("map" , map); //페이지 begin. end hiring
			req.setAttribute("ComTaglist", ComTaglist);
			
			
			
			//페이징
			req.setAttribute("nowPage", nowPage); //페이지 번호
			req.setAttribute("totalCount", totalCount);
			req.setAttribute("searchTotalCount", searchTotalCount);
			req.setAttribute("totalPage", totalPage); //페이지 번호
			req.setAttribute("pagebar", sb.toString()); //페이지 바 작업

			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/company/companylist.jsp");
			dispatcher.forward(req, resp);
			
			
		}
			 
	
		

}

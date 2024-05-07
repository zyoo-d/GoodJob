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
import com.good.company.repository.CompanyDAO;

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
			
			//기업명 검색
			String column = req.getParameter("column");
			String word = req.getParameter("word");
			String search = "n"; //(y:검색후)
			
			if((column == null && word == null) || (word.equals(""))) {
				search = "n";
			} else {
				search = "y";
			}
			
			HashMap<String,String> map = new HashMap<String,String>();
			if(column == null) column="";
			if(word == null) word="";
			
			map.put("search", search);
			map.put("column", column);
			map.put("word", word);
			
			map.put("begin", begin+"");
			map.put("end", end+"");
			
			HttpSession session = req.getSession();
			
			//목록 출력
			CompanyDAO dao = new CompanyDAO();
			ArrayList<CompanyDTO> comListInfo = dao.comListInfo(map);
			
			
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
			    
			    
				//평균연봉
			    int avg_salary = dto.getHire_avr_salary();
			    dto.setHire_avr_salary((int)Math.round((float)avg_salary/10000));//(단위:만원)  
			    
			    //이미지
			    String comlogo = dto.getImage();
			    if(comlogo == null) {
			    	comlogo = "/good/asset/img/logo/default.jpg";
			    }else {
			    	comlogo.replaceAll("images","img");
			    }
			    
			    
			}
			
			if (search =="y") {
				totalCount = dao.searchCompanyCount(map);
			} else {
				totalCount = dao.countCompanys();
			}
			
			//총페이지수
			totalPage = (int)Math.ceil((double)totalCount/pageSize);
				
			
			//페이지바 작업
			StringBuilder sb = new StringBuilder();
			loop = 1;
			n = ((nowPage - 1) / blockSize) * blockSize + 1; //페이지 번호 역할
			
			System.out.println("확인: " + column);
			
			
			//이전 5페이지
			if (n == 1) {
				sb.append(String.format(" <a href='#!'>[이전 %d페이지]</a> ", blockSize));
			} else {
				sb.append(String.format(" <a href='/toy/board/list.do?page=%d&column=%s&word=%s'>[이전 %d페이지]</a> ", n - 1, column, word, blockSize));
			}
			
			
			
			while (!(loop > blockSize || n > totalPage)) {
				
				if (n == nowPage) {
					sb.append(String.format(" <a href='#!' style='color: tomato;'>%d</a> ", n));
				} else {
					sb.append(String.format(" <a href='/toy/board/list.do?page=%d&column=%s&word=%s'>%d</a> ", n, column, word, n));
				}
				
				loop++;
				n++;
			}
			
			
			//다음 5페이지
			if (n >= totalPage) {
				sb.append(String.format(" <a href='#!'>[다음 %d페이지]</a> ", blockSize));
			} else {
				sb.append(String.format(" <a href='/toy/board/list.do?page=%d&column=%s&word=%s'>[다음 %d페이지]</a> ", n, column, word, blockSize));
			}
			
			
			req.setAttribute("comListInfo" , comListInfo);
			req.setAttribute("map" , map); //페이지 begin. end
			
			//페이징
			req.setAttribute("nowPage", nowPage); //페이지 번호
			req.setAttribute("totalCount", totalCount); //페이지 번호
			req.setAttribute("totalPage", totalPage); //페이지 번호
			req.setAttribute("pagebar", sb.toString()); //페이지 바 작업

			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/company/companylist.jsp");
			dispatcher.forward(req, resp);
			
			
		}
		
	
		

}

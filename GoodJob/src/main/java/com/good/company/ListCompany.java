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

import com.good.company.model.CompanyDTO;
import com.good.company.repository.CompanyDAO;

/**
 * 기업 목록 조회 서블릿
 * 
 * 이 서블릿은 기업 목록을 조회하고 페이징 처리를 수행합니다.
 * 검색, 정렬, 필터링 등의 기능을 포함하고 있습니다.
 * 조회된 기업 목록과 페이징 정보를 JSP 페이지로 전달합니다.
 */
@WebServlet("/user/company/companylist.do")
public class ListCompany extends HttpServlet {

	/**
     * GET 요청 처리 메소드
     * 
     * 기업 목록을 조회하고 페이징 처리를 수행합니다.
     * 검색, 정렬, 필터링 등의 기능을 포함하고 있습니다.
     * 조회된 기업 목록과 페이징 정보를 JSP 페이지로 전달합니다.
     * 
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외 발생 시
     * @throws IOException      입출력 예외 발생 시
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 페이징
		String page = req.getParameter("page");

		int nowPage = 0;// 현재페이지
		int totalCount = 0;// 총 게시물 수
		int pageSize = 10;// 출력 게시물 수
		int totalPage = 0;// 총페이지
		int begin = 0; // 페이지 시작
		int end = 0; // 페이지 끝
		int n = 0; // 페이지바
		int loop = 0;
		int blockSize = 5;

		if (page == null || page.equals("")) {
			nowPage = 1;
		} else {
			nowPage = Integer.parseInt(page);
		}

		begin = ((nowPage - 1) * pageSize) + 1;
		end = begin + pageSize - 1;

		// 검색 기록 가져오기
		String search = "n"; // 목록보기(n), 검색하기(y)
		String sort = req.getParameter("sort");
		String column = (req.getParameter("column") != null ? req.getParameter("column") : "");
		String word = (req.getParameter("word") != null ? req.getParameter("word") : "");
		String hiring = req.getParameter("hiring");
		
		if (sort == null || sort.isEmpty()) {
		    sort = "review"; // 기본 정렬 기준
		}
		if ((column == null && word == null) || word.equals("")) {
		    search = "n";
		} else {
		    search = "y";
		}
		
		if(hiring == null) {
		    hiring = "n";
		}
		
		
		String salary_seq = req.getParameter("salary_seq");
		String[] cp_address = req.getParameterValues("cp_address");
		String selectedLocations = "";
		if (cp_address != null && cp_address.length > 0) {
			selectedLocations = String.join(",", cp_address);
		}

		HashMap<String, String> map = new HashMap<>();
		map.put("search", search);
		map.put("column", column);
		map.put("word", word);
		map.put("begin", begin + "");
		map.put("end", end + "");
		map.put("hiring", hiring);
		map.put("sort", sort);
		map.put("salary_seq", salary_seq);
		map.put("cp_address", selectedLocations);


		// 목록 출력
		CompanyDAO dao = new CompanyDAO();
		ArrayList<CompanyDTO> comListInfo = dao.comListInfo(map);
		
		
		String unit = "";
		
		for (CompanyDTO dto : comListInfo) {

			// 주소
			String address = dto.getCp_address();
			if (address.contains("서울특별시")) {
				address = address.replaceAll("서울특별시", "서울");
			}
			int firstSpaceIndex = address.indexOf(' '); // 첫 번째 공백의 위치
			int secondSpaceIndex = address.indexOf(' ', firstSpaceIndex + 1); // 두 번째 공백의 위치
			address = address.substring(0, secondSpaceIndex);
			dto.setCp_address(address);
		

			// 평균연봉
			int avg_salary = dto.getHire_avr_salary();
			
			// 태그리스트출력
			ArrayList<String> topTags = dao.getTopTagsByCpSeq(dto.getCp_seq());
			dto.setTag_list(topTags);
		}
		
		// 총게시물수
		totalCount = dao.getCompanyCount(map);
		totalPage = (int) Math.ceil((double) totalCount / pageSize);

		
		// 페이지 바 작업
		StringBuilder sb = new StringBuilder();

		loop = 1; // 루프 변수(10바퀴)
		n = ((nowPage - 1) / blockSize) * blockSize + 1; // 페이지 번호 역할

		// 이전 5페이지
		if (n == 1) {
		    sb.append("<li class='page-item z-custom'><a class='page-link' href='#!'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_left</span></a></li>");
		    sb.append("<li class='page-item z-custom'><a class='page-link' href='#!'><span class='material-symbols-outlined paging-icon z-custom'>navigate_before</span></a></li>");
		} else if (n <= 5) {
		    sb.append("<li class='page-item z-custom'><a class='page-link' href='#!'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_left</span></a></li>");
		    sb.append(String.format("<li class='page-item z-custom'><a class='page-link' href='/good/user/company/companylist.do?page=%d&hiring=%s&word=%s&sort=%s'><span class='material-symbols-outlined paging-icon z-custom'>navigate_before</span></a></li>", n - 1, hiring, word, sort));
		} else if (n > 5) {
		    sb.append(String.format("<li class='page-item z-custom'><a class='page-link' href='/good/user/company/companylist.do?page=%d&hiring=%s&word=%s&sort=%s'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_left</span></a></li>", n - 5, hiring, word, sort));
		    sb.append(String.format("<li class='page-item z-custom'><a class='page-link' href='/good/user/company/companylist.do?page=%d&hiring=%s&word=%s&sort=%s'><span class='material-symbols-outlined paging-icon z-custom'>navigate_before</span></a></li>", n - 1, hiring, word, sort));
		}

		// 페이지 번호
		while (!(loop > blockSize || n > totalPage)) {
		    if (n == nowPage) {
		        sb.append(String.format("<li class='page-item z-custom'><a class='page-link' href='#!' style='background-color: #6777EE; color: #FFF; border-color: #6777EE;'>%d</a></li>", n));
		    } else {
		        sb.append(String.format("<li class='page-item z-custom'><a class='page-link' href='/good/user/company/companylist.do?page=%d&hiring=%s&word=%s&sort=%s'>%d</a></li>", n, hiring, word, sort, n));
		    }
		    loop++;
		    n++;
		}

		// 다음 5페이지
		if (n >= totalPage) {
		    sb.append("<li class='page-item z-custom'><a class='page-link' href='#!'><span class='material-symbols-outlined paging-icon z-custom'>navigate_next</span></a></li>");
		    sb.append("<li class='page-item z-custom'><a class='page-link' href='#!'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_right</span></a></li>");
		} else if (n >= totalPage - 5) {
		    sb.append(String.format("<li class='page-item z-custom'><a class='page-link' href='/good/user/company/companylist.do?page=%d&hiring=%s&word=%s&sort=%s'><span class='material-symbols-outlined paging-icon z-custom'>navigate_next</span></a></li>", n, hiring, word, sort));
		    sb.append("<li class='page-item z-custom'><a class='page-link' href='#!'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_right</span></a></li>");
		} else {
		    sb.append(String.format("<li class='page-item z-custom'><a class='page-link' href='/good/user/company/companylist.do?page=%d&hiring=%s&word=%s&sort=%s'><span class='material-symbols-outlined paging-icon z-custom'>navigate_next</span></a></li>", n, hiring, word, sort));
		    sb.append(String.format("<li class='page-item z-custom'><a class='page-link' href='/good/user/company/companylist.do?page=%d&hiring=%s&word=%s&sort=%s'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_right</span></a></li>", n + 5, hiring, word, sort));
		}

		dao.close();
		
		
		req.setAttribute("comListInfo", comListInfo);
		req.setAttribute("map", map); // 페이지 begin. end hiring
		req.setAttribute("hiring", hiring);
		
		// 페이징
		req.setAttribute("nowPage", nowPage); // 페이지 번호
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage); // 페이지 번호
		req.setAttribute("pagebar", sb.toString()); // 페이지 바 작업
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/company/companylist.jsp");
		dispatcher.forward(req, resp);

	}

}

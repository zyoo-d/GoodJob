package com.good.company.recruit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.company.model.RecruitDTO;
import com.good.company.repository.RecruitDAO;

/**
 * 채용 공고 목록을 처리하는 서블릿
 */
@WebServlet("/user/recruit/listrecruit.do")
public class ListRecruit extends HttpServlet{
	
	
	/**
     * GET 요청을 처리하는 메소드
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외
     * @throws IOException      입출력 예외
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 페이징
		String page = req.getParameter("page");
		int nowPage = 0; // 현재 페이지 번호
		int totalCount = 0; // 총 게시물 수
		int pageSize = 10; // 한 페이지에서 출력할 게시물 수
		int totalPage = 0; // 총 페이지 수
		int begin = 0; // 페이징 시작 위치
		int end = 0; // 페이징 끝 위치
		int n = 0; //
		int loop = 0; //
		int blockSize = 5; // 페이지 개수

		if (page == null || page.equals("")) {
			nowPage = 1;
		} else {
			nowPage = Integer.parseInt(page);
		}
		begin = ((nowPage - 1) * pageSize) + 1;
		end = begin + pageSize - 1;


		// 검색 기록 가져오기
		String column = (req.getParameter("column") != null ? req.getParameter("column") : "");
		

		//정렬 기준
		String sort = req.getParameter("sort");

		if (sort == null || sort.equals("")) {
			sort = "latest"; // 기본 정렬 순서
		}
		
		String orderBy = "";
		switch (sort) {
		case "latest":
			orderBy = "rcrt_seq desc";
			break;
		case "oldest":
			orderBy = "enddate asc";
			break;
		default:
			orderBy = "rcrt_seq desc";
			break;
		}
		
		String word = (req.getParameter("word") != null ? req.getParameter("word") : "");
		String edu_seq = req.getParameter("edu_seq");
		String career = req.getParameter("career");
		String salary_seq = req.getParameter("salary_seq");
		String[] cp_address = req.getParameterValues("cp_address");
		String search = "n";
		
		if(cp_address != null) {
			
			
			for(String s : cp_address) {
				
				System.out.println("지역 선호 선택: " + s);
			}
			
		}

		if ((word != null && !word.isEmpty()) ||
			    (edu_seq != null && !edu_seq.isEmpty()) ||
			    (career != null && !career.isEmpty()) ||
			    (salary_seq != null && !salary_seq.isEmpty()) ||
			    (cp_address != null && cp_address.length > 0)) {
			    search = "y"; // 검색 조건이 있으면 'y'로 설정
		}

		HashMap<String, Object> map = new HashMap<>();
		
		map.put("begin", begin + "");
		map.put("end", end + "");
		map.put("sort", orderBy);
		map.put("word", word);
		map.put("edu_seq", edu_seq);
		map.put("career", career);
		map.put("salary_seq", salary_seq);
		map.put("cp_address", cp_address);
		map.put("search", search);
		
		System.out.println("word "+ word );
		System.out.println("edu_seq " + edu_seq);
		System.out.println("career " + career);
		System.out.println("cp_address " + cp_address);
		System.out.println("salary_seq " + salary_seq);
		System.out.println("search " + search);
		System.out.println("sort" + sort);

		RecruitDAO dao = new RecruitDAO();
		
		ArrayList<RecruitDTO> list = dao.getList(map);

		// 총 게시물 수
		totalCount =  dao.getTotalCount(map);
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
			sb.append(String.format(
					"<li class='page-item z-custom'><a class='page-link' href='/good/user/recruit/listrecruit.do?page=%d&word=%s&sort=%s'><span class='material-symbols-outlined paging-icon z-custom'>navigate_before</span></a></li>",
					n - 1,  word, sort));
		} else if (n > 5) {
			sb.append(String.format(
					"<li class='page-item z-custom'><a class='page-link' href='/good/user/recruit/listrecruit.do?page=%d&word=%s&sort=%s'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_left</span></a></li>",
					n - 5,  word, sort));
			sb.append(String.format(
					"<li class='page-item z-custom'><a class='page-link' href='/good/user/recruit/listrecruit.do?page=%d&word=%s&sort=%s'><span class='material-symbols-outlined paging-icon z-custom'>navigate_before</span></a></li>",
					n - 1, word, sort));
		}

		while (!(loop > blockSize || n > totalPage)) {
			if (n == nowPage) {
				sb.append(String.format(
						"<li class='page-item z-custom'><a class='page-link' href='#!' style='background-color: #6777EE; color: #FFF; border-color: #6777EE;'>%d</a></li>",
						n));
			} else {
				sb.append(String.format(
						"<li class='page-item z-custom'><a class='page-link' href='/good/user/recruit/listrecruit.do?page=%d&word=%s&sort=%s'>%d</a></li>",
						n, word, sort, n));
			}
			loop++;
			n++;
		}

		// 다음 5페이지
		if (n >= totalPage) {
			sb.append("<li class='page-item z-custom'><a class='page-link' href='#!'><span class='material-symbols-outlined paging-icon z-custom'>navigate_next</span></a></li>");
			sb.append("<li class='page-item z-custom'><a class='page-link' href='#!'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_right</span></a></li>");
		} else if (n >= totalPage - 5) {
			sb.append(String.format(
					"<li class='page-item z-custom'><a class='page-link' href='/good/user/recruit/listrecruit.do?page=%d&word=%s&sort=%s'><span class='material-symbols-outlined paging-icon z-custom'>navigate_next</span></a></li>",
					n, word, sort));
			sb.append("<li class='page-item z-custom'><a class='page-link' href='#!'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_right</span></a></li>");
		} else {
			sb.append(String.format(
					"<li class='page-item z-custom'><a class='page-link' href='/good/user/recruit/listrecruit.do?page=%d&word=%s&sort=%s'><span class='material-symbols-outlined paging-icon z-custom'>navigate_next</span></a></li>",
					n,  word, sort));
			sb.append(String.format(
					"<li class='page-item z-custom'><a class='page-link' href='/good/user/recruit/listrecruit.do?page=%d&word=%s&sort=%s'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_right</span></a></li>",
					n + 5, word, sort));
		}

		req.setAttribute("list", list);
		req.setAttribute("map", map);
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("pagebar", sb.toString());
		
		dao.close();
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/recruit/listrecruit.jsp");
		dispatcher.forward(req, resp);
	}
	



}

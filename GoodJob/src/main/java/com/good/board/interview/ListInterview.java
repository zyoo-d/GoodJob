package com.good.board.interview;

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

import com.good.board.model.InterviewDTO;
import com.good.board.model.QnaBoardDTO;
import com.good.board.model.StudyDTO;
import com.good.board.repository.InterviewDAO;
import com.good.board.repository.QnaBoardDAO;

/*

서블릿 구현 클래스 ListInterview

이 서블릿은 "/board/interview/interview.do" URL 요청을 처리하는 역할을 합니다.
데이터베이스에서 인터뷰 목록을 검색하고, 페이지네이션 및 검색 기능을 적용하여
데이터를 JSP 페이지로 전달해 렌더링합니다.

*/
@WebServlet("/board/interview/interview.do")
public class ListInterview extends HttpServlet {

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
		String cp_name = (req.getParameter("cp_name") != null ? req.getParameter("cp_name") : "");
		String search = "n"; // 목록보기(n), 검색하기(y)

		if (cp_name == null || cp_name.equals("")) {
			search = "n";
		} else {
			search = "y";
		}

		HashMap<String, String> map = new HashMap<>();

		map.put("search", search);
		map.put("cp_name", cp_name);
		map.put("begin", begin + "");
		map.put("end", end + "");

		InterviewDAO dao = new InterviewDAO();
		ArrayList<InterviewDTO> list = dao.list(map);

		for (InterviewDTO dto : list) {
			// 날짜 자르기
			String date = dto.getITV_MEETDATE().substring(0, 10);
			dto.setITV_MEETDATE(date);
		}

		
		// 총 게시물 수
		totalCount = dao.getTotalCount(map);
		totalPage = (int) Math.ceil((double) totalCount / pageSize);

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
					"<li class='page-item z-custom'><a class='page-link' href='/good/board/interview/interview.do?page=%d&cp_name=%s'><span class='material-symbols-outlined paging-icon z-custom'>navigate_before</span></a></li>",
					n - 1, cp_name));
		} else if (n > 5) {
			sb.append(String.format(
					"<li class='page-item z-custom'><a class='page-link' href='/good/board/interview/interview.do?page=%d&cp_name=%s'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_left</span></a></li>",
					n - 5, cp_name));
			sb.append(String.format(
					"<li class='page-item z-custom'><a class='page-link' href='/good/board/interview/interview.do?page=%d&cp_name=%s'><span class='material-symbols-outlined paging-icon z-custom'>navigate_before</span></a></li>",
					n - 1, cp_name));
		}

		while (!(loop > blockSize || n > totalPage)) {
			if (n == nowPage) {
				sb.append(String.format(
						"<li class='page-item z-custom'><a class='page-link' href='#!' style='background-color: #6777EE; color: #FFF; border-color: #6777EE;'>%d</a></li>",
						n));
			} else {
				sb.append(String.format(
						"<li class='page-item z-custom'><a class='page-link' href='/good/board/interview/interview.do?page=%d&cp_name=%s'>%d</a></li>",
						n, cp_name, n));
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
					"<li class='page-item z-custom'><a class='page-link' href='/good/board/interview/interview.do?page=%d&cp_name=%s'><span class='material-symbols-outlined paging-icon z-custom'>navigate_next</span></a></li>",
					n, cp_name));
			sb.append(
					"<li class='page-item z-custom'><a class='page-link' href='#!'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_right</span></a></li>");
		} else {
			sb.append(String.format(
					"<li class='page-item z-custom'><a class='page-link' href='/good/board/interview/interview.do?page=%d&cp_name=%s'><span class='material-symbols-outlined paging-icon z-custom'>navigate_next</span></a></li>",
					n, cp_name));
			sb.append(String.format(
					"<li class='page-item z-custom'><a class='page-link' href='/good/board/interview/interview.do?page=%d&cp_name=%s'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_right</span></a></li>",
					n + 5, cp_name));
		}

		req.setAttribute("list", list);
		req.setAttribute("map", map);
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("pagebar", sb.toString());

		dao.close();

		// JSP 페이지로 포워딩
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/interview/interview.jsp");
		dispatcher.forward(req, resp);

	}
}

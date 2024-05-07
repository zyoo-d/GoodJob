package com.good.board.notice;

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

import com.good.board.model.NoticeDTO;
import com.good.board.repository.NoticeDAO;

@WebServlet("/board/notice.do")
public class ListNotice extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String page = req.getParameter("page");

		int nowPage = 0; // 현재 페이지 번호
		int totalCount = 0; // 총 게시물 수
		int pageSize = 10; // 한 페이지에서 출력할 게시물 수
		int totalPage = 0; // 총 페이지 수
		int begin = 0; // 페이지 시작 위치
		int end = 0; // 페이지 끝 위치
		int n = 0;
		int loop = 0;
		int blockSize = 10; // 페이지 개수

		if (page == null || page.equals("")) {
			nowPage = 1;
		} else {
			nowPage = Integer.parseInt(page);
		}

		begin = ((nowPage - 1) * pageSize) + 1;
		end = begin + pageSize - 1;

		String column = req.getParameter("column");
		String word = req.getParameter("word");
		
		String search = "n"; // 목록보기(n), 검색하기(y)

		if ((column == null && word == null) || (word.equals(""))) {
			search = "n";
		} else {
			search = "y";
		}

		HashMap<String, String> map = new HashMap<String, String>();

		if (column == null)
			column = "";
		if (word == null)
			word = "";

		map.put("search", search); // n, y
		map.put("column", column);
		map.put("word", word);

		map.put("begin", begin + "");
		map.put("end", end + "");

		HttpSession session = req.getSession();
		
		//조회수
		session.setAttribute("read", "n");
		
		NoticeDAO dao = new NoticeDAO();
		
		ArrayList<NoticeDTO> list = dao.list(map);
		
		totalCount = dao.getTotalCount(map);
		
		System.out.println("totalCount" + totalCount);
		
		totalPage = (int)Math.ceil((double)totalCount / pageSize);
		
		StringBuilder sb = new StringBuilder();


		loop = 1; // 루프 변수(5바퀴)
		n = ((nowPage - 1) / blockSize) * blockSize + 1; // 페이지 번호 역할

		// 이전 5페이지
		if (n == 1) {
		    sb.append("<li class='page-item z-custom'><a class='page-link' href='#!'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_left</span></a></li>");
		    sb.append("<li class='page-item z-custom'><a class='page-link' href='#!'><span class='material-symbols-outlined paging-icon z-custom'>navigate_before</span></a></li>");
		} else if (n <= 5) {
		    sb.append("<li class='page-item z-custom'><a class='page-link' href='#!'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_left</span></a></li>");
		    sb.append(String.format(
		        "<li class='page-item z-custom'><a class='page-link' href='/good/board/notice.do?page=%d&column=%s&word=%s'><span class='material-symbols-outlined paging-icon z-custom'>navigate_before</span></a></li>",
		        n - 1, column, word));
		} else if (n > 5) {
		    sb.append(String.format(
		        "<li class='page-item z-custom'><a class='page-link' href='/good/board/notice.do?page=%d&column=%s&word=%s'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_left</span></a></li>",
		        n - 5, column, word));
		    sb.append(String.format(
		        "<li class='page-item z-custom'><a class='page-link' href='/good/board/notice.do?page=%d&column=%s&word=%s'><span class='material-symbols-outlined paging-icon z-custom'>navigate_before</span></a></li>",
		        n - 1, column, word));
		}

		while (!(loop > blockSize || n > totalPage)) {
		    if (n == nowPage) {
		        sb.append(String.format(
		            "<li class='page-item z-custom'><a class='page-link' href='#!' style='background-color: #6777EE; color: #FFF; border-color: #6777EE;'>%d</a></li>",
		            n));
		    } else {
		        sb.append(String.format(
		            "<li class='page-item z-custom'><a class='page-link' href='/good/board/notice.do?page=%d&column=%s&word=%s'>%d</a></li>",
		            n, column, word, n));
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
		        "<li class='page-item z-custom'><a class='page-link' href='/good/board/notice.do?page=%d&column=%s&word=%s'><span class='material-symbols-outlined paging-icon z-custom'>navigate_next</span></a></li>",
		        n, column, word));
		    sb.append("<li class='page-item z-custom'><a class='page-link' href='#!'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_right</span></a></li>");
		} else {
		    sb.append(String.format(
		        "<li class='page-item z-custom'><a class='page-link' href='/good/board/notice.do?page=%d&column=%s&word=%s'><span class='material-symbols-outlined paging-icon z-custom'>navigate_next</span></a></li>",
		        n, column, word));
		    sb.append(String.format(
		        "<li class='page-item z-custom'><a class='page-link' href='/good/board/notice.do?page=%d&column=%s&word=%s'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_right</span></a></li>",
		        n + 5, column, word));
		}

		req.setAttribute("list", list);
		req.setAttribute("map", map);
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("pagebar", sb.toString());
		


		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/notice/noticeBoard.jsp");
		dispatcher.forward(req, resp);

	}
}

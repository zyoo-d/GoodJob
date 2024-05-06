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
		
		String sort = req.getParameter("sort");
		
		if (sort == null || sort.equals("")) {
		    sort = "latest"; // 기본 정렬 순서
		}
		
		String orderBy = "";
		
		switch (sort) {
		    case "RD":
		        orderBy = "nt_seq desc";
		        break;
		    case "EA":
		        orderBy = "nt_seq asc";
		        break;
		    default:
		        orderBy = "nt_seq desc";
		        break;
		}

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
		map.put("sort", orderBy);

		HttpSession session = req.getSession();

		// 조회수 관련
		session.setAttribute("read", "n");


		NoticeDAO dao = new NoticeDAO();
		ArrayList<NoticeDTO> list = dao.list(map);
		
		totalCount = dao.getTotalCount(map);
		totalPage = (int)Math.ceil((double)totalCount / pageSize);
		
		//페이지 바 작업
		StringBuilder sb = new StringBuilder();
		
		loop = 1; //루프 변수(10바퀴)
		n = ((nowPage - 1) / blockSize) * blockSize + 1;	//페이지 번호 역할

		if (n == 1) {
			sb.append(String.format(" <a href='#!'>[이전 %d페이지]</a> ", blockSize));
			
		} else {
			sb.append(String.format(" <a href='/toy/board/notice.do?page=%d&column=%s&word=%s'>[이전 %d페이지]</a> ", n - 1, column, word, blockSize));
		}
		
		while (!(loop > blockSize || n > totalPage)) {
			
			if (n == nowPage) {
				sb.append(String.format(" <a href='#!' style='color:tomato;'>%d</a> ", n));
				
			} else {
				sb.append(String.format(" <a href='/toy/board/notice.do?page=%d&column=%s&word=%s'>%d</a> ", n, column, word, n));				
			}

			loop++;
			n++;
		}
		
		//다음 10페이지
		if (n >= totalPage) {
			sb.append(String.format(" <a href='#!'>[다음 %d페이지]</a> ", blockSize));
		} else {
			sb.append(String.format(" <a href='/toy/board/notice.do?page=%d&column=%s&word=%s'>[다음 %d페이지]</a> ", n, column, word,  blockSize));
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

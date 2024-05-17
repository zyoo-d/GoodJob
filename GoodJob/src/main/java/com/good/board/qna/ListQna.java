package com.good.board.qna;

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

import com.good.board.model.QnaBoardDTO;
import com.good.board.model.StudyDTO;
import com.good.board.repository.QnaBoardDAO;

/**
 * QnA 게시글 목록을 조회하고 페이징 처리를 수행하는 서블릿 클래스입니다.
 * "/user/qna/listqna.do" URL 패턴에 매핑됩니다.
 */
@WebServlet("/user/qna/listqna.do")
public class ListQna extends HttpServlet {

	/**
     * GET 요청을 처리합니다.
     * QnA 게시글 목록을 조회하고 페이징 처리를 수행한 후, 목록 페이지로 전달합니다.
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
		
		switch(column) {
			case "title":
				column = "qna_title";
				break;
			case "content":
				column = "qna_content";
				break;
			case "name":
				column = "cp_name";
				break;
		}
		
		
		String word = (req.getParameter("word") != null ? req.getParameter("word") : "");
		String search = "n"; // 목록보기(n), 검색하기(y)
		
		//정렬 기준
		String sort = req.getParameter("sort");
		
		if (sort == null || sort.equals("")) {
		    sort = "latest"; // 기본 정렬 순서
		}
		
		String orderBy = "";
		switch (sort) {
		    case "latest":
		        orderBy = "qna_seq desc";
		        break;
		    case "oldest":
		        orderBy = "qna_seq asc";
		        break;
		    case "comments":
		        orderBy = "comment_count desc";
		        break;
		    default:
		        orderBy = "qna_seq desc";
		        break;
		}
		
		

		if ((column == null && word == null) || word.equals("")) {
			search = "n";
		} else {
			search = "y";
		}

		HashMap<String, String> map = new HashMap<>();

		map.put("search", search);
		map.put("column", column);
		map.put("word", word);
		map.put("begin", begin + "");
		map.put("end", end + "");
		map.put("sort", orderBy);

		// 조회수 관련
		HttpSession session = req.getSession();
		session.setAttribute("read", "n");

		QnaBoardDAO dao = new QnaBoardDAO();

		ArrayList<QnaBoardDTO> list = dao.listQna(map);

		for (QnaBoardDTO dto : list) {
			// 제목 자르기
			String subject = dto.getQna_title();
			if (subject.length() > 20) {
				subject = subject.substring(0, 20) + "..";
			}
			// 제목 > 태그 > 이스케이프
			subject = subject.replace(">", "&gt;").replace("<", "&lt;");
			dto.setQna_title(subject);

		}

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
		        "<li class='page-item z-custom'><a class='page-link' href='/good/user/qna/listqna.do?page=%d&column=%s&word=%s&sort=%s'><span class='material-symbols-outlined paging-icon z-custom'>navigate_before</span></a></li>",
		        n - 1, column, word, sort));
		} else if (n > 5) {
		    sb.append(String.format(
		        "<li class='page-item z-custom'><a class='page-link' href='/good/user/qna/listqna.do?page=%d&column=%s&word=%s&sort=%s'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_left</span></a></li>",
		        n - 5, column, word, sort));
		    sb.append(String.format(
		        "<li class='page-item z-custom'><a class='page-link' href='/good/user/qna/listqna.do?page=%d&column=%s&word=%s&sort=%s'><span class='material-symbols-outlined paging-icon z-custom'>navigate_before</span></a></li>",
		        n - 1, column, word, sort));
		}

		while (!(loop > blockSize || n > totalPage)) {
		    if (n == nowPage) {
		        sb.append(String.format(
		            "<li class='page-item z-custom'><a class='page-link' href='#!' style='background-color: #6777EE; color: #FFF; border-color: #6777EE;'>%d</a></li>",
		            n));
		    } else {
		        sb.append(String.format(
		            "<li class='page-item z-custom'><a class='page-link' href='/good/user/qna/listqna.do?page=%d&column=%s&word=%s&sort=%s'>%d</a></li>",
		            n, column, word, sort, n));
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
		        "<li class='page-item z-custom'><a class='page-link' href='/good/user/qna/listqna.do?page=%d&column=%s&word=%s&sort=%s'><span class='material-symbols-outlined paging-icon z-custom'>navigate_next</span></a></li>",
		        n, column, word, sort));
		    sb.append("<li class='page-item z-custom'><a class='page-link' href='#!'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_right</span></a></li>");
		} else {
		    sb.append(String.format(
		        "<li class='page-item z-custom'><a class='page-link' href='/good/user/qna/listqna.do?page=%d&column=%s&word=%s&sort=%s'><span class='material-symbols-outlined paging-icon z-custom'>navigate_next</span></a></li>",
		        n, column, word, sort));
		    sb.append(String.format(
		        "<li class='page-item z-custom'><a class='page-link' href='/good/user/qna/listqna.do?page=%d&column=%s&word=%s&sort=%s'><span class='material-symbols-outlined paging-icon z-custom'>keyboard_double_arrow_right</span></a></li>",
		        n + 5, column, word, sort));
		}

		req.setAttribute("list", list);
		req.setAttribute("map", map);
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("pagebar", sb.toString());
		
		dao.close();

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/qna/listqna.jsp");
		dispatcher.forward(req, resp);



	}

}

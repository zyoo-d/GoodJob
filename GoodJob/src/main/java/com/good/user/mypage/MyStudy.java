package com.good.user.mypage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.board.model.StudyDTO;
import com.good.board.repository.StudyDAO;

@WebServlet("/user/mypage/mystudy.do")
public class MyStudy extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");

		// 페이징
		String page = req.getParameter("page");
		int nowPage = 0; // 현재 페이지 번호
		int totalCount = 0; // 총 게시물 수
		int pageSize = 5; // 한 페이지에서 출력할 게시물 수
		int totalPage = 0; // 총 페이지 수
		int begin = 0; // 페이징 시작 위치
		int end = 0; // 페이징 끝 위치
		int n = 0; //
		int loop = 0; //
		int blockSize = 3; // 페이지 개수

		if (page == null || page.equals("")) {
			nowPage = 1;
		} else {
			nowPage = Integer.parseInt(page);
		}
		begin = ((nowPage - 1) * pageSize) + 1;
		end = begin + pageSize - 1;

		HashMap<String, String> map = new HashMap<>();

		map.put("begin", begin + "");
		map.put("end", end + "");
		map.put("id", id);

		StudyDAO dao = new StudyDAO();

		ArrayList<StudyDTO> list = dao.myStudy(map);

		for (StudyDTO dto : list) {
			// 제목 자르기
			String subject = dto.getStd_title();
			if (subject.length() > 10) {
				subject = subject.substring(0, 8) + "..";
			}
			// 제목 > 태그 > 이스케이프
			subject = subject.replace(">", "&gt;").replace("<", "&lt;");
			dto.setStd_title(subject);

			// 날짜 자르기
			String duedate = dto.getStd_duedate().substring(0, 10);
			dto.setStd_duedate(duedate);
			String regdate = dto.getStd_regdate().substring(0, 10);
			dto.setStd_regdate(regdate);
		}

		// 총 게시물 수
		totalCount = dao.getCount(id);
		totalPage = (int) Math.ceil((double) totalCount / pageSize);

		// 페이지 바 작업
		StringBuilder sb = new StringBuilder();

		loop = 1; // 루프 변수(10바퀴)
		n = ((nowPage - 1) / blockSize) * blockSize + 1; // 페이지 번호 역할

		// 이전 3페이지
		if (n == 1) {
			sb.append(
					"<li class='page-item disabled'><a class='page-link' href='#' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span> <span class='sr-only'>이전페이지</span> </a></li>");
		} else if (n > 3) {
			sb.append(String.format(
					"<li class='page-item'><a class='page-link' href='/good/user/mypage/mystudy.do?page=%d' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span> <span class='sr-only'>이전페이지</span> </a></li>",
					n - 1));
		}

		while (!(loop > blockSize || n > totalPage)) {
			if (n == nowPage) {
				sb.append(String.format(
						"<li class='page-item active'><a class='page-link' href='#'>%d</a></li>",
						n));
			} else {
				sb.append(String.format(
						"<li class='page-item'><a class='page-link' href='/good/user/mypage/mystudy.do?page=%d'>%d</a></li>",
						n, n));
			}
			loop++;
			n++;
		}

		// 다음 3페이지
		if (n >= totalPage) {
			sb.append(
					"<li class='page-item disabled'><a class='page-link' href='#' aria-label='Next'> <span aria-hidden='true'>&raquo;</span> <span class='sr-only'>다음페이지</span> </a></li>");
		} else {
			sb.append(String.format(
					"<li class='page-item'><a class='page-link' href='/good/user/mypage/mystudy.do?page=%d' aria-label='Next'> <span aria-hidden='true'>&raquo;</span> <span class='sr-only'>다음페이지</span> </a></li>", n));
		}
		req.setAttribute("list", list);
		req.setAttribute("map", map);
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("pagebar", sb.toString());

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/mypage/mystudy.jsp");
		dispatcher.forward(req, resp);

	}

}
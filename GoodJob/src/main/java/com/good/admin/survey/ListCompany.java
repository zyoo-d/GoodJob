package com.good.admin.survey;

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
 * ListCompany 서블릿 클래스는 회사 목록 페이지의 요청을 처리합니다. 이 서블릿은 "/admin/company/company.do"
 * URL 패턴으로 매핑되어 있습니다.
 */
@WebServlet("/admin/company/company.do")
public class ListCompany extends HttpServlet {
	/**
	 * GET 요청을 처리하여 회사 목록과 관련 페이징 정보를 가져와 사용자에게 보여줍니다. 회사 목록 검색, 필터링 및 페이징 처리를
	 * 포함합니다.
	 *
	 * @param req  클라이언트로부터 받은 HttpServletRequest 객체, 요청 데이터 접근에 사용됩니다.
	 * @param resp 클라이언트에게 보낼 HttpServletResponse 객체, 응답 데이터 설정에 사용됩니다.
	 * @throws ServletException 서블릿 처리 중 예외가 발생할 경우 던져집니다.
	 * @throws IOException      입출력 예외 발생시 던져집니다.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 페이징
		String page = req.getParameter("page");

		int nowPage = 0;// 현재페이지
		int totalCount = 0;// 총 게시물 수
		int pageSize = 6;// 출력 게시물 수
		int totalPage = 0;// 총페이지
		int begin = 0; // 페이지 시작
		int end = 0; // 페이지 끝
		int n = 0; // 페이지바
		int loop = 0;
		int blockSize = 3;

		if (page == null || page.equals("")) {
			nowPage = 1;
		} else {
			nowPage = Integer.parseInt(page);
		}

		begin = ((nowPage - 1) * pageSize) + 1;
		end = begin + pageSize - 1;

		// 검색 기록 가져오기
		String search = "n"; // 목록보기(n), 검색하기(y)
		String word = (req.getParameter("word") != null ? req.getParameter("word") : "");
		if (word == null || word.equals("")) {
			search = "n";
		} else {
			search = "y";
		}

		HashMap<String, String> map = new HashMap<>();
		map.put("search", search);
		map.put("word", word);
		map.put("begin", begin + "");
		map.put("end", end + "");
		map.put("hiring", "n");
		map.put("sort", "review");
		map.put("salary_seq", "");
		map.put("cp_address", "");

		// 목록 출력
		CompanyDAO dao = new CompanyDAO();
		ArrayList<CompanyDTO> comListInfo = dao.comListInfo(map);

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

		}

		// 총 게시물 수
		totalCount = dao.countCompanys();
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
					"<li class='page-item'><a class='page-link' href='/good/admin/company/company.do?page=%d' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span> <span class='sr-only'>이전페이지</span> </a></li>",
					n - 1));
		}

		while (!(loop > blockSize || n > totalPage)) {
			if (n == nowPage) {
				sb.append(String.format("<li class='page-item active'><a class='page-link' href='#'>%d</a></li>", n));
			} else {
				sb.append(String.format(
						"<li class='page-item'><a class='page-link' href='/good/admin/company/company.do?page=%d'>%d</a></li>",
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
					"<li class='page-item'><a class='page-link' href='/good/admin/company/company.do?page=%d' aria-label='Next'> <span aria-hidden='true'>&raquo;</span> <span class='sr-only'>다음페이지</span> </a></li>",
					n));
		}

		dao.close();

		req.setAttribute("comListInfo", comListInfo);
		req.setAttribute("map", map); // 페이지 begin. end hiring

		// 페이징
		req.setAttribute("nowPage", nowPage); // 페이지 번호
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage); // 페이지 번호
		req.setAttribute("pagebar", sb.toString()); // 페이지 바 작업

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/company/company.jsp");
		dispatcher.forward(req, resp);

	}

}
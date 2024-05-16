package com.good.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.company.model.CompanyDTO;
import com.good.company.repository.CompanyDAO;
import com.google.gson.Gson;
/**
 * SearchCompany 서블릿 클래스는 사용자의 회사 검색 요청을 처리합니다.
 * 이 서블릿은 "/user/searchcompany.do" URL 패턴으로 매핑되어 있으며,
 * 사용자가 입력한 검색 쿼리를 기반으로 회사 정보를 검색하고 결과를 JSON 형태로 반환합니다.
 */
@WebServlet("/user/searchcompany.do")
public class SearchCompany extends HttpServlet {
	 /**
     * GET 요청을 통해 회사 검색을 수행하고, 검색 결과를 JSON 형식으로 반환합니다.
     * 요청에 포함된 검색 쿼리를 사용하여 회사 데이터베이스에서 해당하는 정보를 조회합니다.
     *
     * @param req 클라이언트로부터 받은 HttpServletRequest 객체, 요청 데이터 접근에 사용됩니다.
     * @param resp 클라이언트에게 보낼 HttpServletResponse 객체, 응답 데이터 설정에 사용됩니다.
     * @throws ServletException 서블릿 처리 중 예외가 발생할 경우 던져집니다.
     * @throws IOException 입출력 예외 발생시 던져집니다.
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String input = req.getParameter("query");

		CompanyDAO dao = new CompanyDAO();
		List<CompanyDTO> list = dao.searchCompany(input);
		dao.close();

		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		Gson gson = new Gson();
		String json = gson.toJson(list);
		out.print(json);
		out.flush();

	}

}
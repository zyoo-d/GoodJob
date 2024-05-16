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

@WebServlet("/user/searchcompany.do")
public class SearchCompany extends HttpServlet {
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
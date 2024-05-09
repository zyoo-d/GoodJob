package com.good.news;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/news/keyword.do")
public class KeywordTrend extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String keyword = req.getParameter("keyword");
		NewsDAO dao = new NewsDAO();
		ArrayList<NewsDTO> nlist = dao.search(keyword);
		System.out.println(nlist);

		// req.setAttribute("nlist", nlist);

	}

}

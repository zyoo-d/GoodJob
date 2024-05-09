package com.good.company;

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
import com.good.company.model.Tag;
import com.good.company.repository.CompareDAO;

@WebServlet("/user/company/comparecompany.do")
public class CompareCompany extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String[] cp = req.getParameterValues("compareCp");
		String tag1 = cp[0];
		String tag2 = cp[1];
		String tag3 = cp[2];
		
		System.out.println(tag1 + ":" + tag2 + ":" + tag3);
		
		CompareDAO dao = new CompareDAO();
		
		
		HashMap<String, String> map = new HashMap<>();
		
		map.put("tag1", tag1);
		map.put("tag2", tag2);
		map.put("tag3", tag3);
		
		ArrayList<CompanyDTO> list = dao.getCompareInfo(map);

		for(CompanyDTO dto : list) {
			String date = dto.getFounded();
			if(date.equals("0000-00-00")) {
				date = "-";
				dto.setFounded(date);
			}
		}
		
		ArrayList<String> taglist = dao.tagList(map);
		
		

		
		req.setAttribute("list", list);
		req.setAttribute("map", map);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/company/comparecompany.jsp");
		dispatcher.forward(req, resp);

	}

}
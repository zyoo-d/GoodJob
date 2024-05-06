package com.good.matching;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.alert.Alert;
import com.good.matching.model.WishDTO;
import com.good.matching.repository.WishDAO;

@WebServlet("/user/matching/editwish.do")
public class EditWish extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");

        String edu_seq = req.getParameter("edu_seq");
        String salary_seq = req.getParameter("salary_seq");
        String career = req.getParameter("career");
        String[] locations = req.getParameterValues("locations");
        String[] welfare = req.getParameterValues("welfare");

		WishDTO dto = new WishDTO();
		WishDAO dao = new WishDAO();

		dto.setId(id);
		dto.setEdu_seq(edu_seq);
		dto.setSalary_seq(salary_seq);
		dto.setCareer(career);
		ArrayList<String> lc_code = new ArrayList<String>();
		for(String location : locations) {
			lc_code.add(location);
		}
		dto.setLc_code(lc_code);
		ArrayList<String> wel_seq = new ArrayList<String>();
		for(String wel : welfare) {
			wel_seq.add(wel);
		}
		dto.setWel_seq(wel_seq);

		int result = dao.editWish(dto);

		if(result==1){
			resp.sendRedirect("/good/main.do");
		} else {
			Alert.fail(resp);
		}

	}

}
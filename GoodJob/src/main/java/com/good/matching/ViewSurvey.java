package com.good.matching;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.alert.Alert;
import com.good.matching.model.SurveyDTO;
import com.good.matching.model.WishDTO;
import com.good.matching.repository.SurveyDAO;
import com.good.matching.repository.WishDAO;

@WebServlet("/user/matching/viewsurvey.do")
public class ViewSurvey extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");
		String mypage = req.getParameter("mypage");

		if (mypage == null || mypage.equals("")) {
			mypage = "N";
		}

		if (id == null || id.equals("")) {
			Alert.needLogin(resp, "/good/user/signin.do");
		} else {
			SurveyDAO dao = new SurveyDAO();
			ArrayList<SurveyDTO> list = dao.listSurvey();

			req.setAttribute("list", list);
			req.setAttribute("mypage", mypage);
			dao.close();
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/matching/viewsurvey.jsp");
			dispatcher.forward(req, resp);
		}

	}

}
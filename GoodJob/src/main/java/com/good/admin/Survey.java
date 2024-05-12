package com.good.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.matching.model.SurveyDTO;
import com.good.matching.repository.SurveyDAO;

@WebServlet("/admin/survey/survey.do")
public class Survey extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		SurveyDAO dao = new SurveyDAO();
		ArrayList<SurveyDTO> salList = dao.listSurvey("연봉");
		ArrayList<SurveyDTO> potList = dao.listSurvey("성장가능성");
		ArrayList<SurveyDTO> stabList = dao.listSurvey("조직안정성");
		ArrayList<SurveyDTO> culList = dao.listSurvey("조직문화");
		ArrayList<SurveyDTO> welList = dao.listSurvey("복지");

		req.setAttribute("salList", salList);
		req.setAttribute("potList", potList);
		req.setAttribute("stabList", stabList);
		req.setAttribute("culList", culList);
		req.setAttribute("welList", welList);

		dao.close();
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/survey/survey.jsp");
		dispatcher.forward(req, resp);

	}

}
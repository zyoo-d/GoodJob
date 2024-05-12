package com.good.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.alert.Alert;
import com.good.matching.model.SurveyDTO;
import com.good.matching.repository.SurveyDAO;

@WebServlet("/admin/survey/editsurvey.do")
public class EditSurvey extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String sv_seq = req.getParameter("sv_seq");
		String sv_content = req.getParameter("sv_content");
		
		SurveyDTO dto = new SurveyDTO();
		dto.setSv_seq(sv_seq);
		dto.setSv_content(sv_content);
		
		SurveyDAO dao = new SurveyDAO();
		int result = dao.editQuestion(dto);
		dao.close();
		
		if(result == 1) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/survey/survey.do");
			dispatcher.forward(req, resp);
		} else {
			Alert.fail(resp);
		}


	}

}
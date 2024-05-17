package com.good.admin.survey;

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

/**
 * EditSurvey 서블릿 클래스는 설문 조사 항목을 편집하는 요청을 처리합니다. 이 서블릿은
 * "/admin/survey/editsurvey.do" URL 패턴으로 매핑되어 있습니다.
 */
@WebServlet("/admin/survey/editsurvey.do")
public class EditSurvey extends HttpServlet {

	/**
	 * GET 요청을 통해 설문 조사의 특정 항목을 수정합니다. 사용자는 설문 조사의 항목 번호(sv_seq)와 수정할
	 * 내용(sv_content)을 제공해야 합니다.
	 *
	 * @param req  클라이언트로부터 받은 HttpServletRequest 객체, 요청 데이터 접근에 사용됩니다.
	 * @param resp 클라이언트에게 보낼 HttpServletResponse 객체, 응답 데이터 설정에 사용됩니다.
	 * @throws ServletException 서블릿 처리 중 예외가 발생할 경우 던져집니다.
	 * @throws IOException      입출력 예외 발생시 던져집니다.
	 */
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

		if (result == 1) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/survey/survey.do");
			dispatcher.forward(req, resp);
		} else {
			Alert.fail(resp);
		}

		dao.close();

	}

}
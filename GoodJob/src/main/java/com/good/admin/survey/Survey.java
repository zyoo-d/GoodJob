package com.good.admin.survey;

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

/**
 * Survey 서블릿 클래스는 설문 조사 데이터를 처리하고 관리하기 위한 요청을 처리합니다.
 * 이 서블릿은 "/admin/survey/survey.do" URL 패턴으로 매핑되어 있습니다.
 */
@WebServlet("/admin/survey/survey.do")
public class Survey extends HttpServlet {

    /**
	 * GET 요청을 처리하여 설문 조사의 결과를 가져오고 결과 페이지로 전달합니다. 설문 조사 데이터는 다양한 카테고리(예: 연봉, 성장 가능성 등)에 따라 요청됩니다.
	 *
	 * @param req  클라이언트로부터 받은 HttpServletRequest 객체, 요청 데이터 접근에 사용됩니다.
	 * @param resp 클라이언트에게 보낼 HttpServletResponse 객체, 응답 데이터 설정에 사용됩니다.
	 * @throws ServletException 서블릿 처리 중 예외가 발생할 경우 던져집니다.
	 * @throws IOException      입출력 예외 발생시 던져집니다.
	 */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SurveyDAO dao = new SurveyDAO(); // 설문 DAO 객체 생성
        ArrayList<SurveyDTO> salList = dao.listSurvey("연봉"); // "연봉" 카테고리에 대한 설문 결과 리스트
        ArrayList<SurveyDTO> potList = dao.listSurvey("성장가능성"); // "성장가능성" 카테고리에 대한 설문 결과 리스트
        ArrayList<SurveyDTO> stabList = dao.listSurvey("조직안정성"); // "조직안정성" 카테고리에 대한 설문 결과 리스트
        ArrayList<SurveyDTO> culList = dao.listSurvey("조직문화"); // "조직문화" 카테고리에 대한 설문 결과 리스트
        ArrayList<SurveyDTO> welList = dao.listSurvey("복지"); // "복지" 카테고리에 대한 설문 결과 리스트

        req.setAttribute("salList", salList); // 요청 객체에 "연봉" 결과 리스트를 속성으로 저장
        req.setAttribute("potList", potList); // 요청 객체에 "성장가능성" 결과 리스트를 속성으로 저장
        req.setAttribute("stabList", stabList); // 요청 객체에 "조직안정성" 결과 리스트를 속성으로 저장
        req.setAttribute("culList", culList); // 요청 객체에 "조직문화" 결과 리스트를 속성으로 저장
        req.setAttribute("welList", welList); // 요청 객체에 "복지" 결과 리스트를 속성으로 저장

        dao.close(); // DAO 객체의 리소스를 해제

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/survey/survey.jsp"); // 결과 페이지로 포워딩 설정
        dispatcher.forward(req, resp); // 요청과 응답을 결과 페이지로 전달
    }
}

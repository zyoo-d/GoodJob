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
/**
 * ViewSurvey 서블릿 클래스는 사용자가 설문조사 페이지를 요청할 때 해당 설문의 목록을 보여줍니다.
 * 이 클래스는 사용자의 설문조사 데이터를 조회하고, 그 결과를 웹 페이지에 제공하는 역할을 합니다.
 */
@WebServlet("/user/matching/viewsurvey.do")
public class ViewSurvey extends HttpServlet {
	/**
     * GET 요청을 처리하여 설문조사 목록을 사용자에게 제공합니다.
     * 사용자가 로그인 상태인지 확인하고, 로그인이 되어 있지 않다면 로그인 페이지로 리다이렉트합니다.
     * 로그인이 확인되면 설문조사 데이터를 데이터베이스에서 가져와서 사용자에게 보여줍니다.
     *
     * @param req 클라이언트로부터 받은 HttpServletRequest 객체, 요청 데이터 접근에 사용됩니다.
     * @param resp 클라이언트에게 보낼 HttpServletResponse 객체, 응답 데이터 설정에 사용됩니다.
     * @throws ServletException 서블릿 처리 중 예외가 발생할 경우 던져집니다.
     * @throws IOException 입출력 예외 발생시 던져집니다.
     */
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
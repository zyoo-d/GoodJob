package com.good.board.study;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.alert.Alert;
import com.good.board.repository.StudyDAO;

/**
 * DelStudy 서블릿 클래스는 사용자의 공부 세션 삭제 요청을 처리합니다. 이 서블릿은 "/user/study/delstudy.do"
 * URL 패턴으로 매핑되어 있습니다.
 */
@WebServlet("/user/study/delstudy.do")
public class DelStudy extends HttpServlet {
	/**
	 * GET 요청을 통해 공부 세션의 삭제를 처리합니다. 사용자 인증을 먼저 확인하고, 해당 사용자가 삭제 권한을 가지고 있으면 공부 세션을
	 * 삭제합니다.
	 *
	 * @param req  클라이언트로부터 받은 HttpServletRequest 객체, 요청 데이터 접근에 사용됩니다.
	 * @param resp 클라이언트에게 보낼 HttpServletResponse 객체, 응답 데이터 설정에 사용됩니다.
	 * @throws ServletException 서블릿 처리 중 예외가 발생할 경우 던져집니다.
	 * @throws IOException      입출력 예외 발생시 던져집니다.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 인증받지 못한 사용자 or 권한이 없는 사용자 > 거부
		if (AuthStudy.check(req, resp)) {
			return;
		}

		String mypage = req.getParameter("mypage");
		String seq = req.getParameter("std_seq");

		if (mypage == null || mypage.equals("")) {
			mypage = "N";
		}

		StudyDAO dao = new StudyDAO();

		int result = dao.delStudy(seq);
		dao.close();

		if (result == 1 && mypage.equals("Y")) {
			resp.sendRedirect("/good/user/mypage/mystudy.do");
		} else if (result == 1 && mypage.equals("N")) {
			resp.sendRedirect("/good/user/study/liststudy.do");
		} else {
			Alert.fail(resp);
		}

	}

}
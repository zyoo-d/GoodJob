package com.good.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Logout 서블릿은 사용자 로그아웃 기능을 제공합니다.
 */
@WebServlet("/user/logout.do")
public class Logout extends HttpServlet {
    /**
     * GET 요청을 처리하여 사용자를 로그아웃 처리하고 메인 페이지로 리다이렉트합니다.
     * 
     * @param req HttpServletRequest 객체로 클라이언트로부터 전송된 요청을 나타냅니다.
     * @param resp HttpServletResponse 객체로 클라이언트로 응답을 보냅니다.
     * @throws ServletException 서블릿 관련 예외가 발생할 경우 던져집니다.
     * @throws IOException 입출력 관련 예외가 발생할 경우 던져집니다.
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		session.invalidate();
		
		resp.sendRedirect("/good/main.do");
		
		
	}

}

package com.good.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * GetAccount 서블릿은 사용자의 계정 정보를 조회하는 페이지로 이동시킵니다.
 */
@WebServlet("/user/getaccount.do")
public class GetAccount extends HttpServlet {
    /**
     * GET 요청을 처리하여 사용자의 계정 정보를 조회하는 페이지로 이동시킵니다.
     * 
     * @param req HttpServletRequest 객체로 클라이언트로부터 전송된 요청을 나타냅니다.
     * @param resp HttpServletResponse 객체로 클라이언트로 응답을 보냅니다.
     * @throws ServletException 서블릿 관련 예외가 발생할 경우 던져집니다.
     * @throws IOException 입출력 관련 예외가 발생할 경우 던져집니다.
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/getaccount.jsp");
		dispatcher.forward(req, resp);

	}
	
	
}

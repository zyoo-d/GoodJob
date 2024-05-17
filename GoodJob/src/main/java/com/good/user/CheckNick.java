package com.good.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.user.repository.UserDAO;
/**
 * CheckNick 서블릿은 사용자가 입력한 닉네임이 데이터베이스에 이미 존재하는지 확인하는 기능을 제공합니다.
 */
@WebServlet("/user/checknick.do")
public class CheckNick extends HttpServlet {
	 /**
     * POST 요청을 처리하여 사용자가 입력한 닉네임이 데이터베이스에 이미 존재하는지 확인합니다.
     * 
     * @param req HttpServletRequest 객체로 클라이언트로부터 전송된 요청을 나타냅니다.
     * @param resp HttpServletResponse 객체로 클라이언트로 응답을 보냅니다.
     * @throws ServletException 서블릿 관련 예외가 발생할 경우 던져집니다.
     * @throws IOException 입출력 관련 예외가 발생할 경우 던져집니다.
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		UserDAO dao = new UserDAO();
		String nickname = req.getParameter("nickname");
			
		int result = dao.checkNick(nickname);
		
		dao.close();
		PrintWriter writer = resp.getWriter();
		writer.print(result);
		writer.close();

	}
}
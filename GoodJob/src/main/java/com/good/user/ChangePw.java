package com.good.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.good.user.model.UserDTO;
import com.good.user.repository.UserDAO;
/**
 * ChangePw 서블릿은 사용자의 비밀번호를 변경하는 기능을 제공합니다.
 * 클라이언트로부터 사용자의 ID와 변경할 비밀번호를 받아와 데이터베이스에 저장된 해당 사용자의 비밀번호를 변경합니다.
 */
@WebServlet("/user/changepw.do")
public class ChangePw extends HttpServlet {
	  /**
     * POST 요청을 처리하여 사용자의 비밀번호를 변경합니다.
     * 
     * @param req HttpServletRequest 객체로 클라이언트로부터 전송된 요청을 나타냅니다.
     * @param resp HttpServletResponse 객체로 클라이언트로 응답을 보냅니다.
     * @throws ServletException 서블릿 관련 예외가 발생할 경우 던져집니다.
     * @throws IOException 입출력 관련 예외가 발생할 경우 던져집니다.
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
	    System.out.println("POST ID: " + id);
	    System.out.println("POST PW: " + pw);
		

		UserDAO dao = new UserDAO();
		UserDTO dto = new UserDTO();
		
		dto.setId(id);
		dto.setPw(pw);
		
		int result = dao.changePw(dto);
		
		dao.close();
		
		JSONObject obj = new JSONObject();
		
		obj.put("result", result);
		
		PrintWriter writer = resp.getWriter();
		writer.print(obj);
		writer.close();

	}

}

package com.good.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.good.user.model.UserDTO;
import com.good.user.repository.UserDAO;
/**
 * GetPw 서블릿은 사용자의 아이디와 전화번호를 받아와 해당 사용자의 비밀번호를 반환합니다.
 */
@WebServlet("/user/getpw.do")
public class GetPw extends HttpServlet {
    /**
     * POST 요청을 처리하여 사용자의 아이디와 전화번호를 받아와 해당 사용자의 비밀번호를 반환합니다.
     * 
     * @param req HttpServletRequest 객체로 클라이언트로부터 전송된 요청을 나타냅니다.
     * @param resp HttpServletResponse 객체로 클라이언트로 응답을 보냅니다.
     * @throws ServletException 서블릿 관련 예외가 발생할 경우 던져집니다.
     * @throws IOException 입출력 관련 예외가 발생할 경우 던져집니다.
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");
		String tel = req.getParameter("tel");
		
		System.out.println("id :" + id);
		System.out.println("tel :" + tel);
		
		UserDTO dto = new UserDTO();
		UserDAO dao = new UserDAO();
		
		dto.setId(id);
		dto.setTel(tel);
		
		int result = dao.getPw(dto);
		
		JSONObject obj = new JSONObject();
		
		obj.put("result", result);
		
		PrintWriter writer = resp.getWriter();
		writer.print(obj);
		writer.close();
		dao.close();
	}
}

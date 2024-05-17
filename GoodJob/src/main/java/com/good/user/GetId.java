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
 * GetId 서블릿은 사용자의 이름과 전화번호를 받아와 해당 사용자의 아이디를 반환합니다.
 */
@WebServlet("/user/getid.do")
public class GetId extends HttpServlet {
    /**
     * POST 요청을 처리하여 사용자의 이름과 전화번호를 받아와 해당 사용자의 아이디를 반환합니다.
     * 
     * @param req HttpServletRequest 객체로 클라이언트로부터 전송된 요청을 나타냅니다.
     * @param resp HttpServletResponse 객체로 클라이언트로 응답을 보냅니다.
     * @throws ServletException 서블릿 관련 예외가 발생할 경우 던져집니다.
     * @throws IOException 입출력 관련 예외가 발생할 경우 던져집니다.
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("name");
		String tel = req.getParameter("tel");
		
		UserDTO dto = new UserDTO();
		UserDAO dao = new UserDAO();
		
		dto.setName(name);
		dto.setTel(tel);
		
		String result = dao.getId(dto);
		
		dao.close();
		
		JSONObject obj = new JSONObject();
		
		obj.put("result", result);
		
		PrintWriter writer = resp.getWriter();
		writer.print(obj);
		writer.close();
		
	}
}

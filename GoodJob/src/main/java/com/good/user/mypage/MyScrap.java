package com.good.user.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.company.model.ScrapDTO;
import com.good.company.repository.CompanyDAO;

@WebServlet("/user/mypage/myscrap.do")
public class MyScrap extends HttpServlet{

    /**
     * HTTP GET 요청을 처리하여 사용자의 스크랩 목록을 표시합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException Servlet 관련 에러 발생 시
     * @throws IOException      I/O 에러 발생 시
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		   // 사용자 세션 정보 가져오기
			HttpSession session = req.getSession();
			String id = (String) session.getAttribute("id");
			System.out.println(id);
	      
		    // CompanyDAO를 사용하여 사용자의 스크랩 목록 가져오기
	        CompanyDAO dao = new CompanyDAO();
	        ArrayList<ScrapDTO> ScrapList = new ArrayList<ScrapDTO>();
	        ScrapList = dao.writeScrap(id);
	        // 스크랩 목록을 요청 객체에 저장     
	        System.out.println(ScrapList);
	        req.setAttribute("ScrapList", ScrapList);
	        // 스크랩 페이지로 포워딩
	        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/mypage/myscrap.jsp");
			dispatcher.forward(req, resp);
	        
	}
	 /**
     * HTTP POST 요청을 처리하여 사용자가 선택한 스크랩을 삭제합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException Servlet 관련 에러 발생 시
     * @throws IOException      I/O 에러 발생 시
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 // 삭제할 스크랩 ID 가져오기
		   String cp_seq = req.getParameter("cp_seq");
		   System.out.println(cp_seq);

		    // CompanyDAO를 이용하여 데이터베이스에서 해당 스크랩 삭제
		    CompanyDAO dao = new CompanyDAO();
		    int success = dao.delScrap(cp_seq);

		    // 응답 전송
		    resp.setContentType("text/plain");
		    if (success>0) {
		        resp.getWriter().write("success");
		    } else {
		        resp.getWriter().write("fail");
		    }

}
}
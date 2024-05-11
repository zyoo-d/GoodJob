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
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			HttpSession session = req.getSession();
			String id = (String) session.getAttribute("id");
			System.out.println(id);
	      
	
	        CompanyDAO dao = new CompanyDAO();
	        ArrayList<ScrapDTO> ScrapList = new ArrayList<ScrapDTO>();
	        ScrapList = dao.writeScrap(id);
	        
	        System.out.println(ScrapList);
	        req.setAttribute("ScrapList", ScrapList);

	        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/mypage/myscrap.jsp");
			dispatcher.forward(req, resp);
	        
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
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
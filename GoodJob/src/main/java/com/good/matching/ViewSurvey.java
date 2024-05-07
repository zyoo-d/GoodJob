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

import com.good.matching.model.SurveyDTO;
import com.good.matching.repository.SurveyDAO;

@WebServlet("/user/matching/viewsurvey.do")
public class ViewSurvey extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String wish = (String)session.getAttribute("wish");
		
		if(wish.equals("0")) {
			PrintWriter writer = resp.getWriter();
			writer.println("<html><head><meta charset=\"UTF-8\"><title>Access Denied</title></head><body>");
			writer.println("<script type='text/javascript'>");
			writer.println("alert('선호근무조건 조사 후 이용 가능합니다.');");
			writer.println("location.href = \"/good/user/matching/viewwish.do\";");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();
		} else {
			SurveyDAO dao = new SurveyDAO();
			ArrayList<SurveyDTO> list = dao.listSurvey();
			
			req.setAttribute("list", list);
			dao.close();
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/matching/viewsurvey.jsp");
			dispatcher.forward(req, resp);
		}
		

	}

}
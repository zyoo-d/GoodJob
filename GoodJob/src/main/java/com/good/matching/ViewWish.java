package com.good.matching;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.alert.Alert;
import com.good.matching.model.WishDTO;
import com.good.matching.repository.WishDAO;

@WebServlet("/user/matching/viewwish.do")
public class ViewWish extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");
		String wish = (String) session.getAttribute("wish");
		String prefer = (String) session.getAttribute("prefer");
		String mypage = req.getParameter("mypage");

		if (mypage == null || mypage.equals("")) {
			mypage = "N";
		}

		if (id == null || id.equals("")) {
			Alert.needLogin(resp, "/good/user/signin.do");
		} else if (prefer.equals("0") && mypage.equals("N")) {
			PrintWriter writer = resp.getWriter();
			writer.println("<html><head><meta charset=\"UTF-8\"><title>Access Denied</title></head><body>");
			writer.println("<script type='text/javascript'>");
			writer.println("alert('설문조사 후 이용 가능합니다.');");
			writer.println("location.href = \"/good/user/matching/viewsurvey.do\";");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();
		} else if (mypage.equals("Y") || prefer.equals("1")) {
			if (wish.equals("1")) {
				WishDAO dao = new WishDAO();
				WishDTO dto = dao.getWish(id);

				req.setAttribute("dto", dto);
				dao.close();
			}
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/matching/viewwish.jsp");
			dispatcher.forward(req, resp);

		}
	}

}
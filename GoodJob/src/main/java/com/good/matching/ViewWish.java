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
/**
 * ViewWish 서블릿 클래스는 사용자가 자신의 '위시리스트'를 조회할 때 사용됩니다.
 * 사용자의 로그인 상태와 선호도 설정 여부를 확인하고, 이에 따라 적절한 페이지로 리다이렉트하거나 위시리스트 정보를 보여줍니다.
 */
@WebServlet("/user/matching/viewwish.do")
public class ViewWish extends HttpServlet {
	/**
     * GET 요청을 처리하여 사용자의 '위시리스트' 정보를 보여줍니다.
     * 로그인이 필요한 페이지에서 사용자가 로그인 상태가 아니면 로그인 페이지로 리다이렉트합니다.
     * 설문조사가 완료되지 않은 경우, 설문조사 페이지로 리다이렉트합니다.
     * 모든 조건이 충족되면 사용자의 위시리스트 정보를 조회하여 제공합니다.
     *
     * @param req 클라이언트로부터 받은 HttpServletRequest 객체, 요청 데이터 접근에 사용됩니다.
     * @param resp 클라이언트에게 보낼 HttpServletResponse 객체, 응답 데이터 설정에 사용됩니다.
     * @throws ServletException 서블릿 처리 중 예외가 발생할 경우 던져집니다.
     * @throws IOException 입출력 예외 발생시 던져집니다.
     */
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
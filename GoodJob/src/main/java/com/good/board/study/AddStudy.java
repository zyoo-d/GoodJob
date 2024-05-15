package com.good.board.study;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.alert.Alert;
import com.good.board.model.StudyDTO;
import com.good.board.repository.StudyDAO;

/**
 * AddStudy 서블릿 클래스는 사용자가 새로운 공부 세션을 추가하는 기능을 처리합니다.
 * 이 서블릿은 "/user/study/addstudy.do" URL 패턴으로 매핑되어 있습니다.
 */
@WebServlet("/user/study/addstudy.do")
public class AddStudy extends HttpServlet {
	/**
     * GET 요청을 처리하여 사용자가 로그인 상태인지 확인하고 공부 세션 추가 폼을 표시합니다.
     * 로그인이 되어 있지 않은 경우, 로그인 페이지로 리다이렉트합니다.
     *
     * @param req 클라이언트로부터 받은 HttpServletRequest 객체, 요청 데이터 접근에 사용됩니다.
     * @param resp 클라이언트에게 보낼 HttpServletResponse 객체, 응답 데이터 설정에 사용됩니다.
     * @throws ServletException 서블릿 처리 중 예외가 발생할 경우 던져집니다.
     * @throws IOException 입출력 예외 발생시 던져집니다.
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		
		if(id == null || id.equals("")) {
			Alert.needLogin(resp, "/good/user/signin.do");
		} else {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/study/addstudy.jsp");
			dispatcher.forward(req, resp);
		}

	}
	/**
     * POST 요청을 처리하여 새로운 공부 세션의 정보를 받아 데이터베이스에 추가합니다.
     * 성공적으로 추가되면 공부 세션 목록 페이지로 리다이렉트합니다. 실패할 경우 실패 알림을 보냅니다.
     *
     * @param req 클라이언트로부터 받은 HttpServletRequest 객체, 요청 데이터 접근에 사용됩니다.
     * @param resp 클라이언트에게 보낼 HttpServletResponse 객체, 응답 데이터 설정에 사용됩니다.
     * @throws ServletException 서블릿 처리 중 예외가 발생할 경우 던져집니다.
     * @throws IOException 입출력 예외 발생시 던져집니다.
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String std_title = req.getParameter("std_title");
		String std_content = req.getParameter("std_content");
		String std_duedate = req.getParameter("std_duedate");
		String cp_seq = req.getParameter("cp_seq");
		
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		
		StudyDTO dto = new StudyDTO();
		dto.setCp_seq(cp_seq);
		dto.setId(id);
		dto.setStd_content(std_content);
		dto.setStd_duedate(std_duedate);
		dto.setStd_ing("N");
		dto.setStd_title(std_title);
		
		StudyDAO dao = new StudyDAO();
		
		int result = dao.addStudy(dto);
		dao.close();
		if(result==1) {
			resp.sendRedirect("/good/user/study/liststudy.do");
		} else {
			Alert.fail(resp);
		}
		
	}

}
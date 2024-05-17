package com.good.board.study;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.alert.Alert;
import com.good.board.model.StudyDTO;
import com.good.board.repository.StudyDAO;
/**
 * EditStudy 서블릿 클래스는 사용자의 공부 세션 수정 요청을 처리합니다.
 * 이 서블릿은 "/user/study/editstudy.do" URL 패턴으로 매핑되어 있습니다.
 */
@WebServlet("/user/study/editstudy.do")
public class EditStudy extends HttpServlet {
	/**
     * GET 요청을 통해 공부 세션 수정 폼을 표시합니다.
     * 사용자 인증을 먼저 확인하고, 해당 사용자가 수정 권한을 가지고 있으면 수정 폼을 제공합니다.
     *
     * @param req 클라이언트로부터 받은 HttpServletRequest 객체, 요청 데이터 접근에 사용됩니다.
     * @param resp 클라이언트에게 보낼 HttpServletResponse 객체, 응답 데이터 설정에 사용됩니다.
     * @throws ServletException 서블릿 처리 중 예외가 발생할 경우 던져집니다.
     * @throws IOException 입출력 예외 발생시 던져집니다.
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 인증받지 못한 사용자 or 권한이 없는 사용자 > 거부
		if(AuthStudy.check(req, resp)) { return; }
		String mypage = req.getParameter("mypage");
		
		if (mypage == null || mypage.equals("")) {
			mypage = "N";
		} 
		
		String std_seq = req.getParameter("std_seq");
		StudyDAO dao = new StudyDAO();
		StudyDTO dto = dao.getStudy(std_seq);

		String date = dto.getStd_duedate().substring(0, 10);
		dto.setStd_duedate(date);
		
		req.setAttribute("dto", dto);
		req.setAttribute("mypage", mypage);
		dao.close();
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/study/editstudy.jsp");
		dispatcher.forward(req, resp);

	}
	/**
     * POST 요청을 처리하여 공부 세션 정보를 업데이트합니다.
     * 성공적으로 업데이트되면 해당 공부 세션의 상세 페이지로 리다이렉트합니다. 실패할 경우 실패 알림을 보냅니다.
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
		String std_ing = req.getParameter("std_ing");
		String std_seq = req.getParameter("std_seq");
		String cp_seq = req.getParameter("cp_seq");
		
		StudyDTO dto = new StudyDTO();
		dto.setCp_seq(cp_seq);
		dto.setStd_seq(std_seq);
		dto.setStd_content(std_content);
		dto.setStd_duedate(std_duedate);
		dto.setStd_ing(std_ing);
		dto.setStd_title(std_title);
		
		StudyDAO dao = new StudyDAO();
		
		int result = dao.editStudy(dto);
		dao.close();
		if(result==1) {
			resp.sendRedirect("/good/user/study/viewstudy.do?std_seq="+std_seq);
		} else {
			Alert.fail(resp);
		}
		
	}
}
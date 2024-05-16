package com.good.board.qna;

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
import com.good.board.model.QnaBoardDTO;
import com.good.board.repository.QnaBoardDAO;


/**
 * QnA 게시글 작성을 처리하는 서블릿 클래스입니다.
 * "/user/qna/addqna.do" URL 패턴에 매핑됩니다.
 */
@WebServlet("/user/qna/addqna.do")
public class AddQna extends HttpServlet {
	
	/**
     * GET 요청을 처리합니다.
     * 사용자가 로그인되어 있으면 QnA 게시글 작성 페이지로 이동하고,
     * 로그인되어 있지 않으면 로그인이 필요하다는 알림 메시지를 표시합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외
     * @throws IOException      입출력 예외
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		
		if(id== null || id.equals("")) {
			
			Alert.needLogin(resp, "/good/user/signin.do");
			
		} else {
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/qna/addqna.jsp");
			dispatcher.forward(req, resp);
			
		}
		
	}

	/**
     * POST 요청을 처리합니다.
     * 사용자가 작성한 QnA 게시글을 데이터베이스에 저장하고,
     * 저장 결과에 따라 작성된 게시글로 이동하거나 실패 메시지를 표시합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외
     * @throws IOException      입출력 예외
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		String id = (String) session.getAttribute("id");
		String qna_title = req.getParameter("qna_title");
		String cp_name = req.getParameter("cp_name");
		String qna_content = req.getParameter("qna_content");
		
		System.out.println(cp_name);
		
		QnaBoardDTO dto = new QnaBoardDTO();
		dto.setId(id);
		dto.setQna_title(qna_title);
		dto.setCp_name(cp_name);
		dto.setQna_content(qna_content);
		
		QnaBoardDAO dao = new QnaBoardDAO();
		int qna_seq = dao.create(dto);
		
		if(qna_seq > 0) {
			
			resp.sendRedirect("/good/user/qna/viewqna.do?qna_seq="+qna_seq);
			
		} else {
			resp.setCharacterEncoding("UTF-8");
			
			PrintWriter writer = resp.getWriter();
			 writer.println("<script>");
		     writer.println("alert('게시글 작성에 실패했습니다.');");
		     writer.println("history.back();"); 
		     writer.println("</script>");
			 writer.flush();
			 writer.close();
			
		}
		
		dao.close();
		
	}

}

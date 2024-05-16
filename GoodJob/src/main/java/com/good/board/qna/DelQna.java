package com.good.board.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.alert.Alert;
import com.good.board.repository.QnaBoardDAO;

/**
 * QnA 게시글 삭제를 처리하는 서블릿 클래스입니다.
 * "/user/qna/delqna.do" URL 패턴에 매핑됩니다.
 */
@WebServlet("/user/qna/delqna.do")
public class DelQna extends HttpServlet {

	 /**
     * GET 요청을 처리합니다.
     * QnA 게시글 삭제 권한을 확인하고, 삭제 작업을 수행합니다.
     * 삭제 성공 시 QnA 목록 페이지로 이동하고, 실패 시 실패 메시지를 표시합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외
     * @throws IOException      입출력 예외
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		if(AuthQna.check(req, resp)) {
			return;
		}
		
		String qna_seq = req.getParameter("qna_seq");
		
		QnaBoardDAO dao = new QnaBoardDAO();
		int result = dao.delQna(qna_seq);
		
		if(result == 1) {
			
			resp.sendRedirect("/good/user/qna/listqna.do");
			
			
		} else {
			
			Alert.fail(resp);
			resp.sendRedirect("/good/user/qna/listqna.do?qna_seq=" + qna_seq);
			
		}
		
		
		

	
	}
	
	
	
}

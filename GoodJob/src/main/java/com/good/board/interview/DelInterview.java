package com.good.board.interview;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.alert.Alert;
import com.good.board.qna.AuthQna;
import com.good.board.repository.InterviewDAO;
/*
 Servlet implementation class DelInterview

 이 서블릿은 "/board/interview/itvDel.do" URL 요청을 처리합니다.
 사용자가 선택한 면접 정보를 삭제하는 기능을 제공합니다.

*/
@WebServlet("/board/interview/itvDel.do")
public class DelInterview extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 /**
		    * HTTP GET 요청을 처리하여 선택한 면접 정보를 삭제합니다.
		    *
		    * @param req  HttpServletRequest 객체
		    * @param resp HttpServletResponse 객체
		    * @throws ServletException Servlet 관련 에러 발생 시
		    * @throws IOException      I/O 에러 발생 시
		    */
	
		
		  // 삭제할 면접 정보 ID 가져오기
		String itv_seq = req.getParameter("itv_seq");
		System.out.println(itv_seq);
		InterviewDAO dao = new InterviewDAO();
		  // InterviewDAO를 사용하여 면접 정보 삭제
		int result = dao.delQna(itv_seq);
		// 삭제 결과에 따른 처리	
		if(result == 1) {
			System.out.println("삭제 성공");
			resp.sendRedirect("/good/board/interview/interview.do");
			
			
		} else {
			  // 삭제 실패 시 경고 메시지 표시 후 면접 수정 페이지로 리다이렉트
			Alert.fail(resp);
			resp.sendRedirect("/itvWrite.do?itv_seq=" + itv_seq);
			
		}
		
		
		
	}
	
}

package com.good.board.comment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.board.repository.StudyDAO;
import com.good.company.repository.CompanyDAO;
/*
  Servlet implementation class LiveDelComment

  이 서블릿은 "/board/comment/livedelcomment.do" URL 요청을 처리합니다.
  실시간 채용 페이지에 작성된 댓글을 삭제하는 기능을 제공합니다.

 */
@WebServlet("/board/comment/livedelcomment.do")
public class LiveDelComment extends HttpServlet {

    /**
     * HTTP POST 요청을 처리하여 실시간 채용 페이지의 댓글을 삭제합니다.
     *
     * @param request  HttpServletRequest 객체
     * @param response HttpServletResponse 객체
     * @throws ServletException Servlet 관련 에러 발생 시
     * @throws IOException      I/O 에러 발생 시
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 삭제할 댓글 ID 가져오기
		String seq = request.getParameter("seq");
		System.out.println(seq);
		
	    // CompanyDAO를 사용하여 댓글 삭제
		CompanyDAO cmdao = new CompanyDAO();
		
		int result = cmdao.delComment(seq);
		System.out.println(result);
		// JSON 형식의 응답 데이터 생성
		response.setContentType("application/json");
		
		PrintWriter writer = response.getWriter();
		writer.print("{");
		writer.print("\"result\": " + result); //"result": 1
		writer.print("}");
		writer.close();
		
		
		
		
	}
}

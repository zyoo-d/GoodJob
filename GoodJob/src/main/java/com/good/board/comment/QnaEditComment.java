package com.good.board.comment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.board.model.CommentDTO;
import com.good.board.repository.QnaBoardDAO;
/*
  Servlet implementation class QnaEditComment

  이 서블릿은 "/board/comment/qnaeditcomment.do" URL 요청을 처리합니다.
  질문답변 게시판의 댓글을 수정하는 기능을 제공합니다.

 */
@WebServlet("/board/comment/qnaeditcomment.do")
public class QnaEditComment extends HttpServlet {


	 /**
     * HTTP POST 요청을 처리하여 질문답변 게시판의 댓글을 수정합니다.
     *
     * @param request  HttpServletRequest 객체
     * @param response HttpServletResponse 객체
     * @throws ServletException Servlet 관련 에러 발생 시
     * @throws IOException      I/O 에러 발생 시
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  // 수정할 댓글 ID와 내용 가져오기
		String seq = request.getParameter("seq");
		String content  =request.getParameter("content");
		
		
		QnaBoardDAO dao = new QnaBoardDAO();
		 // QnaBoardDAO를 사용하여 댓글 수정
		CommentDTO dto = new CommentDTO();
		dto.setCm_seq(seq);
		dto.setContent(content);
		
		
		int result = dao.editComment(dto);
		// JSON 형식의 응답 데이터 생성
		response.setContentType("application/json");
		
		PrintWriter writer = response.getWriter();
		writer.print("{");
		writer.print("\"result\": " + result); //"result": 1
		writer.print("}");
		writer.close();
		
	}

}
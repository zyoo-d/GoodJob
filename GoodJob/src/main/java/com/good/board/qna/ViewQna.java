package com.good.board.qna;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.board.model.CommentDTO;
import com.good.board.model.QnaBoardDTO;
import com.good.board.repository.QnaBoardDAO;

/**
 * QnA 게시글 상세 보기를 처리하는 서블릿 클래스입니다.
 * "/user/qna/viewqna.do" URL 패턴에 매핑됩니다.
 */
@WebServlet("/user/qna/viewqna.do")
public class ViewQna extends HttpServlet{

	 /**
     * GET 요청을 처리합니다.
     * QnA 게시글의 상세 정보와 해당 게시글에 달린 댓글 목록을 조회하여 상세 보기 페이지로 전달합니다.
     * 조회수 처리를 위해 세션을 사용하여 중복 조회수 증가를 방지합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외
     * @throws IOException      입출력 예외
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int qna_seq = Integer.parseInt(req.getParameter("qna_seq"));


		QnaBoardDAO dao = new QnaBoardDAO();

		HttpSession session = req.getSession();
		String viewedKey = "qna_" + qna_seq + "viewed";
		if(session.getAttribute(viewedKey) == null) {
			dao.updateReadcount(qna_seq);
			session.setAttribute(viewedKey, true);
		}

		QnaBoardDTO dto = dao.viewQna(qna_seq);

		String content = dto.getQna_content();
		
		//글내용 > 태그 > 이스케이프
		content = content.replace(">", "&gt;").replace("<", "&lt;");

		//글내용 > 개행 문자 처리
//		content = content.replace("\r\n", "<br>");
		
		dto.setQna_content(content);

		ArrayList<CommentDTO> clist = dao.listComment(qna_seq);
		
		req.setAttribute("dto", dto);
		req.setAttribute("clist", clist);
		dao.close();

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/qna/viewqna.jsp");
		dispatcher.forward(req, resp);

	}

}

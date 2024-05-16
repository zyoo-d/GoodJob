package com.good.board.study;
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
import com.good.board.model.StudyDTO;
import com.good.board.repository.StudyDAO;
/**
 * ViewStudy 서블릿 클래스는 사용자가 특정 공부 세션의 상세 정보를 조회할 때 사용됩니다.
 * 이 서블릿은 사용자가 공부 세션의 상세 페이지를 요청하면, 해당 세션의 정보와 관련 댓글들을 데이터베이스에서 불러와 제공합니다.
 * "/user/study/viewstudy.do" URL 패턴으로 매핑되어 사용자 요청을 처리합니다.
 */
@WebServlet("/user/study/viewstudy.do")
public class ViewStudy extends HttpServlet {
	/**
     * GET 요청을 통해 공부 세션의 상세 정보 페이지를 반환합니다.
     * 요청된 공부 세션의 ID를 기반으로 상세 정보와 댓글 목록을 조회하여 사용자에게 제공합니다.
     *
     * @param req 클라이언트로부터 받은 HttpServletRequest 객체, 요청 데이터 접근에 사용됩니다.
     * @param resp 클라이언트에게 보낼 HttpServletResponse 객체, 응답 데이터 설정에 사용됩니다.
     * @throws ServletException 서블릿 처리 중 예외가 발생할 경우 던져집니다.
     * @throws IOException 입출력 예외 발생시 던져집니다.
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String std_seq = req.getParameter("std_seq");
		String column = req.getParameter("column");
		String word = req.getParameter("word");
		String page = req.getParameter("page");
		
		StudyDAO dao = new StudyDAO();
		
		//조회수 증가
		if(session.getAttribute("read")!=null&& session.getAttribute("read").toString().equals("n")) {
			dao.updateReadcount(std_seq);
			session.setAttribute("read", "y");
		}
		
		StudyDTO dto = dao.getStudy(std_seq);
		
		String subject = dto.getStd_title();
		subject = subject.replace(">", "&gt;").replace("<", "&lt;");
		dto.setStd_title(subject);
		
		String content = dto.getStd_content();
		//글내용 > 태그 > 이스케이프
		content = content.replace(">", "&gt;").replace("<", "&lt;");
		dto.setStd_content(content);
		
		//날짜 자르기
		String duedate = dto.getStd_duedate().substring(5, 10);
		dto.setStd_duedate(duedate);
		String regdate = dto.getStd_regdate().substring(0, 10);
		dto.setStd_regdate(regdate);
		
		//댓글 목록 가져오기
		ArrayList<CommentDTO> clist = dao.listComment(std_seq);
	
		req.setAttribute("dto", dto);
		req.setAttribute("column", column);
		req.setAttribute("word", word);
		req.setAttribute("page", page);
		req.setAttribute("clist", clist);

		dao.close();
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/study/viewstudy.jsp");
		dispatcher.forward(req, resp);

	}

}
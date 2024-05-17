package com.good.board.qna;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.board.model.QnaBoardDTO;
import com.good.board.repository.QnaBoardDAO;

/**
 * QnA 게시글 수정을 처리하는 서블릿 클래스입니다.
 * "/user/qna/editqna.do" URL 패턴에 매핑됩니다.
 */
@WebServlet("/user/qna/editqna.do")
public class EditQna extends HttpServlet{
	
	 /**
     * GET 요청을 처리합니다.
     * QnA 게시글 수정 권한을 확인하고, 수정할 게시글 정보를 조회하여 수정 페이지로 전달합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외
     * @throws IOException      입출력 예외
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
//		//접근 권한
//		if (!"2".equals((String) session.getAttribute("lv"))) {
//		    PrintWriter writer = resp.getWriter();
//		    writer.print("<script>alert('접근권한이 없습니다.');location.href='/good/main.do';</script>");
//		    writer.close();
//		}
		
		String qna_seq = req.getParameter("qna_seq");
		
		//인증
		if(AuthQna.check(req, resp)) {
			return;
		}
		
		
		
		
		QnaBoardDAO dao = new QnaBoardDAO();
		
		QnaBoardDTO dto = dao.getQna(qna_seq);
		
		req.setAttribute("dto", dto);
		
		dao.close();
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/qna/editqna.jsp");
		dispatcher.forward(req, resp);
		
		
		
	}
	
	/**
     * POST 요청을 처리합니다.
     * 사용자가 수정한 QnA 게시글 정보를 받아와 데이터베이스에 업데이트하고,
     * 수정 결과에 따라 해당 게시글로 이동하거나 실패 메시지를 표시합니다.
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
		String qna_seq = req.getParameter("qna_seq");
		
		System.out.println(cp_name);
		
		QnaBoardDTO dto = new QnaBoardDTO();
		dto.setId(id);
		dto.setQna_title(qna_title);
		dto.setCp_name(cp_name);
		dto.setQna_content(qna_content);
		dto.setQna_seq(Integer.parseInt(qna_seq));
		
		
		QnaBoardDAO dao = new QnaBoardDAO();
		int result = dao.edit(dto);
		
		if(result == 1) {
			
			resp.sendRedirect("/good/user/qna/qnaview.do?qna_seq="+qna_seq);
			
		} else {
			
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

package com.good.board.comment;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.board.model.StdCommentDTO;
import com.good.board.repository.StdCommentDAO;

@WebServlet("/study.do")
public class StdComment extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/study/study.jsp");
        dispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // POST 요청을 처리하여 댓글을 추가하는 코드를 작성합니다.
        
    	HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");		
    	String setSTD_CM_CONTENT = request.getParameter("commentContent"); // 댓글 내용을 가져옵니다. 이 이름은 JSP에서 textarea의 name 속성과 일치해야 합니다.        
        String setSTD_CM_REGDATE = request.getParameter("currentDate");
        
        
        
        // 여기에 댓글을 데이터베이스에 추가하는 코드를 작성합니다.
        // CommentDAO 클래스를 사용하여 데이터베이스에 댓글을 추가하는 예제입니다.
        StdCommentDTO dto = new StdCommentDTO();
        StdCommentDAO dao = new StdCommentDAO();
        
        
        dto.setID(id);
        dto.setSTD_CM_CONTENT(setSTD_CM_CONTENT);
        dto.setSTD_CM_REGDATE(setSTD_CM_REGDATE);
        //글번호
        
        
        dao.addComment(dto); // 댓글을 데이터베이스에 추가합니다.

        // 댓글을 추가한 후에는 다시 해당 페이지로 리다이렉트합니다.
        response.sendRedirect(request.getContextPath() + "/study.do");
    }
}

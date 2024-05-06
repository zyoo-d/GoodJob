package com.good.board.comment;

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
import com.good.board.repository.CommentDAO;
import com.google.gson.Gson;

@WebServlet("/user/comment/addcomment.do")
public class AddComment extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/study/study.jsp");
        dispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // POST 요청을 처리하여 댓글을 추가하는 코드를 작성합니다.
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("id");
        String setSTD_CM_CONTENT = request.getParameter("content");
        String STD_SEQ = request.getParameter("std_seq");

        // 댓글을 데이터베이스에 추가하는 코드를 작성합니다.
        CommentDTO dto = new CommentDTO();
        CommentDAO dao = new CommentDAO();

        dto.setID(id);
        dto.setSTD_CM_CONTENT(setSTD_CM_CONTENT);
        dto.setSTD_SEQ(STD_SEQ);

        
        int result = dao.addComment(dto); // 댓글을 데이터베이스에 추가합니다. 성공 1
        System.out.println(result);
  
    }
}

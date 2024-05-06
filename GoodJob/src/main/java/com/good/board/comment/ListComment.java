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

@WebServlet("/user/comment/Listcomment.do")
public class ListComment extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/study/study.jsp");
        dispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // POST 요청을 처리하여 댓글을 추가하는 코드를 작성합니다.
 
        String STD_SEQ = request.getParameter("std_seq");

        // 댓글을 데이터베이스에 추가하는 코드를 작성합니다.
        CommentDTO dto = new CommentDTO();
        CommentDAO dao = new CommentDAO();



        dto.setSTD_SEQ(STD_SEQ);


        // 댓글을 다시 불러옵니다.
        CommentDAO commentDAO = new CommentDAO();
        ArrayList<CommentDTO> comments = commentDAO.getComments(STD_SEQ);
        
        System.out.println(comments);
        // JSON 형식으로 댓글 목록을 응답합니다.
        Gson gson = new Gson();
        String jsonComments = gson.toJson(comments);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonComments);
    }
}

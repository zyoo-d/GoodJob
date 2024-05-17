package com.good.board.comment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.good.board.model.CommentDTO;
import com.good.board.repository.CommentDAO;

@WebServlet("/user/comment/Listcomment.do")
public class ListComment extends HttpServlet {
	/**
     * HTTP GET 요청을 처리합니다.
     * 이 메서드는 현재 아무 동작을 하지 않고 JSP 페이지로 포워딩합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException Servlet 관련 에러 발생 시
     * @throws IOException      I/O 에러 발생 시
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/study/study.jsp");
        // JSP 페이지로 포워딩
        dispatcher.forward(req, resp);
    }
    /**
     * HTTP POST 요청을 처리합니다.
     * 게시글 ID를 받아 해당 게시글의 댓글 목록을 조회하여 반환합니다.
     *
     * @param request  HttpServletRequest 객체
     * @param response HttpServletResponse 객체
     * @throws ServletException Servlet 관련 에러 발생 시
     * @throws IOException      I/O 에러 발생 시
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	  // 게시글 ID 가져오기
        String STD_SEQ = request.getParameter("std_seq");

        // CommentDAO를 사용하여 댓글 목록 조회
        CommentDAO dao = new CommentDAO();
       
   
        ArrayList<CommentDTO> comments = dao.getComments(STD_SEQ);
        // 조회한 댓글 목록을 처리하여 응답 데이터로 반환
        // (현재 코드는 구현되지 않음) 
    }
}

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

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/study/study.jsp");
        dispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String STD_SEQ = request.getParameter("std_seq");

        
        CommentDAO dao = new CommentDAO();
       
   
        ArrayList<CommentDTO> comments = dao.getComments(STD_SEQ);
        
        System.out.println(comments);
              
	
		
	
		

    }
}

package com.good.board.study;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.good.board.model.CommentDTO;
import com.good.board.repository.StudyDAO;

@WebServlet("/board/study/stdmorecomment.do")
public class StdMoreComment extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        	
    	int commentBegin = Integer.parseInt(request.getParameter("commentBegin"));
		String bseq = request.getParameter("bseq");
		
		StudyDAO dao = new StudyDAO();
		
		


		
		ArrayList<CommentDTO> morelist = dao.listMoreComment(bseq, commentBegin);
		
		System.out.println(morelist);
		
		
		response.setContentType("application/json");
		
		JSONArray arr = new JSONArray(); //댓글 목록
		
		for (CommentDTO dto : morelist) {
			
			JSONObject obj = new JSONObject(); //댓글 1개
			
			obj.put("seq", dto.getCm_seq());
			obj.put("content", dto.getContent());
			obj.put("id", dto.getId());
			obj.put("name", dto.getNickname());
			obj.put("regdate", dto.getRegdate());
			
			arr.add(obj);			
		}
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		writer.print(arr);
		writer.close();
    }
}
package com.good.board.study;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.good.board.model.CommentDTO;
import com.good.board.repository.StudyDAO;

@WebServlet("/board/study/stdaddcomment.do")
public class StdAddComment extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/study/study.jsp");
		dispatcher.forward(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// POST 요청을 처리하여 댓글을 추가하는 코드를 작성합니다.
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String setSTD_CM_CONTENT = request.getParameter("content");
		String STD_SEQ = request.getParameter("std_seq");
		
		
	
		// 댓글을 데이터베이스에 추가하는 코드를 작성합니다.
		CommentDTO dto = new CommentDTO();
		StudyDAO dao = new StudyDAO();
		
		dto.setId(id);
		dto.setContent(setSTD_CM_CONTENT);
		dto.setBoard_seq(STD_SEQ);

		int result = dao.addComment(dto); // 댓글을 데이터베이스에 추가합니다. 성공 1
		
	

		System.out.println("51");
		System.out.println("51");
		System.out.println("51");
		System.out.println("51");
		System.out.println("51");
		System.out.println("51");
		
		//방금작성한 댓글 가져오기
		CommentDTO dto2 = dao.getComment(STD_SEQ);
		System.out.println(dto2);
		
		
		JSONObject obj = new JSONObject();
		
		JSONObject subObj = new JSONObject();
		subObj.put("STD_CM_SEQ",dto2.getCm_seq());
		subObj.put("STD_CM_CONTENT", dto2.getContent());
		subObj.put("STD_CM_REGDATE", dto2.getRegdate());
		subObj.put("STD_SEQ", dto2.getBoard_seq());
		subObj.put("STD_CM_BSEQ", dto2.getCm_bseq());
		subObj.put("ID", dto2.getId());
		subObj.put("NICKNAME", dto2.getNickname());
		
		obj.put("dto", subObj);
		
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter wr = response.getWriter();
		wr.print(obj); 
		wr.close();
				
}

}
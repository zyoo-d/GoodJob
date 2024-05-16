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
/**
 * StdMoreComment 서블릿 클래스는 사용자가 공부 관련 게시물의 댓글을 추가로 불러올 때의 요청을 처리합니다.
 * 이 서블릿은 "/board/study/stdmorecomment.do" URL 패턴으로 매핑되어 있습니다.
 */
@WebServlet("/board/study/stdmorecomment.do")
public class StdMoreComment extends HttpServlet {
	/**
     * GET 요청을 통해 댓글을 추가로 불러오는 기능을 처리합니다.
     * 사용자가 댓글 더 보기를 요청할 때, 더 많은 댓글을 불러와 JSON 형식으로 반환합니다.
     *
     * @param request 클라이언트로부터 받은 HttpServletRequest 객체, 요청 데이터 접근에 사용됩니다.
     * @param response 클라이언트에게 보낼 HttpServletResponse 객체, 응답 데이터 설정에 사용됩니다.
     * @throws ServletException 서블릿 처리 중 예외가 발생할 경우 던져집니다.
     * @throws IOException 입출력 예외 발생시 던져집니다.
     */
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
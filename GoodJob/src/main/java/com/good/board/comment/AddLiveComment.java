package com.good.board.comment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.good.board.model.CommentDTO;
import com.good.company.repository.CompanyDAO;
import com.good.company.repository.RecruitDAO;

@WebServlet("/board/comment/AddLivecomment.do")
public class AddLiveComment extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");

		String cp_seq = req.getParameter("cp_seq");
		String content = req.getParameter("content");

		System.out.println(cp_seq);
		System.out.println(content);

		CommentDTO cmdto = new CommentDTO();
		CompanyDAO cmdao = new CompanyDAO();

		cmdto.setId(id);
		cmdto.setContent(content);
		cmdto.setCp_seq(cp_seq);

		int cmresult = cmdao.AddLiveComment(cmdto);

		System.out.println(cmresult);

		// 방금작성한 댓글 가져오기
		CommentDTO dto2 = cmdao.getComment(cp_seq);

		JSONObject obj = new JSONObject();

		JSONObject subObj = new JSONObject();
		subObj.put("live_seq", dto2.getCm_seq());
		subObj.put("live_content", dto2.getContent());
		subObj.put("live_cm_regdate", dto2.getRegdate());
		subObj.put("cp_seq", dto2.getBoard_seq());
		subObj.put("id", dto2.getId());
		subObj.put("nickname", dto2.getNickname());

		obj.put("dto", subObj);

		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		PrintWriter wr = resp.getWriter();
		wr.print(obj);
		wr.close();


	}
}

package com.good.board.interview;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.board.model.InterviewDTO;
import com.good.board.qna.AuthQna;
import com.good.board.repository.InterviewDAO;

@WebServlet("/board/interview/itvEdit.do")
public class EditInterview extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		//접근 권한
		if (!"2".equals((String) session.getAttribute("lv"))) {
			resp.setContentType("text/html; charset=UTF-8");
		    resp.setCharacterEncoding("UTF-8");
		    PrintWriter writer = resp.getWriter();
		    writer.print("<script>alert('접근권한이 없습니다.');location.href='/good/main.do';</script>");
		    writer.close();
		}
		
		String itv_seq = req.getParameter("itv_seq");
		
		//인증
		if(AuthQna.check(req, resp)) {
			return;
		}
		
		InterviewDAO dao = new InterviewDAO();
		InterviewDTO dto = dao.getItv(itv_seq);
		
		
		req.setAttribute("dto", dto);
		
		dao.close();
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/interview/edititv.jsp");
		dispatcher.forward(req, resp);
		
		
	
	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		String itv_seq = req.getParameter("itv_seq");
		
		req.setCharacterEncoding("UTF-8");
	    
		String ITV_CPNAME = req.getParameter("itvCpName");
	    String cpSeq = req.getParameter("cp_seq");
	    String itvCareer = req.getParameter("ITV_CAREER");
	    String itvMeetdate = req.getParameter("ITV_MEETDATE");
	    String itvEvaluation = req.getParameter("ITV_EVALUATION");
	    String itvDifficulty = req.getParameter("ITV_DIFFICULTY");
	    String[] itvCategories = req.getParameterValues("ITV_CATEGORY");
	    String itvPersonnel = req.getParameter("ITV_PERSONNEL");
	    String itvQuestion = req.getParameter("ITV_QUESTION");
	    String itvTip = req.getParameter("ITV_TIP");
	    String itvWhether = req.getParameter("ITV_WHETHER");
	

	    InterviewDTO dto = new InterviewDTO();
	    dto.setITV_CPNAME(ITV_CPNAME);
	    dto.setCP_SEQ(cpSeq);
	    dto.setITV_CAREER(itvCareer);
	    dto.setITV_MEETDATE(itvMeetdate);
	    dto.setITV_EVALUATION(itvEvaluation);
	    dto.setITV_DIFFICULTY(itvDifficulty);
	    dto.setITV_CATEGORY(String.join(",", itvCategories));
	    dto.setITV_PERSONNEL(itvPersonnel);
	    dto.setITV_QUESTION(itvQuestion);
	    dto.setITV_TIP(itvTip);
	    dto.setITV_WHETHER(itvWhether);
	    dto.setID(id);
	    dto.setITV_SEQ(itv_seq);
	    
	    InterviewDAO dao = new InterviewDAO();
	    int result = dao.edit(dto);
  
	    if (result > 0) {
	      resp.sendRedirect("/good/interview.do");
	    } else {
	    	System.out.println("interviewWrite 글쓰기 실패");
	      resp.sendRedirect("/good/interview.do");
	    }
		
	
	}
	
}

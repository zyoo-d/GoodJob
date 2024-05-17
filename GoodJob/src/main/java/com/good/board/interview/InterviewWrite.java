package com.good.board.interview;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.board.model.InterviewDTO;
import com.good.board.repository.InterviewDAO;

@WebServlet("/board/interview/itvWrite.do")
public class InterviewWrite extends HttpServlet {


	   /**
	    * HTTP GET 요청을 처리하여 면접 작성 폼을 표시합니다.
	    *
	    * @param req  HttpServletRequest 객체
	    * @param resp HttpServletResponse 객체
	    * @throws ServletException Servlet 관련 에러 발생 시
	    * @throws IOException      I/O 에러 발생 시
	    */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


	        // JSP 페이지로 포워딩
	        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/interview/itvwrite.jsp");
	        dispatcher.forward(req, resp);
	    }
	
	
	
	
	
	/**
	    * HTTP POST 요청을 처리하여 새로운 면접 정보를 작성합니다.
	    *
	    * @param request  HttpServletRequest 객체
	    * @param response HttpServletResponse 객체
	    * @throws ServletException Servlet 관련 에러 발생 시
	    * @throws IOException      I/O 에러 발생 시
	    */
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		request.setCharacterEncoding("UTF-8");
	    
		String ITV_CPNAME = request.getParameter("itvCpName");
	    String cp_seq = request.getParameter("cp_seq");
	    String itvCareer = request.getParameter("ITV_CAREER");
	    String itvMeetdate = request.getParameter("ITV_MEETDATE");
	    String itvEvaluation = request.getParameter("ITV_EVALUATION");
	    String itvDifficulty = request.getParameter("ITV_DIFFICULTY");
	    String[] itvCategories = request.getParameterValues("ITV_CATEGORY");
	    String itvPersonnel = request.getParameter("ITV_PERSONNEL");
	    String itvQuestion = request.getParameter("ITV_QUESTION");
	    String itvTip = request.getParameter("ITV_TIP");
	    String itvWhether = request.getParameter("ITV_WHETHER");
	

	    System.out.println(cp_seq);
	    System.out.println(ITV_CPNAME);
	    System.out.println(itvDifficulty);
	    
	    
	    
	    InterviewDTO dto = new InterviewDTO();
	    dto.setITV_CPNAME(ITV_CPNAME);
	    dto.setCP_SEQ(cp_seq);
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

	    InterviewDAO dao = new InterviewDAO();
	    int result = dao.Write(dto);
	    
	    System.out.println(result);
	    
	    if (result > 0) {
	      response.sendRedirect("/good/user/mypage/myinterview.do");
	    } else {
	    	System.out.println("interviewWrite 글쓰기 실패");
	      response.sendRedirect("/good/board/interview/interview.do");
	    }
	  }
	}
	

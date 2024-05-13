package com.good.admin.interview;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.admin.PageUtil;
import com.good.board.model.InterviewDTO;
import com.good.board.repository.InterviewDAO;



@WebServlet("/admin/interview/interviewhold.do")
public class InterviewHold extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		InterviewDAO dao = new InterviewDAO();

		int totalCount = dao.getTotalHoldInterviewCount();
		int pageSize = 10;
		int currentPage = PageUtil.parseCurrentPage(req.getParameter("page"));


		PageUtil pageUtil = new PageUtil(totalCount, pageSize, currentPage);
		int startIndex = pageUtil.calculateStartIndex();
		int endIndex = pageUtil.calculateEndIndex();

		ArrayList<InterviewDTO> list = dao.getInterviewHold(startIndex, endIndex);

		req.setAttribute("list", list);
		req.setAttribute("pageUtil", pageUtil);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/review/interviewhold.jsp");
		dispatcher.forward(req, resp);

	}

}




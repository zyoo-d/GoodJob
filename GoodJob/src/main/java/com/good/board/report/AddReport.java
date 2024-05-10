package com.good.board.report;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.alert.Alert;


@WebServlet("/user/report/addreport.do")
public class AddReport extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		String id = (String)session.getAttribute("id");
		String seq = req.getParameter("seq");
		String rp_seq = req.getParameter("reason");
		String description = req.getParameter("description");
		String boardType = req.getParameter("boardtype");
		
		System.out.println(description);
		
		if(rp_seq == null || description == null || description.isEmpty()) {
			
			Alert.failReportNull(resp);
		}	
		
		else if(CheckReport.check(id,boardType,seq)) {
			
			Alert.failReport(resp);
			
		} else {
			
			ReportService reportService = new ReportService();
			int result = reportService.createReport(id, seq, rp_seq, description, boardType);
			
			if(result == 1) {
				//성공
			} else {
				//실패
			}
			
			
			
			resp.sendRedirect(req.getHeader("Referer"));
			
		}
		
	
		
	}


	
}

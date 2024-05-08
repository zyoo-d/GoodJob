package com.good.board.report;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.good.board.report.model.QnaReportDTO;
import com.good.board.report.model.StdReportDTO;
import com.good.board.report.repository.QnaReportDAO;
import com.good.board.report.repository.ReportDAO;
import com.good.board.report.repository.StdReportDAO;

@WebServlet("/user/report/addreport.do")
public class AddReport extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		String seq = req.getParameter("seq");
		String reason = req.getParameter("reason");
		String description = req.getParameter("description");
		String boardType = req.getParameter("boardType");
		
		ReportDAO dao;
		
		switch (boardType) {
		case "qna":
			dao = new QnaReportDAO();
			dto = (QnaReportDTO)dao.createDTO();
			break;
		case "std":
			dao = new StdReportDAO();
			
		default:
			break;
		}
		
	
		handleReport(id,seq,reason,description,boardType);
		
		
		
		resp.sendRedirect(req.getHeader("Referer"));
	
		
	}



	private void handleReport(String id, String seq, String reason, String description, String boardType) {
		
		BoardType type = BoardType.valueOf(boardType.toUpperCase());
		String tableName = type.getTableName();
		String sequence = seq + tableName.substring(3)+".nextVal";
		
		try {
			
			String sql = "insert into %s values(sequence, sysdate, ?, ? , ? , (select rp_seq from tblReportType where rp_content = ? ))";
			
			
			
		} catch (Exception e) {
			System.out.println("AddReport.handleReport");
			e.printStackTrace();
		}
		
		
		
	}
	
}

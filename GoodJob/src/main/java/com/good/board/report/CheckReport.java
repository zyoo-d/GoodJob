package com.good.board.report;

import java.io.IOException;

import com.good.board.report.repository.LiveCmReportDAO;
import com.good.board.report.repository.QnaCmReportDAO;
import com.good.board.report.repository.QnaReportDAO;
import com.good.board.report.repository.StdCmReportDAO;
import com.good.board.report.repository.StdReportDAO;


public class CheckReport {
	
	public static boolean check(String id, String boardType, String seq) throws IOException {
		
		int result = 0;
		
		switch (boardType) {
	    case "qna":
	        QnaReportDAO qnaReportDAO = new QnaReportDAO();
	        result = qnaReportDAO.isReport(id, seq);
	        break;
	    case "std":
	        StdReportDAO stdReportDAO = new StdReportDAO();
	        result = stdReportDAO.isReport(id, seq);
	        break;
	    case "qna_cm":
	        QnaCmReportDAO qnaCmReportDAO = new QnaCmReportDAO();
	        result = qnaCmReportDAO.isReport(id, seq);
	        break;
	    case "std_cm":
	        StdCmReportDAO stdCmReportDAO = new StdCmReportDAO();
	        result = stdCmReportDAO.isReport(id, seq);
	        break;
	    case "live":
	        LiveCmReportDAO liveCmReportDAO = new LiveCmReportDAO();
	        result = liveCmReportDAO.isReport(id, seq);
	        break;
	    default:
	        // 잘못된 게시판 타입인 경우 처리
	    	result = 0;
	        break;
	}
		
		if(result==0) {
			return true;
		}
		
		return false;
		
		
		
	}

}

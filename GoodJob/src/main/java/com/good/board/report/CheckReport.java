package com.good.board.report;

import java.io.IOException;

import com.good.board.report.repository.LiveCmReportDAO;
import com.good.board.report.repository.QnaCmReportDAO;
import com.good.board.report.repository.QnaReportDAO;
import com.good.board.report.repository.StdCmReportDAO;
import com.good.board.report.repository.StdReportDAO;


public class CheckReport {
	
	public static boolean check(String id, String boardType, String seq) throws IOException {
		
		switch (boardType) {
	    case "qna":
	        QnaReportDAO qnaReportDAO = new QnaReportDAO();
	        return qnaReportDAO.isReported(id, seq);
	    case "std":
	        StdReportDAO stdReportDAO = new StdReportDAO();
	        return stdReportDAO.isReported(id, seq);
	    case "qna_cm":
	        QnaCmReportDAO qnaCmReportDAO = new QnaCmReportDAO();
	        return qnaCmReportDAO.isReported(id, seq);
	    case "std_cm":
	        StdCmReportDAO stdCmReportDAO = new StdCmReportDAO();
	        return stdCmReportDAO.isReported(id, seq);
	    case "live":
	        LiveCmReportDAO liveCmReportDAO = new LiveCmReportDAO();
	        return liveCmReportDAO.isReported(id, seq);
	    default:
	        // 잘못된 게시판 타입인 경우 처리
	    	return true;
	}
		
	}
	
	
	
	
	

}

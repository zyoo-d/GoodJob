package com.good.board.report;

import java.io.IOException;

import com.good.board.report.repository.LiveCmReportDAO;
import com.good.board.report.repository.QnaCmReportDAO;
import com.good.board.report.repository.QnaReportDAO;
import com.good.board.report.repository.ReportDAO;
import com.good.board.report.repository.StdCmReportDAO;
import com.good.board.report.repository.StdReportDAO;

/**
 * 신고 여부를 확인하는 유틸리티 클래스입니다.
 */
public class CheckReport {
	
	/**
     * 사용자가 해당 게시물을 이미 신고했는지 확인합니다.
     *
     * @param id        사용자 ID
     * @param boardType 게시판 타입
     * @param seq       게시물 번호
     * @return 사용자가 해당 게시물을 이미 신고한 경우 true, 그렇지 않은 경우 false
     * @throws IOException 입출력 예외
     */
	public static boolean check(String id, String boardType, String seq) throws IOException {
		
		
		ReportDAO<?> reportDAO = null;
		
		switch (boardType) {
	    case "qna":
	        reportDAO = new QnaReportDAO();
	        break; 
	    case "std":
	    	reportDAO = new StdReportDAO();
	        break; 
	    case "qna_cm":
	    	reportDAO = new QnaCmReportDAO();
	        break; 
	    case "std_cm":
	    	reportDAO = new StdCmReportDAO();
	        break;
	    case "live":
	    	reportDAO = new LiveCmReportDAO();
	        break; 
	    default:
	        // 잘못된 게시판 타입인 경우 처리
	    	return true;
	}
		return reportDAO.isReported(id, seq);
		
	}
	
	
	
	
	

}

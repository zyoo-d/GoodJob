package com.good.board.report;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.good.alert.Alert;

/**
 * 신고 등록을 처리하는 서블릿 클래스입니다.
 * "/user/report/addreport.do" URL 패턴에 매핑됩니다.
 */
@WebServlet("/user/report/addreport.do")
public class AddReport extends HttpServlet{

	/**
     * POST 요청을 처리합니다.
     * 사용자가 입력한 신고 정보를 받아와서 신고를 등록하는 작업을 수행합니다.
     * 신고 등록 전에 필수 입력 필드 검사와 중복 신고 여부를 확인합니다.
     * 신고 등록 성공 시 이전 페이지로 리다이렉트합니다.
     *
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException 서블릿 예외
     * @throws IOException      입출력 예외
     */
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

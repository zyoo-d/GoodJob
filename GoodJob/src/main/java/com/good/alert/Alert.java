package com.good.alert;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Alert {

	public static void needLogin(HttpServletResponse resp) throws IOException {
		PrintWriter writer = resp.getWriter();
		writer.println("<html><head><meta charset=\"UTF-8\"><title>Access Denied</title></head><body>");
		writer.println("<script type='text/javascript'>");
		writer.println("alert('로그인 후 이용 가능합니다.');");
		writer.println("</script>");
		writer.println("</body></html>");
		writer.close();
	}
	
	public static void needLogin(HttpServletResponse resp, String link) throws IOException {
		PrintWriter writer = resp.getWriter();
		writer.println("<html><head><meta charset=\"UTF-8\"><title>Access Denied</title></head><body>");
		writer.println("<script type='text/javascript'>");
		writer.println("alert('로그인 후 이용 가능합니다.');");
		writer.println("location.href = \""+ link +"\";");
		writer.println("</script>");
		writer.println("</body></html>");
		writer.close();
	}
	
	public static void fail(HttpServletResponse resp) throws IOException {
		PrintWriter writer = resp.getWriter();
		writer.println("<html><head><meta charset=\"UTF-8\"><title>Access Denied</title></head><body>");
		writer.println("<script type='text/javascript'>");
		writer.println("alert('오류가 발생했습니다. 다시 시도해주세요.');");
		writer.println("</script>");
		writer.println("</body></html>");
		writer.close();
	}
	
	public static void fail(HttpServletResponse resp, String link) throws IOException {
		PrintWriter writer = resp.getWriter();
		writer.println("<html><head><meta charset=\"UTF-8\"><title>Access Denied</title></head><body>");
		writer.println("<script type='text/javascript'>");
		writer.println("alert('오류가 발생했습니다. 다시 시도해주세요.');");
		writer.println("location.href = \""+ link +"\";");
		writer.println("</script>");
		writer.println("</body></html>");
		writer.close();
	}
	
	public static void noPermission(HttpServletResponse resp) throws IOException {
		PrintWriter writer = resp.getWriter();
		writer.println("<html><head><meta charset=\"UTF-8\"><title>Access Denied</title></head><body>");
		writer.println("<script type='text/javascript'>");
		writer.println("alert('접근 권한이 없습니다.');");
		writer.println("</script>");
		writer.println("</body></html>");
		writer.close();
	}
	
	public static void noPermission(HttpServletResponse resp, String link) throws IOException {
		PrintWriter writer = resp.getWriter();
		writer.println("<html><head><meta charset=\"UTF-8\"><title>Access Denied</title></head><body>");
		writer.println("<script type='text/javascript'>");
		writer.println("alert('접근 권한이 없습니다.');");
		writer.println("location.href = \""+ link +"\";");
		writer.println("</script>");
		writer.println("</body></html>");
		writer.close();
	}
	
	public static void deleteSuccess(HttpServletResponse resp) throws IOException{
		
		PrintWriter writer = resp.getWriter();
		writer.println("<html><head><meta charset=\"UTF-8\"><title>Access Denied</title></head><body>");
		writer.println("<script type='text/javascript'>");
		writer.println("alert('삭제가 완료되었습니다.');");
		writer.println("</script>");
		writer.println("</body></html>");
		writer.close();
		
		
		
	}
	
	public static void failReport(HttpServletResponse resp) throws IOException {
	    PrintWriter writer = resp.getWriter();
	    writer.println("<html><head><meta charset=\"UTF-8\"><title>Access Denied</title></head><body>");
	    writer.println("<script type='text/javascript'>");
	    writer.println("alert('이미 신고한 게시글입니다.');");
	    writer.println("history.back();"); // 이전 페이지로 이동
	    writer.println("if (window.opener) {"); // 부모 창(모달창을 연 창)이 있는 경우
	    writer.println("    window.opener.hideModal();"); // 부모 창에서 hideModal() 함수 호출
	    writer.println("}");
	    writer.println("</script>");
	    writer.println("</body></html>");
	    writer.close();
	}
	
	public static void failReportNull(HttpServletResponse resp) throws IOException {
	    PrintWriter writer = resp.getWriter();
	    writer.println("<html><head><meta charset=\"UTF-8\"><title>Access Denied</title></head><body>");
	    writer.println("<script type='text/javascript'>");
	    writer.println("alert('신고 유형 및 사유를 모두 입력하세요.');");
	    writer.println("history.back();"); // 이전 페이지로 이동
	    writer.println("</script>");
	    writer.println("</body></html>");
	    writer.close();
	}
	
	
}

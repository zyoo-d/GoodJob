package com.good.alert;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Alert {

	public static void needLogin(HttpServletResponse resp) throws IOException {
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.println("<html><head><meta charset=\"UTF-8\"><title>Access Denied</title></head><body>");
		writer.println("<script type='text/javascript'>");
		writer.println("alert('로그인 후 이용 가능합니다.');");
		writer.println("</script>");
		writer.println("</body></html>");
		writer.close();
	}
	
	public static void needLogin(HttpServletResponse resp, String link) throws IOException {
		resp.setCharacterEncoding("UTF-8");
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
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.println("<html><head><meta charset=\"UTF-8\"><title>Access Denied</title></head><body>");
		writer.println("<script type='text/javascript'>");
		writer.println("alert('오류가 발생했습니다. 다시 시도해주세요.');");
		writer.println("</script>");
		writer.println("</body></html>");
		writer.close();
	}
	
	public static void fail(HttpServletResponse resp, String link) throws IOException {
		resp.setCharacterEncoding("UTF-8");
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
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.println("<html><head><meta charset=\"UTF-8\"><title>Access Denied</title></head><body>");
		writer.println("<script type='text/javascript'>");
		writer.println("alert('접근 권한이 없습니다.');");
		writer.println("</script>");
		writer.println("</body></html>");
		writer.close();
	}
	
	public static void noPermission(HttpServletResponse resp, String link) throws IOException {
		resp.setCharacterEncoding("UTF-8");
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
	
	
}

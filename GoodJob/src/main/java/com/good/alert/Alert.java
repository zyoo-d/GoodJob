package com.good.alert;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * Alert 클래스는 웹 응답에 경고 창을 표시하는 기능을 제공합니다.
 */
public class Alert {

	/**
	 * 사용자에게 로그인이 필요함을 알리는 경고창을 표시합니다.
	 * 
	 * @param resp HttpServletResponse 객체로, 사용자에게 경고창을 보낼 때 사용됩니다.
	 * @throws IOException 입출력 예외 발생시 예외를 던집니다.
	 */
	public static void needLogin(HttpServletResponse resp) throws IOException {
		PrintWriter writer = resp.getWriter();
		writer.println("<html><head><meta charset=\"UTF-8\"><title>Access Denied</title></head><body>");
		writer.println("<script type='text/javascript'>");
		writer.println("alert('로그인 후 이용 가능합니다.');");
		writer.println("</script>");
		writer.println("</body></html>");
		writer.close();
	}

	/**
	 * 사용자에게 로그인이 필요함을 알리고, 지정된 URL로 리다이렉트합니다.
	 * 
	 * @param resp HttpServletResponse 객체로, 사용자에게 경고창을 보낼 때 사용됩니다.
	 * @param link 사용자를 리다이렉트할 URL입니다.
	 * @throws IOException 입출력 예외 발생시 예외를 던집니다.
	 */
	public static void needLogin(HttpServletResponse resp, String link) throws IOException {
		PrintWriter writer = resp.getWriter();
		writer.println("<html><head><meta charset=\"UTF-8\"><title>Access Denied</title></head><body>");
		writer.println("<script type='text/javascript'>");
		writer.println("alert('로그인 후 이용 가능합니다.');");
		writer.println("location.href = \"" + link + "\";");
		writer.println("</script>");
		writer.println("</body></html>");
		writer.close();
	}

	/**
	 * 일반적인 오류를 사용자에게 알리는 경고창을 표시합니다.
	 * 
	 * @param resp HttpServletResponse 객체로, 사용자에게 경고창을 보낼 때 사용됩니다.
	 * @throws IOException 입출력 예외 발생시 예외를 던집니다.
	 */
	public static void fail(HttpServletResponse resp) throws IOException {
		PrintWriter writer = resp.getWriter();
		writer.println("<html><head><meta charset=\"UTF-8\"><title>Access Denied</title></head><body>");
		writer.println("<script type='text/javascript'>");
		writer.println("alert('오류가 발생했습니다. 다시 시도해주세요.');");
		writer.println("</script>");
		writer.println("</body></html>");
		writer.close();
	}

	/**
	 * 일반적인 오류를 알리고 지정된 URL로 사용자를 리다이렉트합니다.
	 * 
	 * @param resp HttpServletResponse 객체로, 사용자에게 경고창을 보낼 때 사용됩니다.
	 * @param link 사용자를 리다이렉트할 URL입니다.
	 * @throws IOException 입출력 예외 발생시 예외를 던집니다.
	 */
	public static void fail(HttpServletResponse resp, String link) throws IOException {
		PrintWriter writer = resp.getWriter();
		writer.println("<html><head><meta charset=\"UTF-8\"><title>Access Denied</title></head><body>");
		writer.println("<script type='text/javascript'>");
		writer.println("alert('오류가 발생했습니다. 다시 시도해주세요.');");
		writer.println("location.href = \"" + link + "\";");
		writer.println("</script>");
		writer.println("</body></html>");
		writer.close();
	}

	/**
	 * 접근 권한이 없는 사용자에게 알리는 경고창을 표시합니다.
	 * 
	 * @param resp HttpServletResponse 객체로, 사용자에게 경고창을 보낼 때 사용됩니다.
	 * @throws IOException 입출력 예외 발생시 예외를 던집니다.
	 */
	public static void noPermission(HttpServletResponse resp) throws IOException {
		PrintWriter writer = resp.getWriter();
		writer.println("<html><head><meta charset=\"UTF-8\"><title>Access Denied</title></head><body>");
		writer.println("<script type='text/javascript'>");
		writer.println("alert('접근 권한이 없습니다.');");
		writer.println("</script>");
		writer.println("</body></html>");
		writer.close();
	}

	/**
	 * 접근 권한 오류를 알리고 지정된 URL로 사용자를 리다이렉트합니다.
	 * 
	 * @param resp HttpServletResponse 객체로, 사용자에게 경고창을 보낼 때 사용됩니다.
	 * @param link 사용자를 리다이렉트할 URL입니다.
	 * @throws IOException 입출력 예외 발생시 예외를 던집니다.
	 */
	public static void noPermission(HttpServletResponse resp, String link) throws IOException {
		PrintWriter writer = resp.getWriter();
		writer.println("<html><head><meta charset=\"UTF-8\"><title>Access Denied</title></head><body>");
		writer.println("<script type='text/javascript'>");
		writer.println("alert('접근 권한이 없습니다.');");
		writer.println("location.href = \"" + link + "\";");
		writer.println("</script>");
		writer.println("</body></html>");
		writer.close();
	}

	/**
	 * 항목 삭제가 성공적으로 완료되었음을 사용자에게 알리는 경고창을 표시합니다.
	 * 
	 * @param resp HttpServletResponse 객체로, 사용자에게 경고창을 보낼 때 사용됩니다.
	 * @throws IOException 입출력 예외 발생시 예외를 던집니다.
	 */
	public static void deleteSuccess(HttpServletResponse resp) throws IOException {
		PrintWriter writer = resp.getWriter();
		writer.println("<html><head><meta charset=\"UTF-8\"><title>Access Denied</title></head><body>");
		writer.println("<script type='text/javascript'>");
		writer.println("alert('삭제가 완료되었습니다.');");
		writer.println("</script>");
		writer.println("</body></html>");
		writer.close();
	}

	/**
	 * 이미 신고된 게시물에 대한 추가 신고 시도를 사용자에게 알리는 경고창을 표시합니다.
	 * 
	 * @param resp HttpServletResponse 객체로, 사용자에게 경고창을 보낼 때 사용됩니다.
	 * @throws IOException 입출력 예외 발생시 예외를 던집니다.
	 */
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

	/**
	 * 미입력 정보 관련 경고창을 표시합니다.
	 * 
	 * @param resp HttpServletResponse 객체로, 사용자에게 경고창을 보낼 때 사용됩니다.
	 * @throws IOException 입출력 예외 발생시 예외를 던집니다.
	 */
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

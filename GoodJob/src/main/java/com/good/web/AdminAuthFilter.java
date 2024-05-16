package com.good.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 관리자 권한을 확인하는 필터 클래스입니다.
 * "/admin/*" URL 패턴에 매핑되어 있습니다.
 */
@WebFilter(filterName = "1.AdminAuthFilter", urlPatterns = "/admin/*")
public class AdminAuthFilter implements Filter {

	 /**
     * 필터 체인을 통해 요청을 처리합니다.
     * 관리자 권한이 있는 경우에만 요청을 통과시키고, 그렇지 않은 경우 메인 페이지로 리다이렉트합니다.
     *
     * @param request  ServletRequest 객체
     * @param response ServletResponse 객체
     * @param chain    FilterChain 객체
     * @throws IOException      입출력 예외
     * @throws ServletException 서블릿 예외
     */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		 HttpServletRequest httpRequest = (HttpServletRequest) request;
	     HttpServletResponse httpResponse = (HttpServletResponse) response;
		
	     
	     HttpSession session = httpRequest.getSession(false);

	        if (session != null) {
	            String lv = (String) session.getAttribute("lv");
	            if (lv != null && lv.equals("2")) {
	                chain.doFilter(request, response);
	                return;
	            }
	        }
	        
	     httpResponse.sendRedirect("/good/main.do");   
		
	}

}

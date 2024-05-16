package com.good.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * 요청과 응답의 인코딩을 설정하는 필터 클래스입니다.
 * "*.do" URL 패턴에 매핑되어 있습니다.
 */
@WebFilter(filterName = "2.EncodingFilter", urlPatterns = "*.do")
public class EncodingFilter implements Filter{

	
	 /**
     * 필터 체인을 통해 요청을 처리합니다.
     * 요청과 응답의 인코딩을 UTF-8로 설정한 후 요청을 통과시킵니다.
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
			
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("UTF-8");

			chain.doFilter(request, response);
			
		}
	
}

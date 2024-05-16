package com.good.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.good.admin.visitor.IpTracker;
import com.good.admin.visitor.VisitorTracker;

/**
* VisitorTrackingFilter 클래스는 Filter 인터페이스를 구현하여 웹 필터로 동작한다.
* @WebFilter 애노테이션으로 필터 이름과 URL 패턴을 지정한다.
*/
@WebFilter(filterName = "3.VisitorTrackingFilter", urlPatterns = "*.do")
public class VisitorTrackingFilter implements Filter{

	/**
	    * 필터 체인을 통해 요청을 처리합니다.
	    * 관리자 권한이 있는 경우에만 요청을 통과시키고, 그렇지 않으면 메인 페이지로 리다이렉트합니다.
	    *
	    * @param request ServletRequest 객체입니다.
	    * @param response ServletResponse 객체입니다.
	    * @param chain FilterChain 객체입니다.
	    * @throws IOException 입출력 예외입니다.
	    * @throws ServletException 서블릿 예외입니다.
	    */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest resq = (HttpServletRequest)request;
		String ipAddress = resq.getRemoteAddr();

		if(IpTracker.trackIp(ipAddress)) {
			System.out.println("IP:" + ipAddress + " 방문자수 증가");
			VisitorTracker.getInstance().incrementVisitors();
		}
		
		chain.doFilter(request, response);
		
	}
	
}

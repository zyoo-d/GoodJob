package com.good.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.good.admin.IpTracker;
import com.good.admin.VisitorTracker;

@WebFilter(filterName = "3.VisitorTrackingFilter", urlPatterns = "*.do")
public class VisitorTrackingFilter implements Filter{

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

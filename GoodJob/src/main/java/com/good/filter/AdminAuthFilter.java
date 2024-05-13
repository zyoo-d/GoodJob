package com.good.filter;

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

@WebFilter(filterName = "1.AdminAuthFilter", urlPatterns = "/admin/*")
public class AdminAuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		 HttpServletRequest httpRequest = (HttpServletRequest) request;
	     HttpServletResponse httpResponse = (HttpServletResponse) response;
		
	     
	     HttpSession session = httpRequest.getSession(false);

	        if (session != null) {
	            Integer lv = (Integer) session.getAttribute("lv");
	            if (lv != null && lv == 2) {
	                chain.doFilter(request, response);
	                return;
	            }
	        }
	        
	     httpResponse.sendRedirect("/good/main.do");   
		
	}

}

package com.javaproject.examplecollect.customresubmit;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomReSubmitFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		try {
			request = (HttpServletRequest)arg0;
			response = (HttpServletResponse)arg1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String token = request.getParameter("token");
		System.out.println("进入");
		if (token != null && !token.equals("")) {
			String sessToken = (String)request.getSession().getAttribute("token");
			if (sessToken != null && sessToken.equals(token)) {
				request.getSession().removeAttribute("token");
				System.out.println("删除token");
				chain.doFilter(request, response);
			}else{
				response.getWriter().write("be careful is resubmit");
			}
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}

package com.web.example.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class GetParameterFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		try {
			request = (HttpServletRequest)req;
			response = (HttpServletResponse)resp;
		} catch (Exception e) {
			throw new RuntimeException("not request and response");
		}
		
		if (request.getMethod().equalsIgnoreCase("get")) {
			MyHttpServletRequest myRequest = new MyHttpServletRequest(request);
			filterChain.doFilter(myRequest, response);
			System.out.println("get过滤器,tomcat是默认编码时使用");
			return;
		}
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	class MyHttpServletRequest extends HttpServletRequestWrapper{
		public MyHttpServletRequest(HttpServletRequest request) {
			super(request);
		}
		@Override
		public String getParameter(String name) {
			String value = super.getParameter(name);
			if(value == null) return null;
			try {
//				return new String(value.getBytes("iso-8859-1"),"utf-8");	//tomcat是默认编码时使用
				return value;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("MyHttpServletRequest包装类getParameter异常");
			}
		}
		
	}

}

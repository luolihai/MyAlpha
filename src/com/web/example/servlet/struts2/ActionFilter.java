package com.web.example.servlet.struts2;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		//作处理
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		try {
			request = (HttpServletRequest)arg0;
			response = (HttpServletResponse)arg1;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		ServletContext requestServletContext = request.getServletContext();
		System.out.println("requestServletContext："+requestServletContext);
		
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		String uriSub = ServletUtil.parse(uri);
		Map<String, String> map = (Map<String, String>)requestServletContext.getAttribute("map");
		
		try {
			Class clazz = Class.forName(map.get(uriSub));
			Method method = clazz.getMethod("execu", HttpServletRequest.class,HttpServletResponse.class);
			String invoke = (String)method.invoke(clazz.newInstance(), request,response);
			System.out.println("invoke:"+invoke);
			request.getRequestDispatcher(invoke).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
 		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}

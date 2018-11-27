package com.web.example.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaproject.examplecollect.base64.Base64Demo;
import com.javaproject.examplecollect.base64.MD5Util;
import com.web.oa.bean.User;

public class AutoLoginFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		try {
			request = (HttpServletRequest)req;
			response = (HttpServletResponse)resp;
		} catch (Exception e) {
			throw new RuntimeException("not request and response");
		}
		
		if (request.getParameter("logout") != null) {
			String cookiename = "autoLogin";
			HttpSession session = request.getSession();
			//自动登录
			if (session == null || session.getAttribute(cookiename) == null) {
				Cookie[] cookies = request.getCookies();
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals(cookiename)) {
						String[] values = cookies[i].getValue().split("_");
						String username = values[0];
						String password = values[1];
					
						User user = new User();
						user.setId(1);
						user.setUsername(Base64Demo.decode(username, "utf-8"));
						user.setPassword(password);
						
						if (username.equals(MD5Util.md5("jack")) && password.equals(Base64Demo.encode("123"))) {
							session.setAttribute("user", user);
							System.out.println("自动登录 ");
							break;
						}
					}
				}
			}
		}
		
		System.out.println("走完自动登录过滤器");
		chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}

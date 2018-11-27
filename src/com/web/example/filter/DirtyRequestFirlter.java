package com.web.example.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

/**
 * Servlet Filter implementation class DirtyRequestFirlter
 */
//@WebFilter(urlPatterns = "/*")
public class DirtyRequestFirlter implements Filter {

    /**
     * Default constructor. 
     */
    public DirtyRequestFirlter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		try {
			request = (HttpServletRequest)req;
			response = (HttpServletResponse)resp;
		} catch (Exception e) {
			throw new RuntimeException("not request and response");
		}
		System.out.println("进入脏文过滤器");
		MyDirtyHttpServletRequest myDirtyRequest = new MyDirtyHttpServletRequest(request);
		chain.doFilter(myDirtyRequest, response);
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}
	
	
	class MyDirtyHttpServletRequest extends HttpServletRequestWrapper{
		String[] strs = new String[]{"傻逼","傻蛋"};

		public MyDirtyHttpServletRequest(HttpServletRequest request) {
			super(request);
		}

		@Override
		public String getParameter(String name) {
			String value = super.getParameter(name);
			if(value == null) return null;
			for (int i = 0; i < strs.length; i++) {
				if (value.contains(strs[i])) {
					value = value.replaceAll(strs[i], "*");
				}
			}
			System.out.println("过滤后："+value);
			return value;
		}
	}
	
	@Test
	public void test(){
		String[] strs = new String[]{"傻逼","傻蛋"};
		String value = "傻逼做事";
		for (int i = 0; i < strs.length; i++) {
			if (value.equals(strs[i])) {
				value = value.replaceAll(strs[i], "*");
			}
		}
		System.out.println(value.replaceAll("傻逼", "*"));
	}

}

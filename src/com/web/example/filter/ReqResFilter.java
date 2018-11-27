package com.web.example.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 编码过虑
 * 设置动态缓存时间
 * 设置静态缓存时间
 * @author 26031
 *
 */
public class ReqResFilter implements Filter{

	private FilterConfig filterConfig;
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		try {
			request = (HttpServletRequest)req;
			response = (HttpServletResponse)res;
		} catch (Exception e) {
			throw new RuntimeException("filter 转换 req,res出错");
		}
		
		//编码过滤
		String encoding = filterConfig.getInitParameter("encoding");//web.xml filter配置中获取
		request.setCharacterEncoding(encoding);
		response.setContentType("text/html;charset="+encoding);
		
		//动态缓存 
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", -1);
		
		//设置静态缓存时间
		String css = filterConfig.getInitParameter("css");
		String js = filterConfig.getInitParameter("js");
		
		String requestURI = request.getRequestURI();
		System.out.println(requestURI);
		String pre = requestURI.substring(requestURI.lastIndexOf(".")+1);
		
		long time = 0;//缓存时间
		if (pre.equals("css")) {
			time = Long.valueOf(css)*60*60*1000;
		}
		if(pre.equals("js")) {
			time = Long.valueOf(js)*60*60*1000;
		}
		response.setDateHeader("Expires", System.currentTimeMillis()+time);
		chain.doFilter(req, res);
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

}

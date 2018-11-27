package com.web.example.rewriterequest;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName="RequestFilter",urlPatterns="/*")
public class RequestFilter implements Filter {

    public RequestFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		request.setAttribute("other", "can bb");
		request.setAttribute("other1", "can bb1");
		
		HttpServletRequest req = (HttpServletRequest)request;
		MyRequestWrap myRequestWrap = new MyRequestWrap(req);
		myRequestWrap.insertOrUpdate("other", new String[]{"yes you are right:bb"});
		myRequestWrap.insertOrUpdate("other1", new String[]{"yes you are right:bb1"});
		
		chain.doFilter(myRequestWrap, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}

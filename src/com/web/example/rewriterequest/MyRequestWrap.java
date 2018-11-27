package com.web.example.rewriterequest;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyRequestWrap extends HttpServletRequestWrapper{

	private Map<String, String[]> paramMap = null;
	public MyRequestWrap(HttpServletRequest request) {
		super(request);
		paramMap = new HashMap<String, String[]>();
		paramMap.putAll(request.getParameterMap());
	}
	
	@Override
	public String getParameter(String name) {
		String[] strs = paramMap.get(name);
		if (strs == null || strs.length == 0) {
			return null;
		}
		return strs[0];
	}
	@Override
	public Enumeration<String> getParameterNames() {
		return new Enumeration<String>() {
			private Iterator<String> iterator = paramMap.keySet().iterator();
			@Override
			public boolean hasMoreElements() {
				return iterator.hasNext();
			}

			@Override
			public String nextElement() {
				return iterator.next();
			}
		};
	}
	
	
	@Override
	public Map<String, String[]> getParameterMap() {
		return paramMap;
	}
	
	@Override
	public String[] getParameterValues(String name) {
		return paramMap.get(name);
	}
	//可以自由修改的值方法
	public void insertOrUpdate(String name,String[] value){
		paramMap.put(name, value);
	}
	
}

package com.web.example.servlet.struts2;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ActionListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		//准备数据
		Map<String, String> map = new HashMap<>();
		map.put("UserAction", "com.web.example.servlet.struts2.UserAction");
		arg0.getServletContext().setAttribute("map", map);
		System.out.println("放入map");
	}

}

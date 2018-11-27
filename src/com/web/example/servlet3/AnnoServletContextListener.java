package com.web.example.servlet3;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AnnoServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("进入liscener");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
	}

}

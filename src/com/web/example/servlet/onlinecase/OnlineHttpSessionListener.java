package com.web.example.servlet.onlinecase;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.web.oa.bean.User;

public class OnlineHttpSessionListener implements HttpSessionAttributeListener{

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		
		HttpSession session = event.getSession();
		ServletContext application = session.getServletContext();
		Map<String, HttpSession> map = (Map<String, HttpSession>)application.getAttribute("map");
		if (map == null) {
			map =new ConcurrentHashMap<String, HttpSession>();
			application.setAttribute("map", map);
		}
		
		//user:com.web.oa.bean.User@50df92ed
		String name = event.getName();
		Object value = event.getValue();
		if (name.equals("user")) {
			User user = (User)value;
			map.put(user.getUsername(), session);
			System.out.println("放入"+user.getUsername());
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		
	}

}

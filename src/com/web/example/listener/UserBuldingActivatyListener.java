package com.web.example.listener;

import java.io.Serializable;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;

/**
 * session中对象的感应监听
 * session添加对象，删除对象
 * 服务器关闭后session钝化及启动的激活
 * 会触发相应接口方法
 * @author 26031
 *
 */
public class UserBuldingActivatyListener implements Serializable,HttpSessionBindingListener,HttpSessionActivationListener{

	
	private String username;
	private String password;
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		System.out.println("UserBuldingActivatyListener实例化放入session后绑定");
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		System.out.println("UserBuldingActivatyListener于session中去除后解绑");
	}

	@Override
	public void sessionDidActivate(HttpSessionEvent arg0) {
		System.out.println("UserBuldingActivatyListener激活监听");
	}

	@Override
	public void sessionWillPassivate(HttpSessionEvent arg0) {
		System.out.println("UserBuldingActivatyListener钝化监听");
	}

}

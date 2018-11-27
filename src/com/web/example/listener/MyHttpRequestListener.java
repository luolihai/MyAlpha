package com.web.example.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyHttpRequestListener implements ServletRequestListener{

	int i = 0;
	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		System.out.println("request失效监听");
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		System.out.println("request生成监听"+(i++)+"次");
	}

}

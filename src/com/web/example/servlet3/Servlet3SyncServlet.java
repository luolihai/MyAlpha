package com.web.example.servlet3;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;

import javax.jws.soap.InitParam;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * servlet3.0新特性
 * 文件上传
 * @author 26031
 *
 */
@WebServlet(urlPatterns="/servlet3SyncServlet",asyncSupported = true)

public class Servlet3SyncServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//这很主要，有时tocmat不支持
		request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write("RegistServlet开始运行了<br/>");
		out.flush();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		out.write("注册成功了<br/>");
		out.flush();
		//发送激活邮件

		AsyncContext ac = request.startAsync();//开始异步
		new Thread(new SendMail(ac)).start();//3秒
		out.write("RegistServlet运行结束了<br/>");
		out.flush();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
class SendMail implements Runnable{

	private AsyncContext ac;

	public SendMail(AsyncContext ac) {
		this.ac= ac;
	}

	public void run() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//打印信息
		try {
			PrintWriter out = ac.getResponse().getWriter();
			out.write("邮件发送成功!");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

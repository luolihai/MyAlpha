package com.web.example.servlet3;

import java.io.IOException;
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
@WebServlet(value="/servlet3PartServlet")
@MultipartConfig(location="d:/")
public class Servlet3PartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Part part = req.getPart("photo");
		String header = part.getHeader("Content-Disposition");
        String fileName = header.substring(header.indexOf("filename=\"") + 10, header.lastIndexOf("\""));
		part.write(fileName);
		
		sleep(Thread.currentThread(), 3*1000);
		System.out.println("上传要3秒");
		
		AsyncContext startAsync = req.startAsync();
		new Thread(new SendEmail(startAsync)).start();
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	public void sleep(Thread thread,long time){
		try {
			thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	class SendEmail implements Runnable{
		private AsyncContext startAsync;
		public SendEmail(AsyncContext startAsync){
			this.startAsync = startAsync;
		}
		@Override
		public void run() {
			sleep(Thread.currentThread(), 2*1000);
			System.out.println("发送邮件休眠2秒");
			try {
				startAsync.getRequest().getRequestDispatcher("/mult/exampleJsp/servlet3/online.jsp").forward(startAsync.getRequest(), startAsync.getResponse());
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}

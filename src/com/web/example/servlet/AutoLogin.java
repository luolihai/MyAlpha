package com.web.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaproject.examplecollect.base64.Base64Demo;
import com.javaproject.examplecollect.base64.MD5Util;
import com.web.oa.bean.User;

/**
 * 自动登录
 * @author 26031
 *
 */
public class AutoLogin extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String logout = req.getParameter("logout");
		String auto = req.getParameter("auto");
		
		System.out.println(username+":"+password);
		if (username != null && username.equals("jack")) {
			if (password.equals("123")) {
				password = MD5Util.md5(password);	//java提供md5加密
				
				User user = new User();
				user.setId(1);
				user.setPassword(password);
				user.setUsername(username);
				if (auto != null && auto.equals("1")) {
					System.out.println("进入cookie");
					username = Base64Demo.encode(username);//cookie不加放入中文
					
					Cookie cookie = new Cookie("autoLogin", username+"_"+password);
					cookie.setMaxAge(60*60*1000);
					cookie.setPath(req.getContextPath());
					resp.addCookie(cookie);
				}
				req.getSession().setAttribute("user", user);
			}
		}
		
		
		//注销
		if (logout!=null && !logout.equals("")) {
			req.getSession().setAttribute("user", null);
			Cookie cookie = new Cookie("autoLogin", "");
			cookie.setMaxAge(0);
			resp.addCookie(cookie);
			resp.sendRedirect(req.getContextPath()+"/mult/exampleJsp/servlet/autoLogin.jsp");
			return;
		}
		resp.sendRedirect(req.getContextPath()+"/mult/exampleJsp/servlet/autoIndex.jsp");
//		req.getRequestDispatcher("mult/exampleJsp/servlet/autoIndex.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
}

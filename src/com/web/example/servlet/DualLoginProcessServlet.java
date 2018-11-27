package com.web.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.SeekableByteChannel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class DualLoginProcessServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		String username = req.getParameter("username");
		String code = req.getParameter("code");
		PrintWriter out = resp.getWriter();
		if (code == null || code.equals("")) {
			out.print("验证码为空");
			return;
		}
		HttpSession session = req.getSession(false);
		if (session != null) {
			Object sessionCode = session.getAttribute("check_code");
			if (sessionCode != null && !code.equals((String)sessionCode)) {
				out.print("验证码不正确");
				return;
			}
		}
		if (username == null || username.equals("")) {
			out.print("用户名为空");
			return;
		}
		
		out.print("欢迎"+username);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
}

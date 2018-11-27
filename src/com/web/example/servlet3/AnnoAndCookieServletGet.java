package com.web.example.servlet3;

import java.io.IOException;

import javax.jws.soap.InitParam;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * servlet3.0新特性
 * cookie onlyhttp
 * annotation
 * @author 26031
 *
 */
@WebServlet(value="/annoAndCookieServletGet",initParams={@WebInitParam(name="encode",value="utf-8")})
public class AnnoAndCookieServletGet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String encode = getServletConfig().getInitParameter("encode");
		req.setCharacterEncoding(encode);
		resp.setContentType("text/html;charset="+encode);
		
		Cookie[] cookies = req.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals("name")) {
				resp.getWriter().write(cookies[i].getName()+"<==>"+cookies[i].getValue());		
				return;
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	

}

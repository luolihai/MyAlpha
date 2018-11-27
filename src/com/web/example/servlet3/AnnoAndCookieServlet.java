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

@WebServlet(value="/annoAndCookieServlet",initParams={@WebInitParam(name="encode",value="utf-8")})
public class AnnoAndCookieServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String encode = getServletConfig().getInitParameter("encode");
		req.setCharacterEncoding(encode);
		resp.setContentType("text/html;charset="+encode);
		
		Cookie cookie = new Cookie("name", "jack");
		cookie.setMaxAge(Integer.MAX_VALUE);
		cookie.setPath(req.getContextPath());
		//onlyhttp servlet3.0 feature
		cookie.setHttpOnly(true);
		System.out.println("根路径："+req.getContextPath());
		
		resp.addCookie(cookie);
		req.getRequestDispatcher("/mult/exampleJsp/servlet3/online.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	

}

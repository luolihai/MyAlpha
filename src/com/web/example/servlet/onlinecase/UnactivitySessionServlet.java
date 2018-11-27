package com.web.example.servlet.onlinecase;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/unactivitySessionServlet")
public class UnactivitySessionServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//登录 
		String username = req.getParameter("username");
		
		Map<String, HttpSession> map = (Map<String, HttpSession>)req.getSession().getServletContext().getAttribute("map");
		HttpSession mapSession = map.get(username);
		if (mapSession != null) {
			mapSession.invalidate();
			map.remove(username);
		}
		
		//跳转在线
		req.getRequestDispatcher("/mult/exampleJsp/servlet/onlinecase/online.jsp").forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}

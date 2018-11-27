package com.web.example.doublesubmit11;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/doubleSubmitServlet")
public class DoubleSubmitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String reqToken = req.getParameter("token");
		String sessToken = (String)req.getSession().getAttribute("token");
		if (sessToken !=null && sessToken.equals(reqToken)) {
			String name = req.getParameter("name");
			System.out.println(name);
//			resp.sendRedirect(req.getContextPath()+"/mult/exampleJsp/doublesubmit/doublesubmit.jsp");
			resp.getWriter().write("success");
			req.getSession().removeAttribute("token");
		}else{
			resp.getWriter().write("double submit ");
		}
//		req.getRequestDispatcher("mult/exampleJsp/doublesubmit/doublesubmit.jsp").forward(req, resp);
//		resp.sendRedirect(req.getContextPath()+"/mult/exampleJsp/doublesubmit/doublesubmit.jsp");
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
	
	
}

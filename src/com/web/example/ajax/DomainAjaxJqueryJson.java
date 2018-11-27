package com.web.example.ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DomainAjaxJqueryJson
@WebServlet("/DomainAjaxJqueryJson")
 */
public class DomainAjaxJqueryJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("conenction server success!");
		
		System.out.println(request.getMethod());
		
		String json = "{'province':'jilinsheng'}";
		
		String callback = request.getParameter("callback");
		
		System.out.println(callback);
		response.setContentType("text/html");
		response.getWriter().write(callback+"("+json+")");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

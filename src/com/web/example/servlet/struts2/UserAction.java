package com.web.example.servlet.struts2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserAction {

	public String execu(HttpServletRequest request,HttpServletResponse response){
		System.out.println("hello");
		return "/mult/exampleJsp/struts2/struts2.jsp";
	}
}

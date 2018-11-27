package com.javaproject.examplecollect.customresubmit;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ReSubmitTab extends SimpleTagSupport{

	@Override
	public void doTag() throws JspException, IOException {
		
		String uuid = UUID.randomUUID().toString();
		PageContext pc = (PageContext)getJspContext();
		pc.getSession().setAttribute("token", uuid);
		pc.getOut().write("<input name=\"token\" type=\"hidden\" value=\""+uuid+"\">");
	}
	
	
}

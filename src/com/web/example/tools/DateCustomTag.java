package com.web.example.tools;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class DateCustomTag extends TagSupport {

	private String pattern;
	
	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	@Override
	public int doStartTag() throws JspException {
		
		SimpleDateFormat sf = null;
		if (this.pattern == null || this.pattern.equals("")) {
			sf =  new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		}else {
			sf =  new SimpleDateFormat(this.pattern);
		}
		
		JspWriter out = this.pageContext.getOut();
		String result = sf.format(new Date());
		try {
			out.write(result);
			System.out.println("写出:"+result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return super.doStartTag();
	}

	
	
}

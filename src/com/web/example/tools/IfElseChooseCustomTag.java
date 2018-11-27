package com.web.example.tools;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

public class IfElseChooseCustomTag extends SimpleTagSupport{

	private boolean isTrue = true;
	
	public boolean isTrue() {
		return isTrue;
	}

	public void setTrue(boolean isTrue) {
		this.isTrue = isTrue;
	}

	@Override
	public void doTag() throws JspException, IOException {

		this.getJspBody().invoke(null);
		
		super.doTag();
	}
	
}

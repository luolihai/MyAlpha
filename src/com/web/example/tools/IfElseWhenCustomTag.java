package com.web.example.tools;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

public class IfElseWhenCustomTag extends SimpleTagSupport{

	private boolean test = true;

	public boolean isTest() {
		return test;
	}

	public void setTest(boolean test) {
		this.test = test;
	}

	@Override
	public void doTag() throws JspException, IOException {
		IfElseChooseCustomTag parent = (IfElseChooseCustomTag)this.getParent();
		if (parent.isTrue() && test) {
			this.getJspBody().invoke(null);
			parent.setTrue(false);
		}
		
		super.doTag();
	}
	
}

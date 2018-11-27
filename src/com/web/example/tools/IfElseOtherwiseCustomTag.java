package com.web.example.tools;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

public class IfElseOtherwiseCustomTag extends SimpleTagSupport{


	@Override
	public void doTag() throws JspException, IOException {
		IfElseChooseCustomTag parent = (IfElseChooseCustomTag)this.getParent();
		
		if (parent.isTrue() ) {
			this.getJspBody().invoke(null);
			parent.setTrue(true);
		}
		
		super.doTag();
	}
	
}

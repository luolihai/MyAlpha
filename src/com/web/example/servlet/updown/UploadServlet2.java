package com.web.example.servlet.updown;

import java.io.UnsupportedEncodingException;

import javax.servlet.annotation.WebServlet;

import org.apache.commons.fileupload.FileItem;

@WebServlet(urlPatterns={"/uploadServlet2"})
public class UploadServlet2 extends UploadServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void getRealFilePath() {
		String path = getServletContext().getInitParameter("filePath");
		if (path != null) {
			filePath = path;
		}
		String realPath = getServletContext().getRealPath("/");
		realFilePath = realPath+filePath;
	}

	@Override
	protected void fieldContextProcess(FileItem item) {
		String fieldName = item.getFieldName();;
		String value = null;
		try {
			value = item.getString("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(fieldName+":"+value);
	}
	
	

}

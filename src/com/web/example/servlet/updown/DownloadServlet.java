package com.web.example.servlet.updown;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	protected String filePath = "files";
	protected String realFilePath;
	@Override
	public void init() throws ServletException {
		getRealFilePath();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String filename = req.getParameter("filename");
		String fileurl = req.getParameter("fileurl");
		if (filename == null || filename.equals("") || fileurl == null || fileurl.equals("")) {
			resp.getWriter().write("<h1>参数错误呀</h1>");
			return ;
		}
		
		String filePath = realFilePath+File.separator+fileurl;
		FileInputStream in = new FileInputStream(new File(filePath));
		ServletOutputStream out = resp.getOutputStream();
		
		//头信息设置
		resp.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(filename,"utf-8"));
		resp.setHeader("ContentType", "application/octet-stream");//搜索tocmat中web.xml bin内容查找
		
		byte[] b = new byte[1024];
		int len = -1;
		while ((len = in.read(b)) != -1) {
			out.write(b, 0, len);
		}
		in.close();
		
		
		
	}
	

	//获取上传文件夹路径
	protected void getRealFilePath() {
		String path = getServletConfig().getInitParameter("filePath");
		if (path != null) {
			filePath = path;
		}
		String realPath = getServletContext().getRealPath("/");
		realFilePath = realPath+filePath;
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	
	
}

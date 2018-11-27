package com.web.example.servlet.updown;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/downloadIndex")
public class DownloadIndex extends HttpServlet{

	private static final long serialVersionUID = 1L;
	protected String filePath = "files";
	protected String realFilePath;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		getRealFilePath();
		
		File file = new File(realFilePath);
		if (file.exists()) {
			Map<String, String> map = new HashMap<String, String>();
			getFileMap(map,file,filePath);
			req.setAttribute("map", map);
			req.getRequestDispatcher("mult/exampleJsp/servlet/downloadIndex.jsp").forward(req, resp);;
		}
	}
	
	
	private Map<String, String> getFileMap(Map<String, String> map,File file,String basePackage) throws IOException {
		if (file.isFile()) {
			String path = file.getPath();
			String lastFilePath = path.substring(path.lastIndexOf(basePackage+File.separator)+(basePackage+File.separator).length());
			map.put(lastFilePath,lastFilePath.substring(lastFilePath.lastIndexOf("_")+1));
		}else{
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				getFileMap(map, files[i],basePackage);
			}
		}
		return map;
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

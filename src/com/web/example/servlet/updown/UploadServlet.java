package com.web.example.servlet.updown;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/**
 * 上传文件处理
 * servlet使用注解方式,并配置初始参数
 * 路径问题小心被暴力攻击
 * @author 26031
 *
 */
@WebServlet(urlPatterns={"/uploadServlet"},initParams = { @WebInitParam(name = "filePath", value = "uploadFiles") })
public class UploadServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected String filePath = "uploadFiles";
	protected String realFilePath;


	@Override
	public void init() throws ServletException {
		//最好在这里把File new出来
		getRealFilePath();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		if (ServletFileUpload.isMultipartContent(request)) {
			
			try {
				DiskFileItemFactory fileItem = new DiskFileItemFactory();
				fileItem.setRepository(new File("d:/"));//设置上传缓存文件位置
				
				ServletFileUpload servletFileUpload = new ServletFileUpload(fileItem);
//				servletFileUpload.setFileSizeMax(3*1000*1000);//单个文件大小 
//				servletFileUpload.setSizeMax(5*1000*1000);	//总文件大小
				
				final HttpSession session = request.getSession();
				servletFileUpload.setProgressListener(new ProgressListener() {
					
					@Override
					public void update(long pBytesRead, long pContentLength, int pItems) {
						double percent = (pBytesRead+0.0)/(pContentLength+0.0);
						System.out.println("上传百分比："+percent);
						NumberFormat numberFormat = NumberFormat.getInstance();
						numberFormat.format(percent);
						session.setAttribute("percent", percent);
					}
				});
				
				
				List list = servletFileUpload.parseRequest(request);
				Iterator iterator = list.iterator();
				while(iterator.hasNext()){
					FileItem item = (FileItem)iterator.next();
					if (item.isFormField()) {
						//普通处理
						fieldContextProcess(item);
					}else{
						//文件处理
						fileContextProcess(item);
					}
				}
				
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			response.getOutputStream().write("不是multipart输入类型".getBytes("utf-8"));
		}
		
	}

	protected void fileContextProcess(FileItem item) throws Exception {
		String fieldName = item.getFieldName();
		String filename = item.getName();
		/*
		InputStream in = item.getInputStream();
		OutputStream out = response.getOutputStream();
		byte[] b = new byte[1024];
		int len = -1;
		while((len = in.read(b)) > -1){
			out.write(b, 0, len);
		}
		in.close();*/
		
		
		File file = getFile(filename);
		item.write(file);
	}



	protected File getFile(String initfilename) {
		String filename = getFileName(initfilename);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String datePath = sdf.format(new Date());
		String isFilePath = realFilePath+"//"+datePath+"//";
		filename =isFilePath+UUID.randomUUID().toString()+"_"+filename;
		System.out.println("filename全路径："+filename);
		
		File file = new File(isFilePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		file = new File(filename);
		return file;
	}
	
	protected String getFileName(String filename) {
		if (filename.indexOf("//") > -1) {
			filename = filename.substring(filename.lastIndexOf("//")+1);
			System.out.println("截取filename:"+filename);
		}
		filename =UUID.randomUUID().toString()+"_"+filename;
		return filename;
	}

	protected void fieldContextProcess(FileItem item) {
		String fieldName = item.getFieldName();;
		String value = item.getString();
		System.out.println(fieldName+":"+value);
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}

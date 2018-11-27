package com.web.example.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.http.impl.io.HttpResponseWriter;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.message.LineFormatter;
import org.apache.http.params.HttpParams;

/**
 * 页面使用gzip技术，浏览器可读取gzip
 */
//@WebFilter("/*")
public class GzipResponseFilter implements Filter {

    /**
     * Default constructor. 
     */
    public GzipResponseFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		try {
			request = (HttpServletRequest)req;
			response = (HttpServletResponse)resp;
		} catch (Exception e) {
			throw new RuntimeException("not request and response");
		}
		
		//重写HttpServletResponse获取流数据源
		MyHttpServletResponse myResponse = new MyHttpServletResponse(response);
		chain.doFilter(request, myResponse);//把myResponse给servlet处理
		
		//获取重写后的数据源
		byte[] bytes = myResponse.getBytes();
		System.out.println("压缩前："+bytes.length);
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		//使用gzip压缩后输入HttpServletResponse
		GZIPOutputStream gzip = new GZIPOutputStream(byteOut);
		gzip.write(bytes);
		gzip.close();
		
		bytes = byteOut.toByteArray();
		System.out.println("压缩后："+bytes.length);
		
		response.setHeader("Content-Encoding", "gzip");
		response.getOutputStream().write(bytes);
}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}
	
	
	//定义包装类包装HttpServletResponse，替换writer流
	class MyHttpServletResponse extends HttpServletResponseWrapper{
		
		private ByteArrayOutputStream baos = new ByteArrayOutputStream();
		private PrintWriter pw;//全局流
		
		public MyHttpServletResponse(HttpServletResponse response) {
			super(response);
		}
		
		//重写servlet的写入流资源
		@Override
		public ServletOutputStream getOutputStream() throws IOException {
			return new MyServletOutputStream(baos);
		}
		//重写getWirter方法替换流
		@Override
		public PrintWriter getWriter() throws IOException {
			
			pw = new PrintWriter(new OutputStreamWriter(baos,super.getCharacterEncoding()));
			
			return pw;
		}
		

		public byte[] getBytes(){
			
			try {
				if(pw != null) pw.close();//先关闭流，这样数据全部写进baos
				baos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return baos.toByteArray();
		}
	}
	
	//主要是重写MyServletOutputStream中的write方法，写入源数据
	class MyServletOutputStream extends ServletOutputStream{
		private ByteArrayOutputStream sourceBaos;
		public MyServletOutputStream(ByteArrayOutputStream sourceBaos){
			this.sourceBaos = sourceBaos;
		}
		@Override
		public void write(int b) throws IOException {
			sourceBaos.write(b);
		}
	}
	
}

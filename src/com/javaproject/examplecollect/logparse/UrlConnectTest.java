package com.javaproject.examplecollect.logparse;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class UrlConnectTest {

	public static void main(String[] args) {
		String url = "http://www.baidu.com";
		UrlConnectTest.whileUrl(url);
	}
	
	public static void whileUrl(String url){
		while (true) {
			try {
				testUrl(url, 2000);
				System.out.println("success");
			} catch (RuntimeException e) {
				System.out.println("路径不通："+url);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			try {
				Thread.currentThread().sleep(3000);
			} catch (Exception e) {
			}
			
		}
	}
	
	public static void testUrl(String url,int time) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		HttpGet httpGet = new HttpGet(url);
		RequestConfig requestConfig = RequestConfig.custom()    
		        .setConnectTimeout(time).build();  
		httpGet.setConfig(requestConfig);
		CloseableHttpResponse response1 = httpclient.execute(httpGet);
		try {
		    System.out.println(response1.getStatusLine());
		    HttpEntity entity1 = response1.getEntity();
		    EntityUtils.consume(entity1);
		} finally {
		    response1.close();
		}
	}
}

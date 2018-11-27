package com.javaproject.utilcollect.httputils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClient {
	public static Log log = LogFactory.getLog(HttpClient.class);
	
	public static void main(String[] args) throws KeyManagementException,
			NoSuchAlgorithmException, ClientProtocolException, IOException {
		long first = System.currentTimeMillis();
/*
		String url = "https://api.weixin.qq.com/bizwifi/qrcode/get?access_token="
				+ "Jx01cRQbdcxGEmo_23hqbVbd_AJlpWKW0ti6sfDkJh5iCzZzYGmOyMkrUtaYKthUbceK_Wtfnr0frUwqjANVo1Nc9vrkGoJw05Ap7OQuy8LOtkZLHK44z65i1vN-0wQBKJPiCJAQPV";
		String parameters = "{\"shop_id\":6543746,\"ssid\":\"tcsoft_3\",\"img_id\":1}";
		httpsPostRequest(url,1, parameters);
		*/
		
		String url = "http://192.168.0.168:18080/interlib/loan/ClusterLoanClient?cmdACT=getReaderInfo_XML&isIDCard=2&rdid=w1";
		
		System.out.println(httpGetRequest(url));
		long last = System.currentTimeMillis();
		System.out.println(last-first+"秒");
		
	}
	public static void validate(Object requestData){
		if (requestData instanceof String) {
			System.out.println(requestData);
		}else if (requestData instanceof Map) {
			List <NameValuePair> nvps = new ArrayList <NameValuePair>();
			Map<String, String> map = (Map<String, String>)requestData;
			for (Entry<String, String> entry : map.entrySet()) {
				System.out.println(entry.getKey()+":"+entry.getValue());
			}
		}
	}
	
	/**
	 * https post请求，返回string内容
	 * 支持json与普通参数类型
	 * key value请需采用map类型传参
	 * @param url 路径
	 * @param requestDataType 类型，1为josn参数,0为普通参数
	 * @param requestData 请求参数，支持String与Map类型
	 * @return 内容
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String httpsPostRequest(String url,int requestDataType,Object requestData)
			throws NoSuchAlgorithmException, KeyManagementException,
			ClientProtocolException, IOException {
		
		String result = "";
		
		// First create a trust manager that won't care.
		X509TrustManager trustManager = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
				// Don't do anything.
			}
			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
				// Don't do anything.
			}
			public X509Certificate[] getAcceptedIssuers() {
				// Don't do anything.
				return null;
			}
		};

		// Now put the trust manager into an SSLContext.
		SSLContext sslcontext = SSLContext.getInstance("SSL");
		sslcontext.init(null, new TrustManager[] { trustManager }, null);
		// Use the above SSLContext to create your socket factory
		// (I found trying to extend the factory a bit difficult due to a
		// call to createSocket with no arguments, a method which doesn't
		// exist anywhere I can find, but hey-ho).
		SSLSocketFactory sf = new SSLSocketFactory(sslcontext);
		sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getConnectionManager().getSchemeRegistry()
				.register(new Scheme("https", sf, 443));

		HttpPost httpPost = new HttpPost(url);
		//设值
		setHttpPostParas(httpPost, requestData, requestDataType);
		CloseableHttpResponse response = httpclient.execute(httpPost);
		try {
			HttpEntity entity = response.getEntity();
			//获取响应内容
			result = EntityUtils.toString(entity);
			//销毁
			EntityUtils.consume(entity);
		} finally {
			response.close();
		}
		return result;
	}
	
	/**
	 * http post请求，返回string内容
	 * 支持json与普通参数类型
	 * key value请需采用map类型传参
	 * @param url 路径
	 * @param requestDataType 类型，1为josn参数,0为普通参数
	 * @param requestData 请求参数，支持String与Map类型
	 * @return 内容
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String httpPostRequest(String url,int requestDataType,Object requestData) 
			throws ClientProtocolException, IOException{
		String result = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		//set参数
		setHttpPostParas(httpPost, requestData, requestDataType);
		CloseableHttpResponse response = httpclient.execute(httpPost);
		try {
		    HttpEntity entity = response.getEntity();
		    result = EntityUtils.toString(entity);
		    EntityUtils.consume(entity);
		} finally {
		    response.close();
		}
		return result;
	}
	
	/**
	 * http get请求，返回string内容
	 * @param url 路径
	 * @return 内容
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String httpGetRequest(String url) 
			throws ClientProtocolException, IOException{
		String result = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = httpclient.execute(httpGet);
		try {
		    HttpEntity entity = response.getEntity();
		    result = EntityUtils.toString(entity);
		    EntityUtils.consume(entity);
		} finally {
		    response.close();
		}
		return result;
	}
	
	/**
	 * httpPost set值
	 * @param httpPost
	 * @param requestData 参数，String类型或Map类型
	 * @param requestDataType 参数是否是json格式
	 * @throws UnsupportedEncodingException
	 */
	public static void setHttpPostParas(HttpPost httpPost,Object requestData,int requestDataType) 
			throws UnsupportedEncodingException{
		//1表示参数是json类型，设置对应表头
		if (requestDataType == 1) {
			httpPost.addHeader("Content-type", "application/json; charset=utf-8");
			httpPost.setHeader("Accept", "application/json");
		}
		//设值
		if (requestData != null) {
			if (requestData instanceof String) {
				StringEntity myEntity = new StringEntity((String)requestData,
						Charset.forName("UTF-8"));
				httpPost.setEntity(myEntity);
			}else if (requestData instanceof Map) {
				List <NameValuePair> nvps = new ArrayList <NameValuePair>();
				Map<String, String> map = (Map<String, String>)requestData;
				for (Entry<String, String> entry : map.entrySet()) {
					nvps.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			}
		}
	}
	
	
}

package com.javaproject.examplecollect.base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import sun.misc.BASE64Encoder;

public class MD5Util {

	@Test
	public void test() throws NoSuchAlgorithmException{
		System.out.println(md5("福利"));
	}


	/**
	 * md5
	 * 返回BASE64Encoder字符
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String md5(String message) {
		if(message == null)return null;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("md5");
		byte[] digest = md.digest(message.getBytes());
		BASE64Encoder base = new BASE64Encoder();
		return base.encode(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException("md5加密异常");
		}
	}
	
	
	
	/** 
    * 将16位byte[] 转换为32位String 
    *  
    * @param buffer 
    * @return 
    */  
   private String toHex(byte buffer[]) {  
       StringBuffer sb = new StringBuffer(buffer.length * 2);  
       for (int i = 0; i < buffer.length; i++) {  
           sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));  
           sb.append(Character.forDigit(buffer[i] & 15, 16));  
       }  
       return sb.toString();  
   }  

}

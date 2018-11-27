package com.web.example.common;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
/**
 * AES对称加密，较高级的加密，加密后密文都较长
 * 加密后的值一般长度不一，根据加密字符长度决定
 * 密钥呢，就是那个对象 
 * @author 26031
 *
 */
public class AESEncryptionAndDecryption {
	//密码计算器
	private static Cipher cipher;
	//密钥
	private static SecretKey generateKey;
	
	public static String encode(String src){
		try
		{
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);//size
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] keyBytes = secretKey.getEncoded();
			//密钥
			generateKey = new SecretKeySpec(keyBytes, "AES");
			
			cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, generateKey);
			byte[] resultBytes = cipher.doFinal(src.getBytes());
			
			return Hex.encodeHexString(resultBytes);
		}
		catch (Exception e)
		{
		 e.printStackTrace();
		}
		return null;
	}
	
	public static String decode(String src){
		try
		{
			cipher.init(Cipher.DECRYPT_MODE, generateKey);
			byte[] result = Hex.decodeHex(src.toCharArray());
			return new String(cipher.doFinal(result));
		}
		catch(Exception e)
		{
		 e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		String result = encode("123123123123123123");
		System.out.println(result);
		System.out.println(result.length());
		String decResult = decode(result);
		System.out.println(decResult);
	}
}

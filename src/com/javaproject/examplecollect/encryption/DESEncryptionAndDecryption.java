package com.javaproject.examplecollect.encryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Hex;
/**
 * DES对称加密，安全性较低，不过运算较快
 * 加密后的值一般长度不一，根据加密字符长度决定
 * 密钥呢，一般是写成二进制文件吧
 * @author 26031
 *
 */
public class DESEncryptionAndDecryption {
	//密钥计算器
	private static Cipher cipher;
	//密钥
	private static SecretKey generateKey;
	
	public static String encode(String src)
	{
		try
		{
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
			keyGenerator.init(56);//size
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] keyBytes = secretKey.getEncoded();
			
			DESKeySpec desKeySpec = new DESKeySpec(keyBytes);
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
			//生成的密钥
			generateKey = secretKeyFactory.generateSecret(desKeySpec);
			
			cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
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
		System.out.println("加密："+result);
		System.out.println(result.length());
		String decResult = decode(result);
		System.out.println("解密："+decResult);
	}
	
}

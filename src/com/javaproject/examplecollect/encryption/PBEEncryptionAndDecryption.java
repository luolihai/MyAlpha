package com.javaproject.examplecollect.encryption;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Hex;
/**
 * 基于口令的加密，如果+盐再MD5加密，那就很难破解
 * @author 26031
 *
 */
public class PBEEncryptionAndDecryption {
	
	private static Cipher cipher;
	//根据口令生成的密钥
	private static SecretKey generateKey;
	private static PBEParameterSpec pbeParameterSpec;
	
	public static String encode(String src){
		try{
			//生成随机盐
			SecureRandom secureRandom = new SecureRandom();
			byte[] salt = secureRandom.generateSeed(8);
			
			//自己的口令密码
			String password = "amuro";
			PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");
			generateKey = secretKeyFactory.generateSecret(pbeKeySpec);
			
			//加盐
			pbeParameterSpec = new PBEParameterSpec(salt, 100);
			
			cipher = Cipher.getInstance("PBEWITHMD5andDES");
			cipher.init(Cipher.ENCRYPT_MODE, generateKey, pbeParameterSpec);
			byte[] resultBytes = cipher.doFinal(src.getBytes());
			return Hex.encodeHexString(resultBytes);
		}
		catch(Exception e)
		{
		 e.printStackTrace();
		}
		return null;
	}
	
	public static String decode(String src){
		try{
			cipher.init(Cipher.DECRYPT_MODE, generateKey, pbeParameterSpec);
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
		String result = encode("123123");
		System.out.println("加密："+result);
		System.out.println("长度："+result.length());
		String decResult = decode(result);
		System.out.println("解密："+decResult);
	}
}
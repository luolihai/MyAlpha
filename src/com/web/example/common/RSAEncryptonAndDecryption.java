package com.web.example.common;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Hex;

public class RSAEncryptonAndDecryption{
	/*
	公钥与私钥，给另一方的一般是把公钥写成对象流二进制文件啦，私钥一般就是已方用来生成密文给人家用的啦，比如生成授权字符文件，
	使用方用这个文件，给指定有公钥的程式解析，然后判断
	*/
	private static RSAPublicKey rsaPublicKey;
	private static RSAPrivateKey rsaPrivateKey;
	
	//加密
	public static String encode(String src){
		try
		{
			//初始化密钥
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(512);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			rsaPublicKey = (RSAPublicKey)keyPair.getPublic();
			rsaPrivateKey = (RSAPrivateKey)keyPair.getPrivate();
			
			//私钥加密 公钥解密
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec 
			= new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			byte[] resultBytes = cipher.doFinal(src.getBytes());
			
			//私钥解密 公钥加密
			//  X509EncodedKeySpec x509EncodedKeySpec =
			//   new X509EncodedKeySpec(rsaPublicKey.getEncoded());
			//  KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			//  PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			//  Cipher cipher = Cipher.getInstance("RSA");
			//  cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			//  byte[] resultBytes = cipher.doFinal(src.getBytes());
			
			return Hex.encodeHexString(resultBytes);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	//解密
	public static String decode(String src){
		try{
			//私钥加密 公钥解密
			X509EncodedKeySpec x509EncodedKeySpec =
			 new X509EncodedKeySpec(rsaPublicKey.getEncoded());
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			byte[] resultBytes = cipher.doFinal(Hex.decodeHex(src.toCharArray()));
			
			//私钥解密 公钥加密
			//  PKCS8EncodedKeySpec pkcs8EncodedKeySpec 
			//  = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
			//  KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			//  PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			//  Cipher cipher = Cipher.getInstance("RSA");
			//  cipher.init(Cipher.DECRYPT_MODE, privateKey);
			//  byte[] resultBytes = cipher.doFinal(Hex.decodeHex(src.toCharArray()));
			return new String(resultBytes);
		}
		catch(Exception e)
		{
		 e.printStackTrace();
		}
		return null;
	 }
	
	public static void main(String[] args) {
		String ss = "123123";
		String result = encode(ss);
		System.out.println("加密结果:"+result);
		String decResult = decode(result);
		System.out.println("解密结果:"+decResult);
	}
}

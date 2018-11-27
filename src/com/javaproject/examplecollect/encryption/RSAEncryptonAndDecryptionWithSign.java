package com.javaproject.examplecollect.encryption;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 数字签名的RSA加密
 * @author 26031
 *
 */
public class RSAEncryptonAndDecryptionWithSign{
	
	public static boolean verifySign(String src){
		try{
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(512);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			//公钥
			PublicKey rsaPublicKey = (RSAPublicKey)keyPair.getPublic();
			//密钥
			PrivateKey rsaPrivateKey = (RSAPrivateKey)keyPair.getPrivate();
			
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec 
			= new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			
			//利用密钥签名
			PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			Signature signature = Signature.getInstance("MD5withRSA");	//数字证书类
			signature.initSign(privateKey);
			signature.update(src.getBytes());
			//生成签名bytes，这东西可作密文发过去给用户使用啦
			byte[] signBytes = signature.sign();
			
			X509EncodedKeySpec x509EncodedKeySpec =
			 new X509EncodedKeySpec(rsaPublicKey.getEncoded());
			keyFactory = KeyFactory.getInstance("RSA");
			//利用公钥解密
			PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			signature = Signature.getInstance("MD5withRSA");
			signature.initVerify(publicKey);
			signature.update(src.getBytes());
			boolean isVerified = signature.verify(signBytes);
			
			return isVerified;
		}
		catch(Exception e)
		{
		 e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) {
		boolean result = verifySign("123123");
		System.out.println(result);
	}
}

package com.dayuan.utill;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AESUtils {
	
private static final String private_key = "1234567812345678";
	
	private static final String alg = "AES";
	
	public static byte[] encode(byte[] data) {
		try {
			SecretKey secretKey = new SecretKeySpec(private_key.getBytes(), alg);
			Cipher cipher = Cipher.getInstance(alg + "/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return cipher.doFinal(data);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String encodeMsg(byte[] data) {// 加密 转换为字节 字节转换为Base64 字符串
		try {
			SecretKey secretKey = new SecretKeySpec(private_key.getBytes(), alg);
			Cipher cipher = Cipher.getInstance(alg+ "/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] en = cipher.doFinal(data);
			return Base64.encodeBase64String(en);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static byte[] decode(byte[] data) throws Exception {
		
		SecretKey secretKey = new SecretKeySpec(private_key.getBytes(), alg);
		Cipher cipher = Cipher.getInstance(alg + "/ECB/PKCS5Padding");
		
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		
		byte[] dataBase = Base64.decodeBase64(data);
		
		return cipher.doFinal(dataBase);
	}
	
	public static boolean check(String msg, String sign) throws Exception {
		byte[] deMsg = decode(msg.getBytes());
		
		return sign.equals(new String(deMsg));
	}
	
	public static String decodeMsg(byte[] data) throws Exception {//解码
		
		SecretKey secretKey = new SecretKeySpec(private_key.getBytes(), alg);
		Cipher cipher = Cipher.getInstance(alg + "/ECB/PKCS5Padding");
		
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		
		byte[] dataBase = Base64.decodeBase64(data);
		
		return new String(cipher.doFinal(dataBase));
	}
	
	public static void main(String args[]) throws Exception {
		String msg = "hello world";
		
		byte[] encodeMsg = AESUtils.encode(msg.getBytes());
		String miwen = Base64.encodeBase64String(encodeMsg);
		String noBase64Miwen = new String(encodeMsg);
		
		System.out.println("加密后：" + miwen);
		System.out.println("加密后>>：" + noBase64Miwen);
		
		String decodeMsg = AESUtils.decodeMsg(miwen.getBytes());
		System.out.println("解密后：" + decodeMsg);
		
	}

}

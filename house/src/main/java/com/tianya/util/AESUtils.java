package com.tianya.util;

import cn.hutool.core.util.RandomUtil;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

/**
 * @author changwenbo
 * @date 2023/6/25 17:15
 */
public class AESUtils {
	public static void main(String[] args) throws Exception {
		String content  = "Hello World";
		String key = generateAESKey();

		String encrypt = encrypt(content, key);
		System.out.println(encrypt);
		System.out.println(decrypt(encrypt, key));
	}

	private static final String KEY_ALGORITHM = "AES";
	private static final String ENCODING = StandardCharsets.UTF_8.name();
	private static final String AES_ECB_MODE = "AES/ECB/PKCS5Padding";
	private static final String AES_CBC_MODE = "AES/CBC/PKCS5Padding";

	// 因为AES块 为16字节，所以IV也为16字节
	private static final String IV = RandomUtil.randomString(16);
	private static final IvParameterSpec ips = new IvParameterSpec(IV.getBytes());

	public static String generateAESKey() {
		KeyGenerator keyGenerator = null;
		try {
			keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		keyGenerator.init(128);
		SecretKey key = keyGenerator.generateKey();
		byte[] keyExternal = key.getEncoded();
		return Base64.encodeBase64String(keyExternal);
	}

	public static String encrypt(String content, String key) {
		try {
			byte[] bytesKey = Base64.decodeBase64(key);
			SecretKeySpec secretKey = new SecretKeySpec(bytesKey, KEY_ALGORITHM);
			Cipher cipher = Cipher.getInstance(AES_ECB_MODE);// 创建密码器
			byte[] byteContent = content.getBytes(ENCODING);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);// 初始化
			byte[] result = cipher.doFinal(byteContent);// 加密
			return Base64.encodeBase64String(result);
		} catch (Exception e) {
		}
		return null;
	}

	public static String decrypt(String content, String key) {
		try {
			byte[] bytesKey = Base64.decodeBase64(key);
			SecretKeySpec secretKey = new SecretKeySpec(bytesKey, KEY_ALGORITHM);
			Cipher cipher = Cipher.getInstance(AES_ECB_MODE);// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, secretKey);// 初始化
			byte[] result = cipher.doFinal(Base64.decodeBase64(content));// 解密
			return new String(result);
		} catch (Exception e) {
		}
		return null;
	}
}

package com.tianya.util;

import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author changwenbo
 * @date 2023/6/25 17:14
 */
@Slf4j
public class RSAUtils {

	public static final String KEY_ALGORITHM = "RSA";
	public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";

	// 公钥加密
	public static String encryptByPublicKey(String plainText, PublicKey publicKey) {
		try {
			Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] enBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
			return Base64.encodeBase64String(enBytes);
		} catch (Exception e) {
			// error
		}
		return null;
	}

	// 私钥解密
	public static String decryptByPrivateKey(String enStr, PrivateKey privateKey) {
		try {
			Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] deBytes = cipher.doFinal(Base64.decodeBase64(enStr));
			return new String(deBytes);
		} catch (Exception e) {
			// error
		}
		return null;
	}

	public static Pair<PublicKey, PrivateKey> genKeyPair() throws Exception {
		// KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");

		// 初始化密钥对生成器，密钥大小为
		keyPairGen.initialize(2048, new SecureRandom());

		// 生成一个密钥对，保存在keyPair中
		KeyPair keyPair = keyPairGen.generateKeyPair();

		// 得到私钥、公钥
		PrivateKey privateKey = keyPair.getPrivate();
		PublicKey publicKey = keyPair.getPublic();
		return new Pair<>(publicKey, privateKey);
	}

	public static Pair<String, String> genKeyPairString() throws Exception {
		Pair<PublicKey, PrivateKey> pair = genKeyPair();

		// 得到私钥、公钥
		PublicKey publicKey = pair.getKey();
		PrivateKey privateKey = pair.getValue();

		// 得到私钥字符串
		String publicKeyString = Base64.encodeBase64String(publicKey.getEncoded());
		String privateKeyString = Base64.encodeBase64String((privateKey.getEncoded()));
		return new Pair<>(publicKeyString, privateKeyString);
	}


	public static String signByPrivateKey(String content, PrivateKey privateKey) {
		try {
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initSign(privateKey);
			signature.update(content.getBytes(StandardCharsets.UTF_8));
			byte[] signed = signature.sign();
			return Base64.encodeBase64String(signed);
		} catch (Exception e) {
			log.warn("sign error, content: {}, priKey: {}", content, privateKey);
			log.error("sign error, message is {}", e.getMessage());
		}
		return null;
	}


	/**
	 * 通过公钥验签
	 * @param content 验签内容
	 * @param sign  签名
	 * @param publicKey 公钥
	 * @return 验签结果
	 */
	public static boolean verifySignByPublicKey(String content, String sign, PublicKey publicKey) {
		try {
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initVerify(publicKey);
			signature.update(content.getBytes(StandardCharsets.UTF_8));
			return signature.verify(Base64.decodeBase64(sign.getBytes(StandardCharsets.UTF_8)));
		} catch (Exception e) {
			log.warn("sign error, content: {}, sign: {}, pubKey: {}", content, sign, publicKey);
			log.error("sign error", e);
		}
		return false;
	}

	public static PublicKey getPublicKey(String key) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(key);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		return keyFactory.generatePublic(keySpec);
	}

	/**
	 * 从私钥字符串中获取私钥
	 * @param key Base64的私钥字符串
	 * @return 私钥
	 * @throws Exception 异常
	 */
	public static PrivateKey getPrivateKey(String key) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(key.getBytes(StandardCharsets.UTF_8));
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		return keyFactory.generatePrivate(keySpec);
	}
}

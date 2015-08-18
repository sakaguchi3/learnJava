package com.github.sakaguchi3.jbatch002.api;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CipherY {

	private static final Logger LOG = LogManager.getLogger();
	private static final SecureRandom RAND = new SecureRandom();
	/** initial vector size[bit] */
	private static final int IV_SIZE = 16;

	private final Cipher encrypter;
	private final Cipher decrypter;

	// ------------------------------------------------------
	// constructor
	// ------------------------------------------------------

	public CipherY(byte[] key, byte[] iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
		var ivps = new IvParameterSpec(iv);

		var ALGORITHM_TRANSFORMATION = "AES/CBC/PKCS5Padding";
		var ALGORITHM_SECRETKEY = "AES";
		var skey = new SecretKeySpec(key, ALGORITHM_SECRETKEY);

		encrypter = Cipher.getInstance(ALGORITHM_TRANSFORMATION);
		encrypter.init(Cipher.ENCRYPT_MODE, skey, ivps);

		decrypter = Cipher.getInstance(ALGORITHM_TRANSFORMATION);
		decrypter.init(Cipher.DECRYPT_MODE, skey, ivps);
	}

	// ------------------------------------------------------
	// public
	// ------------------------------------------------------

	public static byte[] generateIV() {
		// 16以外だとエラー
		byte[] iv = new byte[IV_SIZE];
		RAND.nextBytes(iv);
		return iv;
	}

	public static String generateIVStr() {
		return RandomStringUtils.randomAlphanumeric(IV_SIZE);
	}

	public static byte[] generateKey(KeyLen keyLen) {
		// max32. 16, 24, 32それ以外だとエラー
		byte[] key = new byte[keyLen.len()];
		RAND.nextBytes(key);
		return key;
	}

	public static String generateKeyStr(KeyLen keyLen) {
		// max32. 16, 24, 32それ以外だとエラー
		return RandomStringUtils.randomAlphanumeric(keyLen.len());
	}

	public Optional<byte[]> encrypto(byte[] plain) {
		try {
			var encBytes = encrypter.doFinal(plain);
			return Optional.ofNullable(encBytes);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return Optional.empty();
		}
	}

	public Optional<byte[]> decrypto(byte[] encryptedData) {
		try {
			var plainBytes = decrypter.doFinal(encryptedData);
			return Optional.ofNullable(plainBytes);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return Optional.empty();
		}
	}

	// ------------------------------------------------------
	// protected
	// ------------------------------------------------------

	// ------------------------------------------------------
	// class
	// ------------------------------------------------------

	public static enum KeyLen {
		// keylength[bit]は8で割った数[byte]になる
		Len128(16), Len192(24), Len256(32),;

		private final int keyLength;

		KeyLen(int keyLen) {
			this.keyLength = keyLen;
		}

		public int len() {
			return keyLength;
		}
	}

}

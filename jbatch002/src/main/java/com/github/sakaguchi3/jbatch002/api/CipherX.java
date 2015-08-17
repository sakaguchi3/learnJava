package com.github.sakaguchi3.jbatch002.api;

import java.security.SecureRandom;
import java.security.Security;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CipherX {

	private static final Logger LOG = LogManager.getLogger();

	final SecureRandom RAND = new SecureRandom();
	final String ALGORITHM = "AES/CBC/PKCS5Padding";

	public Optional<SecretKey> generateKey() {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
//			keyGen.init(128);
//			keyGen.init(192);
			keyGen.init(256);

			return Optional.ofNullable(keyGen.generateKey());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return Optional.empty();
		}
	}

	public IvParameterSpec generateIV() {

		byte[] iv = new byte[16];
		RAND.nextBytes(iv);

		return new IvParameterSpec(iv);
	}

	public Optional<byte[]> encrypto(String plainText, SecretKey key, IvParameterSpec iv) {
		try {
			Security.getProviders();
			Cipher encrypter = Cipher.getInstance(ALGORITHM);
			encrypter.init(Cipher.ENCRYPT_MODE, key, iv);
			var bytes1 = plainText.getBytes();
			var bytes2 = encrypter.doFinal(bytes1);

			return Optional.ofNullable(bytes2); 
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return Optional.empty();
		}
	}

	public Optional<String> decrypto(byte[] cryptoText, SecretKey key, IvParameterSpec iv) {
		try {
			Cipher decrypter = Cipher.getInstance(ALGORITHM);
			decrypter.init(Cipher.DECRYPT_MODE, key, iv);
//			decrypter.init(Cipher.DECRYPT_MODE, key); throw exception
			var bytes = decrypter.doFinal(cryptoText);
			var strDec = new String(bytes);

			return Optional.ofNullable(strDec);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return Optional.empty();
		}
	}

	void debug() {
	}
}

package com.github.sakaguchi3.jbatch002.javaapi;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.Base64;

import javax.crypto.Cipher;

import org.junit.jupiter.api.Test;

import com.github.sakaguchi3.jbatch002.api.CipherY;
import com.github.sakaguchi3.jbatch002.api.CipherY.KeyLen;

public class CipherYTest {

	@Test
	void maxKeyLengthTest() {
		try {
			var names = Security.getAlgorithms("Cipher");
			for (String name : names) {
				var len = Cipher.getMaxAllowedKeyLength(name);
				var s = String.format("%d, %s", len, name);
				System.out.println(s);
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void webSafeBase64Test() {
		try {
			var keyStr = CipherY.generateKeyStr(KeyLen.Len256);
			var ivStr = CipherY.generateIVStr();

			var iv = ivStr.getBytes();
			var key = keyStr.getBytes();
			var cipher = new CipherY(key, iv);

			var plainStr = "平文データ";

			// 送信側
			byte[] encDtBytes = cipher.encrypto(plainStr.getBytes(StandardCharsets.UTF_8)).get();
			String encDtWebSaveBase64Str = org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(encDtBytes);

			// 受信側
			byte[] encDt2Bytes = org.apache.commons.codec.binary.Base64.decodeBase64(encDtWebSaveBase64Str);

			var decDtBytes = cipher.decrypto(encDt2Bytes).get();
			var decPlainStr = new String(decDtBytes);

			// varify

			assertArrayEquals(encDtBytes, encDt2Bytes);
			assertEquals(decPlainStr, plainStr);
			debug();

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	void base64Test() {
		try {
			var keyStr = CipherY.generateKeyStr(KeyLen.Len256);
			var ivStr = CipherY.generateIVStr();

			var iv = ivStr.getBytes();
			var key = keyStr.getBytes();
			var cipher = new CipherY(key, iv);

			var plainStr = "平文データ";

			// 送信側
			byte[] encDtBytes = cipher.encrypto(plainStr.getBytes(StandardCharsets.UTF_8)).get();
			byte[] encDtBase64Bytes = Base64.getEncoder().encode(encDtBytes);
			String encDtBase64Str = new String(encDtBase64Bytes, StandardCharsets.UTF_8);

			// 受信側
			byte[] encDt2Base64Bytes = encDtBase64Str.getBytes(StandardCharsets.UTF_8);
			byte[] encDt2Bytes = Base64.getDecoder().decode(encDt2Base64Bytes);

			var decDtBytes = cipher.decrypto(encDt2Bytes).get();
			var decPlainStr = new String(decDtBytes);

			// varify

			assertArrayEquals(encDtBase64Bytes, encDt2Base64Bytes);
			assertArrayEquals(encDtBytes, encDt2Bytes);
			assertEquals(decPlainStr, plainStr);
			debug();

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	void strKeyTest() {
		try {
			var keyStr = CipherY.generateKeyStr(KeyLen.Len256);
			var ivStr = CipherY.generateIVStr();

			var iv = ivStr.getBytes();
			var key = keyStr.getBytes();
			var cipher = new CipherY(key, iv);

			var plainStr = "ccbbaa2";

			var encDtBytes = cipher.encrypto(plainStr.getBytes(StandardCharsets.UTF_8)).get();
			var decDtBytes = cipher.decrypto(encDtBytes).get();
			var decDtStr = new String(decDtBytes);

			assertEquals(plainStr, decDtStr);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	void bytesKeyTest() {
		try {
			byte[] iv = CipherY.generateIV();
			byte[] key = CipherY.generateKey(KeyLen.Len256);
//			var keyStr = new String(key); //文字化け
			var cipher = new CipherY(key, iv);

			var plainStr = "ccbbaa2";

			var encDtBytes = cipher.encrypto(plainStr.getBytes(StandardCharsets.UTF_8)).get();
			var decDtBytes = cipher.decrypto(encDtBytes).get();
			var decDtStr = new String(decDtBytes);

			assertEquals(plainStr, decDtStr);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	void stringKeyTest() {
		try {
			// これ以上長いとエラー
			String keyStr = "55555555" + "11111111" + "22222222" + "11111111";

			var iv = CipherY.generateIV();
			var key = keyStr.getBytes();
			var cipher = new CipherY(key, iv);

			var plainStr = "ccbbaa2";

			var encDtBytes = cipher.encrypto(plainStr.getBytes(StandardCharsets.UTF_8)).get();
			var decDtBytes = cipher.decrypto(encDtBytes).get();
			var decDtStr = new String(decDtBytes);

			assertEquals(plainStr, decDtStr);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	void debug() {
	}

}

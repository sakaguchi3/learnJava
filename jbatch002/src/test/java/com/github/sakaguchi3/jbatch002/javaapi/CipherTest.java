package com.github.sakaguchi3.jbatch002.javaapi;

import static io.vavr.API.For;
import static io.vavr.control.Option.ofOptional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.github.sakaguchi3.jbatch002.api.CipherX;

import io.vavr.control.Option;

public class CipherTest {
	final CipherX cipher = new CipherX();

	@Test
	void enc2Test() {
		var iv = cipher.generateIV();
		var secretkeyOp = cipher.generateKey();
		var plainStr = "ccbbaa2";

		var cipheredBytesOp = secretkeyOp.flatMap(v -> cipher.encrypto(plainStr, v, iv));

		var sutOp = For(ofOptional(secretkeyOp), ofOptional(cipheredBytesOp)) //
				.yield((key, ciphered) -> cipher.decrypto(ciphered, key, iv)) //
				.flatMap(Option::ofOptional);
		
		assertTrue(sutOp.isDefined());
		assertEquals(plainStr, sutOp.get());
	}

	@Test
	void encTest() {
		var iv = cipher.generateIV();
		var secretkey = cipher.generateKey().get();
		var plainStr = "ccbbaa";

		var cipheredBytes = cipher.encrypto(plainStr, secretkey, iv).get();
		var decStr = cipher.decrypto(cipheredBytes, secretkey, iv).get();

		assertEquals("ccbbaa", decStr);
	}

	void debug() {
	}

}

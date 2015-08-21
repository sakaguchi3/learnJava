package com.github.sakaguchi3.jbatch002.s;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.github.sakaguchi3.util.UtilRandom;

import io.vavr.collection.CharSeq;

public class UtilRandTest {

	@Test
	public void alphabetTest() {
		var randAlhabet = UtilRandom.randomAlphaNumeric(100);
		assertEquals(100, randAlhabet.length());

		final var REGEX = "[a-zA-Z0-9]";
		var replace1 = CharSeq.of(randAlhabet).replaceAll(REGEX, "");
		assertTrue(replace1.isEmpty());

		var replace2 = randAlhabet.replaceAll(REGEX + "+", "");
		var replace3 = randAlhabet.replaceAll(REGEX, "");

		assertTrue(replace2.isEmpty());
		assertTrue(replace3.isEmpty());

		debug();
	}

	@Test
	void charTest() {

		// 48
		int i0 = '0';
		// 57
		int i9 = '9';

		// 65
		int iA = 'A';
		// 90
		int iZ = 'Z';

		// 97
		int ia = 'a';
		// 122
		int iz = 'z';

		// [, int:91
		char ccc1 = (char) ('Z' + 1);
		// `, int:96
		char ccc2 = (char) ('a' - 1);
	}

	void debug() {
	}

}

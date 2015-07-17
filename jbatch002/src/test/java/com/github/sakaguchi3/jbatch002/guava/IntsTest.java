package com.github.sakaguchi3.jbatch002.guava;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.google.common.primitives.Ints;

public class IntsTest {

	@Test
	public void aTest() {
		debug();
	}

	@Test
	public void bTest() {
		Integer num33 = Ints.tryParse("33");
		Integer numNull = Ints.tryParse("notNumber");
		Integer numMix1 = Ints.tryParse("2notNumber1");
		Integer numMix2 = Ints.tryParse("2notNumber");

		// answer
		assertEquals(33, num33);
		assertNull(numNull);
		assertNull(numMix1);
		assertNull(numMix2);
	}

	void debug() {
	}

}

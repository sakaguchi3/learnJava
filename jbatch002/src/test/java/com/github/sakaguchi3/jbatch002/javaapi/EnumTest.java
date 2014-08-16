package com.github.sakaguchi3.jbatch002.javaapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class EnumTest {

	@Test
	void valueOfTest() {
		var r1 = x.valueOf("j3");
		assertEquals(x.j3, r1);
	}

	@Test
	void valueOfNullTest() {

		assertThrows(IllegalArgumentException.class, () -> {
			var r2 = x.valueOf("xxx");
		});
		d();
	}

	private static enum x {
		j1, j2, j3, j4,;
	}

	private void d() {
	}

}

package com.github.sakaguchi3.jbatch002.javaapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.EnumMap;

import org.junit.jupiter.api.Test;

import lombok.ToString;

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

	@Test
	void enumMapTest() {
		var sut = new EnumSample();

		assertFalse(sut.containsKey(x.j1));
		assertTrue(sut.containsKey(x.j2));
		assertEquals("d", sut.get(x.j4));
		assertNull(sut.get(x.j1));
	}

	@ToString
	private static class EnumSample extends EnumMap<x, String> {
		public EnumSample() {
			super(x.class);
			put(x.j2, "b");
			put(x.j3, "c");
			put(x.j4, "d");
		}

		private static final long serialVersionUID = 39192194996601779L;
	}

	private static enum x {
		j1, j2, j3, j4,;
	}

	private void d() {
	}

}

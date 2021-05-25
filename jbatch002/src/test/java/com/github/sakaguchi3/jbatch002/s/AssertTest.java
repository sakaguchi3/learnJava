package com.github.sakaguchi3.jbatch002.s;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

public class AssertTest {

	@Test
	public void nullEqualTest() {
		assertTrue(Objects.equals(null, null));
	}

//	@Test
	public void assertEqualsTest() {
		assertSame(127, 127);
		assertSame(127, Integer.valueOf(127));

		assertNotSame(128, 128);
		assertNotSame(128, Integer.valueOf(128));

		d();
	}

//	@Test
	public void eqListTest() {
		assertEquals(List.of(3), Arrays.asList(3));
		d();
	}

//	@Test
	public void sameListTest() {
		var d = List.of(3);
		assertEquals(d, d);
		d();
	}

	private void d() {
	}

}

package com.github.sakaguchi3.jbatch002.s;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class Generics02Test {

	// ---------------------------------------------------------
	// public method
	// ---------------------------------------------------------

	@Test
	public void test01Test() {
		TestClass<String> sut = new TestClass();

		String okStr = "ok string";
		String ngStr = "ng string";
		List<String> str01List = Arrays.asList("a01", "a02", "a09", "ok string", "b01");

		assertEquals(sut.contains1(str01List, okStr), (true));
		assertEquals(sut.contains2(str01List, okStr), (true));
		assertEquals(sut.contains3(str01List, okStr), (true));

	}

	private class TestClass<T> {

		public boolean contains3(List<T> list, Object o) {
			return list.contains(o);
		}

		public boolean contains2(List list, Object o) {
			return list.contains(o);
		}

		public boolean contains1(List<?> list, Object o) {
			return list.contains(o);
		}
	}
	// ---------------------------------------------------------
	// private method
	// ---------------------------------------------------------

	/**
	 * 
	 */
	@Test
	public void importPackage() {
		if (true) {
			assertEquals(true, (true));
		} else {
			fail("empty");
		}

	}

	void debug() {

	}

}
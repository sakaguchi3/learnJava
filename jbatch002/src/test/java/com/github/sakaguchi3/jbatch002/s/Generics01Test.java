package com.github.sakaguchi3.jbatch002.s;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class Generics01Test {

	@Test
	public void j03Test() {
		Object[] oArr = new Integer[] { 99, 88, 44 };
		Integer[] iArr = new Integer[] { 1, 2, 3 };

		// これはエラー
		// iArr = oArr;
	}

	@Test
	public void j02Test() {
		Object[] oArr;
		Integer[] iArr = new Integer[] { 1, 2, 3 };
		oArr = iArr;
		// ok
		oArr[1] = 59;

		assertThrows(ArrayStoreException.class, () -> {
			// Javaのarrayは共変なのでこれができてしまう
			oArr[0] = "err";
		});
	}

	@Test
	public void j01Test() {
		Object[] oArr;
		Integer[] iArr = new Integer[] { 1, 2, 3 };
		oArr = iArr;

		// ok
		oArr[1] = 59;

		assertEquals(oArr[1], (59));
		assertEquals(oArr[0], (1));

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
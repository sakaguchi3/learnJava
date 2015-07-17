package com.github.sakaguchi3.jbatch002.javaapi;



 import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class BigDecimalTest {

	/**
	 * https://qiita.com/ota-meshi/items/967304d406d668febe1d
	 */
	@Test
	public void calcTest() {

		// immutable
		final BigDecimal ten = BigDecimal.valueOf(10);
		final BigDecimal two = BigDecimal.valueOf(2);

		// 10+2 = 12
		final BigDecimal num12 = ten.add(two);
		// 10-2 = 8
		final BigDecimal num08 = ten.subtract(two);
		// 10-8-8 = 6
		final BigDecimal num06 = ten.subtract(two).subtract(two);

		// 2 * 10^2 = 200
		final BigDecimal num200 = two.scaleByPowerOfTen(2);

		assertEquals(ten.intValue(), (10));
		// c1 + c2
		assertEquals(num12.intValue(), (12));
		// c1 - c2
		assertEquals(num08.intValue(), (8));
		assertEquals(num06.intValue(), (6));
		// c * 10^n
		assertEquals(num200.intValue(), (200));
		// c * (-1)
		assertEquals(two.negate().intValue(), (-2));

		debug();
	}

	private void debug() {

	}

}

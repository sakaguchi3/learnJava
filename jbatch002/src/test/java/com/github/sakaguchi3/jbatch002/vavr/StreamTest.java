package com.github.sakaguchi3.jbatch002.vavr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

public class StreamTest {

	@Test
	public void aTest() {

		var sut10 = IntStream.range(1, 4) // [1,2,3]
				.reduce(1, (x, y) -> x + y);
		assertEquals(7, sut10);

		// parallelにするとstreamが分割される。identityがゼロ元じゃないと計算結果が正しくない
		var sut11 = IntStream.range(1, 4).parallel() //
				.reduce(1, (x, y) -> x + y);
		assertNotEquals(7, sut11);
	}

	@Test

	void debug() {
	}

}

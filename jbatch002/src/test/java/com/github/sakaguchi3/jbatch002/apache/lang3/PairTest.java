package com.github.sakaguchi3.jbatch002.apache.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.Test;

public class PairTest {

	@Test
	public void tupleTest() {

		Pair<Integer, String> pair = Pair.of(3, "three");
		Triple<Integer, Double, String> triple = Triple.of(9, 9.999d, "九");

		assertEquals(("three"), pair.getRight());
		assertEquals((9.999), triple.getMiddle());

	}

	private void debug() {

	}

}

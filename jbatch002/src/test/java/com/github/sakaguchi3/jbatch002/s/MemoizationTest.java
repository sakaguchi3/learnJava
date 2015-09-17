package com.github.sakaguchi3.jbatch002.s;

import org.junit.jupiter.api.Test;

import io.vavr.Function0;

public class MemoizationTest {

	@Test
	public void bTest() {
		Function0<Integer> f = Function0.of(() -> 1).memoized();
		debug();
	}

	static void debug() {
	}

}

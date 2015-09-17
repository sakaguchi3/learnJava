package com.github.sakaguchi3.jbatch002.guava;

import org.junit.jupiter.api.Test;

import com.google.common.base.Suppliers;

public class MemorizerTest {
	@Test
	public void bTest() {
		var memoizedSupplier = Suppliers.memoize(() -> 3);

		debug();
	}

	void debug() {
	}

}

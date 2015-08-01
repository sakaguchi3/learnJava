package com.github.sakaguchi3.jbatch002.ec;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.List;

import org.eclipse.collections.impl.factory.Lists;
import org.junit.jupiter.api.Test;

public class ImmutableLstTest {

	@Test
	void immutableLst2Test() {

		debug();
	}


	@Test
	void immutableLstTest() {
		var baseLst = Lists.immutable.of(1, 2, 3);
		var sut1 = baseLst.select(v -> v % 2 == 0);
		var sut2 = sut1.collect(s -> s + 9);

		assertIterableEquals(List.of(1, 2, 3), baseLst);
		assertIterableEquals(List.of(2), sut1);
		assertIterableEquals(List.of(11), sut2);

		debug();
	}

	void debug() {
	}

}

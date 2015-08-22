package com.github.sakaguchi3.jbatch002.ec;

import org.eclipse.collections.impl.factory.Maps;
import org.junit.jupiter.api.Test;

public class ImmutableMapTest {

	@Test
	void immutableLst2Test() {
		// あ
		debug();
	}

	@Test
	void immutableLstTest() {
		var m = Maps.immutable.of(1, "a", 2, "b");
	}

	void debug() {
	}

}

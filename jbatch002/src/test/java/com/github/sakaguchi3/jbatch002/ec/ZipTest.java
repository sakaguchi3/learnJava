package com.github.sakaguchi3.jbatch002.ec;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.jupiter.api.Test;

public class ZipTest {

	@Test
	public void zipWithIndexTest() {
		var lst1 = Lists.immutable.of("a", "b", "c");
		var sut = lst1.zipWithIndex();

		var ans = Lists.immutable.of( //
				Tuples.pair("a", 0), //
				Tuples.pair("b", 1), //
				Tuples.pair("c", 2));

		assertIterableEquals(ans, sut);
		debug();
	}

	@Test
	void zipTest() {
		var lst1 = Lists.immutable.of(1, 2, 3);
		var lst2 = Lists.immutable.of(100, 200, 300, 400);

		ImmutableList<Pair<Integer, Integer>> lstzp = lst1.zip(lst2);

		assertEquals(3, lstzp.size());
		assertEquals(Tuples.pair(2, 200), lstzp.get(1));

		debug();
	}

	void debug() {
	}

}

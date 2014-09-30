package com.github.sakaguchi3.jbatch002.eclipsecollection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.junit.jupiter.api.Test;

public class CollectionTest {

	@Test
	public void u02Test() {
		ImmutableList<Integer> l = Lists.immutable.of(1, 2, 3, 4, 5);

		assertTrue(l.contains(1));
		assertFalse(l.contains(99));

		d();
	}

	private void d() {
	}

}

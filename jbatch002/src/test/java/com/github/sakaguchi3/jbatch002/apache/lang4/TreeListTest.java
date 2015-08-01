package com.github.sakaguchi3.jbatch002.apache.lang4;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.list.TreeList;
import org.junit.jupiter.api.Test;

public class TreeListTest {
	@Test
	public void bTest() {
		debug();
	}

	@Test
	public void unmodifiableTest() {
		TreeList<Integer> t = new TreeList<>(List.of(8, 12, 20));
		var ut = Collections.unmodifiableCollection(t);
		assertThrows(UnsupportedOperationException.class, () -> {
			ut.add(3);
		});

		debug();
	}

	@Test
	public void treeTest() {
		TreeList<Integer> t = new TreeList<>(List.of(8, 12, 20));

		var ut = Collections.unmodifiableCollection(t);

		// ut is changed
		t.add(3, 11);

		assertIterableEquals(List.of(8, 12, 20, 11), ut);
		debug();
	}

	void debug() {
	}

}

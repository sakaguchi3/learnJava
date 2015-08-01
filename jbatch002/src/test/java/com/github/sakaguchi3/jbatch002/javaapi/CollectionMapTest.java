package com.github.sakaguchi3.jbatch002.javaapi;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CollectionMapTest {

	@Test
	public void map1Test() {
		var map = new HashMap<String, List<Number>>();

		var key = "a";

		List<Number> lst = map.computeIfAbsent(key, __ -> new LinkedList<>());
		boolean b = map.computeIfAbsent(key, __ -> new ArrayList<>()).add(3);

		assertTrue((map.get(key) instanceof LinkedList));
		assertFalse((map.get(key) instanceof ArrayList));

		debug();
	}

	@Test
	public void bTest() {
		debug();
	}

	void debug() {
	}

}

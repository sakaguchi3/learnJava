package com.github.sakaguchi3.jbatch002.javaapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CollectionMapTest {

//	@Test
	public void map1Test() {
		var map = new HashMap<String, List<Number>>();

		var key = "a";

		List<Number> lst = map.computeIfAbsent(key, __ -> new LinkedList<>());
		boolean b = map.computeIfAbsent(key, __ -> new ArrayList<>()).add(3);

		assertTrue((map.get(key) instanceof LinkedList));
		assertFalse((map.get(key) instanceof ArrayList));

		d();
	}

	@Test
	public void computeIfAbsentLazyCheckTest() {

		var map = new HashMap<String, Integer>();
		var key = "a";
		map.put(key, 30);

		var val = map.computeIfAbsent(key, __ -> {
			fail();
			return 10;
		});

		assertEquals(30, val);
		assertTrue(map.containsKey(key));

		d();

	}

//	@Test
	public void computeIfAbsentTest() {
		var map = new HashMap<String, Integer>();
		var key = "a";

		var val = map.computeIfAbsent(key, __ -> 10);

		assertEquals(10, val); 
		assertTrue(map.containsKey(key));
		assertEquals(10, map.get(key));

		d();
	}

	private void d() {
	}

}

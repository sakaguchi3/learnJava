package com.github.sakaguchi3.jbatch002.guava;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.MutableClassToInstanceMap;

public class CollectionTest {

	@Test
	public void Test() {
		debug();
	}

	@Test
	public void classToInstanceTest() {

		// Flyweight pattern みたいなことができる
		ClassToInstanceMap<Number> numberDefaults = MutableClassToInstanceMap.create();

		// null
		var intNull = numberDefaults.putInstance(Integer.class, Integer.valueOf(0));
		// Intager(0)
		var intNoNull = numberDefaults.putInstance(Integer.class, Integer.valueOf(0));
		numberDefaults.put(Double.class, 3.0d);

		var sutInt = numberDefaults.getInstance(Integer.class);
		var sutDob = numberDefaults.getInstance(Double.class);
		var sutFlo = numberDefaults.getInstance(Float.class);

		assertNull(intNull);
		assertNotNull(intNoNull);
		assertEquals(intNoNull, (0));

		assertEquals(sutInt, (0));
		assertEquals(sutDob, (3.0d));
		assertNull(sutFlo);

		assertEquals(0, sutInt.intValue());

		debug();
	}


	@Test
	public void multisetTest() {

		// ["word1", "word1", "word2", ]
		var mset = HashMultiset.<String>create();
		mset.add("word1");
		mset.add("word2");
		mset.add("word1");

		var c2 = mset.count("word1"); // result 2

		mset.remove("word1");
		var c1 = mset.count("word1"); // result 1

		mset.remove("word1");
		var c0 = mset.count("word1"); // result 0

		mset.remove("word1");
		var c00 = mset.count("word1"); // result 0

		assertEquals(c2, (2));
		assertEquals(c1, (1));
		assertEquals(c0, (0));
		assertEquals(c00, (0));

		debug();
	}

	private static void debug() {
	}

}

package com.github.sakaguchi3.jbatch002.javaapi;

import java.util.AbstractMap.SimpleEntry;

import org.junit.jupiter.api.Test;

public class TupleTest {

	/**
	 */
	@Test
	public void calcTest() {

		SimpleEntry<String, Integer> tuple2 = new SimpleEntry<>("key1", 2);
		String t1 = tuple2.getKey();
		Integer t2 = tuple2.getValue();

		System.out.println(tuple2);

		debug();
	}

	private void debug() {

	}

}

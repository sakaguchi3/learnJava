package com.github.sakaguchi3.jbatch002.guava;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.primitives.ImmutableLongArray;

public class CollectionImmutableTest {

	@Test
	public void aTest() {
		debug();
	}

	@Test
	public void ImmutableLongListTest() {
		var data = ImmutableLongArray.builder()//
				.add(3) //
				.add(4) //
				.add(5) //
				.add(6) //
				.build();
		assertEquals(4, data.length());
		debug();
	}

	@Test
	public void immutableListTest() {
		var data = ImmutableList.<String>builder() //
				.add("a") //
				.add("b") //
				.add("c") //
				.build();
		assertEquals(List.of("a", "b", "c"), data);
		debug();
	}

	@Test
	public void immutableMapTest() {
		var data = ImmutableMap.<String, String>builder() //
				.put("k1", "v1") //
				.put("k2", "v2") //
				.put("k3", "v3") //
				.build();
		var ans = Map.of(//
				"k1", "v1", //
				"k2", "v2", //
				"k3", "v3");
		assertEquals(ans, data);
		debug();
	}

	void debug() {

	}

}

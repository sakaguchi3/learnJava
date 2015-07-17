package com.github.sakaguchi3.jbatch002.vavr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.vavr.collection.HashMap;
import io.vavr.collection.HashMultimap;
import io.vavr.collection.LinkedHashMap;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.TreeMap;
import io.vavr.control.Option;

public class CollectionMapTest {

	@Test
	public void aTest() {
		debug();
	}

	@Test
	public void hashTblTest() {
		debug();
	}

	@Test
	public void map3Test() {
		// LinkedHashMap((k2, 2), (k1, 2), (k3, 3))
		var m1 = LinkedHashMap.of( //
				"k2", 2, //
				"k1", 1, //
				"k1", 2, //
				"k1", 2, //
				"k3", 3 //
		);

		// k2がreplaceされる. LinkedHashMap((k2, 22), (k1, 2), (k3, 3))
		var m2 = m1.replaceValue("k2", 22);

		// k9がないので変化なし. LinkedHashMap((k2, 2), (k1, 2), (k3, 3))
		var m3 = m1.replaceValue("k9", 9);

		final var a1 = HashMap.of("k1", 2, "k2", 2, "k3", 3);
		final var a2 = TreeMap.of("k1", 2, "k2", 22, "k3", 3);
		final var a3 = LinkedHashMap.of("k1", 2, "k2", 2, "k3", 3);

		assertEquals(a1, m1);
		assertEquals(a2, m2);
		assertEquals(a3, m3);

		debug();
	}

	@Test
	public void map2Test() {
		// 内部で はsortされる[k1, k2, k3]
		var m1 = TreeMap.of( //
				"k2", 2, //
				"k1", 1, //
				"k1", 2, //
				"k1", 2, //
				"k3", 3 //
		);
		assertEquals(3, m1.length());

		var m2 = m1.put("k4", 4);

		assertEquals(3, m1.length());
		assertEquals(4, m2.length());

		debug();
	}

	@Test
	public void multiMap2Test() {

		// immutable
		var mm1 = HashMultimap //
				.<Number>withSeq() //
				.<String, Integer>of("key1", 3, "key9", 9);

		// m1には追加されない. key1が複数putされる
		var mm2 = mm1.put(Map.entry("key1", 1)); // HashMultimap[List]((key1, 3), (key1, 1), (key9, 9))

		var v1 = mm1.get("key1"); // Some(List(3))
		var v2 = mm2.get("key1"); // Some(List(3, 1))

		var a1 = Option.of(List.of(3));
		var a2 = Option.of(List.of(3, 1));

		assertEquals(a1, v1);
		assertEquals(a2, v2);

		var mm3 = mm1.remove("key9");
		var v11 = mm1.get("key9");
		var v31 = mm3.get("key9");

		assertEquals(Option.of(List.of(9)), v11);
		assertEquals(Option.none(), v31);

		debug();
	}

	@Test
	public void listTest() {
		var l1 = List.of(3);
		var l2 = l1.append(4); // l1には追加されない。新たにappendされる。

		assertEquals(List.of(3), l1);
		assertEquals(List.of(3, 4), l2);
		debug();
	}

	@Test
	public void mapTest() {
		// HashMap((1, a), (2, b), (3, c), (9, dc))
		var c = HashMap.of(1, "a" //
				, 2, "b" //
				, 3, "c" //
				, 9, "c" //
				, 9, "ac" //
				, 9, "ac" //
				, 9, "bc" //
				, 9, "bc" //
				, 9, "dc" //
		);
		debug();
	}

	void debug() {
	}

}

package com.github.sakaguchi3.jbatch002.guava;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.Multiset;
import com.google.common.collect.Streams;

import io.vavr.Tuple;
import io.vavr.collection.Stream;

public class CollectionMultimapTest {

	@Test
	public void aTest() {
		debug();
	}

	@Test
	public void multiMap3Test() {

		// (String, List<Int>)
		Multimap<String, Integer> mm = MultimapBuilder.treeKeys() //
				.arrayListValues(5) //
				.build();
		// [(k1,1), (k1,1), (k1,1), (k1,2), (k1,3)]
		mm.put("k1", 1);
		mm.put("k1", 1);
		mm.put("k1", 1);
		mm.put("k1", 2);
		mm.put("k2", 3);

		// [1, 1, 1, 2, 3]
		var val = mm.values();
		// [k1 x 4, k2]
		Multiset<String> key = mm.keys();

		// answer --------------------

		var ansVal = List.of(1, 1, 1, 2, 3);
		var ansKey = HashMultiset.create(List.of("k1", "k1", "k1", "k1", "k2"));

		Stream.ofAll(val).zip(ansVal)//
				.forEach(v -> assertEquals(v._1(), v._2()));

		Streams.zip(val.stream(), ansVal.stream(), Tuple::of) //
				.forEach(v -> assertEquals(v._1(), v._2()));

		Streams.zip(val.stream(), ansVal.stream(), Pair::of) //
				.forEach(v -> assertEquals(v.getRight(), v.getLeft()));

		assertIterableEquals(ansVal, val);

		assertEquals(ansKey, key);

		debug();
	}

	@Test
	public void multiMap2Test() {

		// (String, List<Int>) = [(key1, val1:List), (key2, val2:List)]
		Multimap<String, Integer> treeListMultimap = MultimapBuilder.treeKeys() //
				.arrayListValues(5) //
				.build();

		// [(k1,1), (k1,1), (k1,1), (k1,2), (k1,3)]
		treeListMultimap.put("k1", 1);
		treeListMultimap.put("k1", 1);
		treeListMultimap.put("k1", 1);
		treeListMultimap.put("k1", 2);
		treeListMultimap.put("k2", 3);

		for (var e : treeListMultimap.entries()) {
			var k = e.getKey();
			var v = e.getValue();
			System.out.println(format("(%s, %s)", k, v));
		}
	}

	@Test
	public void multiMapTest() {

		// (String, List<Int>) = [(key1, val1:List), (key2, val2:List) ]
		Multimap<String, Integer> treeListMultimap = MultimapBuilder.treeKeys() //
				.arrayListValues(5) //
				.build();
		treeListMultimap.put("k1", 1);
		treeListMultimap.put("k1", 1);
		treeListMultimap.put("k1", 1);
		treeListMultimap.put("k1", 2);
		treeListMultimap.put("k2", 3);

		var d1 = treeListMultimap.get("k1"); // [1, 1, 1, 2]

		assertEquals(List.of(1, 1, 1, 2), d1);
		debug();
	}

	void debug() {
	}

}

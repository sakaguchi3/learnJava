package com.github.sakaguchi3.jbatch002.vavr;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.function.Function;

import org.junit.jupiter.api.Test;

import io.vavr.Function1;
import io.vavr.collection.Array;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.collection.Traversable;
import io.vavr.collection.Vector;

public class FlatmapTest {
	@Test
	public void bTest() {
		debug();
	}

	@Test
	void vavrLstflatMapTest() {
		Array<Traversable<Integer>> nestLst = Array.of(List.of(3, 4, 5), Vector.of(10, 11, 12));

		Array<Integer> flattenLst1 = nestLst.flatMap(Function.identity());
		Seq<Integer> flattenLst2 = nestLst.flatMap(Function1.identity());

		var ans1 = Array.of(3, 4, 5, 10, 11, 12);
		assertIterableEquals(ans1, flattenLst1);
		assertIterableEquals(ans1, flattenLst2); 
		debug();
	}

	void debug() {
	}

}

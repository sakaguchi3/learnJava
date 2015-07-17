package com.github.sakaguchi3.jbatch002.guava;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Streams;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.Stream;

public class StreamZipTest {

	@Test
	public void zipByVavrTest() {
		var names = ImmutableList.of("Alice", "Bob", "Charles", "Dragon");
		var ages = ImmutableList.of(42, 27, 31);
		List<Tuple2<String, Integer>> tuples = Stream //
				.ofAll(names) //
				.zip(ages) //
				.toJavaList();
		// [(Alice, 42), (Bob, 27), (Charles, 31)]
		tuples.forEach(v -> System.out.println(v));
		debug();
	}

	@Test
	public void zipByGuavaTest() {
		var names = ImmutableList.of("Alice", "Bob", "Charles", "Dragon");
		var ages = ImmutableList.of(42, 27, 31);
		List<Tuple2<String, Integer>> tuples = Streams //
				.zip(names.stream(), ages.stream(), Tuple::of) //
				.collect(ImmutableList.toImmutableList());
		// [(Alice, 42), (Bob, 27), (Charles, 31)]
		tuples.forEach(v -> System.out.println(v));
		debug();
	}

	void debug() {
	}

}

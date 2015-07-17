package com.github.sakaguchi3.jbatch002.guava;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;

public class CollectionRangeTest {

	@Test
	public void aTest() {

		debug();
	}


	@Test
	public void rangeTest() {
		var b1 = Range.closed(8, 11).contains(3);
		var b3 = Range.closed(8, 11).contains(9);
		var b9 = Range.closed(8, 11).containsAll(List.of(8, 11, 9, 10));

		var rs1 = Range.closed("a", "d");
		var rs2 = Range.open("x", "z");
		var rs3 = Range.open("xx", "z").intersection(Range.open("k", "yy"));
		debug();
	}

	
	@Test
	public void rangeToStreamTest() {
		ContiguousSet.create(Range.open(0, 4), DiscreteDomain.integers()) //
				.stream() //
				.collect(Collectors.toSet());
		debug();
	}


	void debug() {
	}

}

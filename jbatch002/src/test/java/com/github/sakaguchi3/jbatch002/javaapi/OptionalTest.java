package com.github.sakaguchi3.jbatch002.javaapi;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class OptionalTest {

	@Test
	void nullTest() {
		var op = Optional.of(1).map(__ -> null);
		assertTrue(op.isEmpty());

		debug();
	}

	@Test
	void optionalStreamTest() {

		var opLst = List.of( //
				Optional.ofNullable("op1"), //
				Optional.empty(), //
				Optional.ofNullable("op9"));
		var sut = opLst.stream() //
				.flatMap(Optional::stream) //
				.collect(Collectors.toList());
		var ans = List.of("op1", "op9");
		assertIterableEquals(ans, sut);

		debug();
	}

	private void debug() {

	}

}

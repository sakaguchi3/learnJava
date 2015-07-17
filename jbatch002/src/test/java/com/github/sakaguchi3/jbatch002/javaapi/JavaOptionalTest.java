package com.github.sakaguchi3.jbatch002.javaapi;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class JavaOptionalTest {

	@Test
	public void optionalStream2Test() {
		var op = Optional.ofNullable("optional");

		var ans = Stream.of("stream") //
				.flatMap(v -> op.stream()) //
				.map(v -> v) //
				.collect(Collectors.toList());

		debug();
	}

	@Test
	public void optionalStreamTest() {
		var i2List = List.of(//
				Optional.of("bb"), //
				Optional.of("x2"), //
				Optional.empty(), //
				Optional.empty(), //
				Optional.empty(), //
				Optional.of("aa") //
		);

		i2List.stream() //
				.flatMap(Optional::stream) //
				.forEach(System.out::println);
	}

	private void debug() {

	}

}

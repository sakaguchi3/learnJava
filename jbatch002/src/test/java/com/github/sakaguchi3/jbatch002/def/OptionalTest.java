package com.github.sakaguchi3.jbatch002.def;


 import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class OptionalTest {

	@Test
	public void uuuTest() {

		var u = new UUU();
		var expect_n01_01 = true;
		var expect_n02_01 = true;
		var expect_n02_02 = 103;

		Optional<Integer> n01op = Optional.ofNullable(u) //
				.flatMap(v -> Optional.ofNullable(v.n1)) //
				.map(v -> v + 100) //
		;

		Optional<Integer> n02op = Optional.ofNullable(u) //
				.flatMap(v -> Optional.ofNullable(v.n2)) //
				.map(v -> v + 100) //
		;

		assertEquals(n01op.isEmpty(),(expect_n01_01));

		assertEquals(n02op.isPresent(),(expect_n02_01));
		assertEquals(n02op.get(),(expect_n02_02));

		debug();
	}

	void debug() {

	}

}

class UUU {

	Integer n1 = null;
	Integer n2 = 3;

}

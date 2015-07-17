package com.github.sakaguchi3.jbatch002.vavr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import io.vavr.control.Either;

public class EitherTest {

	@Test
	public void aTest() {
		debug();
	}

	@Test
	public void either1Test() {
		Either<Throwable, String> l = Either.left(new RuntimeException());
		Either<Throwable, String> r = Either.right("success");

		l.forEach(v -> fail("msg:" + v));
		r.forEach(v -> assertEquals("success", v));

		debug();
	}

	void debug() {
	}

}

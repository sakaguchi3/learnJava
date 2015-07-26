package com.github.sakaguchi3.jbatch002.vavr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import io.vavr.control.Either;

public class EitherTest {

	@Test
	void eitherMapLeftTest() {
		var sut = Either.left(1) //
				.mapLeft(__ -> 2);
		assertTrue(sut.isLeft());
		assertEquals(2, sut.getLeft());
	}

	@Test
	void eitherMapRightTest() {
		var sut = Either.<Integer, String>right("success1") //
				.map(__ -> "success2");
		assertTrue(sut.isRight());
		assertEquals("success2", sut.get());
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

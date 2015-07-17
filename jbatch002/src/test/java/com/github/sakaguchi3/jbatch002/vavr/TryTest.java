package com.github.sakaguchi3.jbatch002.vavr;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import io.vavr.control.Try;

public class TryTest {

	@Test
	public void tryEitherTest() {
		var t1 = Try.of(() -> "ok1") //
				.mapTry(v -> "ok2") //
				.mapTry(v -> {
					throw new RuntimeException("err1");
				}) //
				.mapTry(v -> {
					throw new RuntimeException("err2");
				});

		assertFalse(t1.isSuccess());
		t1.onFailure(v -> v.getMessage()).onFailure(v -> {
			debug();
			assertTrue(v instanceof RuntimeException);
			assertTrue(v.getMessage().equals("err1"));
		}).onSuccess(v -> {
			fail();
		});
	}

	@Test
	public void try2Test() {
		var t1 = Try.of(() -> {
			throw new RuntimeException("msg");
		});

		var t2 = t1.recover(v -> Match(v).of( //
				Case($(instanceOf(RuntimeException.class)), "ok"), //
				Case($(instanceOf(IllegalAccessError.class)), "NG"), //
				Case($(), "default") //
		));
		assertEquals("ok", t2.get());

		var t3 = t1.recover(v -> v.getMessage());
		assertEquals("msg", t3.get());
	}

	void debug() {
	}

}

package com.github.sakaguchi3.jbatch002.vavr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.function.Function;

import org.junit.jupiter.api.Test;

import io.vavr.collection.Seq;
import io.vavr.control.Option;

public class OptionTest {
	@Test
	void flatmapNullTest() {
		var o = Option.of(1).flatMap(__ -> null);
		assertNull(o);
	}

	@Test
	void mapNullTest() {
		// Some(null)
		var op = Option.of(1).map(__ -> null);

		var o = op.get();
		assertNull(o);
	}

	@Test
	void funcTest() {
		var opNum1 = Option.of(1);
		Function<Integer, Function<Integer, Integer>> func = x -> y -> x + y;

		Option<Function<Integer, Integer>> opFun1 = opNum1.map(func);

		var opResult = opFun1.map(f -> f.apply(0));
		assertEquals(1, opResult.get());
	}

	@Test
	public void test01() {

		try {
			U u1 = new U();
			U u2 = null;

			Option<Integer> i1op = Option.of(u1) //
					.flatMap(v -> Option.of(v.i0)) //
			;

			Option<Integer> i2op = Option.of(u2) //
					.flatMap(v -> Option.of(v.i0));

			Seq<Integer> seq = Option.of(u1) //
					.flatMap(v -> Option.of(v.i0)) //
					.map(v -> v + 3)//
					.toList();

		} catch (Throwable e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		debug();
	}

	void debug() {

	}

	class U {
		Integer i0 = null;
	}
}

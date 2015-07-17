package com.github.sakaguchi3.jbatch002.vavr;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import io.vavr.collection.Seq;
import io.vavr.control.Option;


public class OptionTest {

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

package com.github.sakaguchi3.jbatch002.vavr;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.is;
import static io.vavr.Predicates.isIn;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class MatchTest {

	@Test
	public void aTest() {
		debug();
	}

	@Test
	public void optional2Test() {
		var op = Optional.empty();
		var c1 = Match(op).of(//
				Case($(Optional.of(10)), v -> "ng.[" + v.get() + "]"), //
				Case($(Optional.of(9)), v -> "ok.[" + v.get() + "]"), //
				Case($(Optional.empty()), "empty"), //
				Case($(), "ng"));

		assertEquals("empty", c1);
		debug();
	}

	@Test
	public void optionalTest() {
		var op = Optional.of(9);
		var c1 = Match(op).of(//
				Case($(Optional.of(10)), v -> "ng.[" + v.get() + "]"), //
				Case($(Optional.of(9)), v -> "ok.[" + v.get() + "]"), //
				Case($(Optional.empty()), "ng. empty"), //
				Case($(), "ng"));

		assertEquals("ok.[9]", c1);
		debug();
	}

	@Test
	public void helpTest() {
		var arg = "--help";

		var f1 = Match(arg).of( //
				Case($(is("-a")), "ng"), //
				Case($(isIn("-b", "--base")), "ng"), //
				Case($(isIn("-h", "--help")), "ok"), //
				Case($(), "ng") //
		);

		assertEquals("ok", f1);
		debug();
	}

	@Test
	public void m1Test() {
		var num = 3;
		var f1 = Match(num).of( //
				Case($(is(9)), "ng"), //
				Case($(isIn(1, 2, 4, 5, 6)), "ng"), //
				Case($(is(3)), "ok"), //
				Case($(is(15)), "ng"), //
				Case($(), "def") //
		);

		assertEquals("ok", f1);
		debug();
	}

	void debug() {
	}

}

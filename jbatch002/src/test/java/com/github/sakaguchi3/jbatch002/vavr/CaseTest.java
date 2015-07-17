package com.github.sakaguchi3.jbatch002.vavr;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Patterns.$Failure;
import static io.vavr.Patterns.$Invalid;
import static io.vavr.Patterns.$Left;
import static io.vavr.Patterns.$None;
import static io.vavr.Patterns.$Right;
import static io.vavr.Patterns.$Some;
import static io.vavr.Patterns.$Success;
import static io.vavr.Patterns.$Valid;
import static io.vavr.Predicates.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import io.vavr.Tuple3;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import io.vavr.control.Validation;
import io.vavr.control.Validation.Invalid;
import io.vavr.control.Validation.Valid;
import testcase.ValidationFuga;

public class CaseTest {

	@Test
	void tryTest() {
		var tryErr = Try.of(() -> 3 / 0);
		var trySuc = Try.of(() -> 3);

		var sutErr = Match(tryErr).of(//
				Case($Success($()), false), //
				Case($Failure($()), true), //
				Case($(), false));
		var sutSuc = Match(trySuc).of(//
				Case($Failure($()), false), //
				Case($Success($(2)), false), //
				Case($Success($()), true), //
				Case($(), false));

		assertTrue(sutErr);
		assertTrue(sutSuc);
	}

	@Test
	void eitherTest() {
		var lft = Either.left(new RuntimeException());
		var rht = Either.right("success");

		var sutL = Match(lft).of( //
				Case($Left($(new NullPointerException())), false), //
				Case($Left($(new RuntimeException())), false), //
				Case($Left($(instanceOf(RuntimeException.class))), true), //
				Case($Right($("success")), false), //
				Case($(), false));

		var sutR = Match(rht).of( //
				Case($Left($()), false), //
				Case($Right($("success")), true), //
				Case($(), true));

		assertTrue(sutL);
		assertTrue(sutR);
	}

	@Test
	void optionTest() {

		Option<Integer> ret02 = Option.some(3);

		String str01 = Match(ret02).of(//
				Case($Some($(3)), "some3"), //
				Case($Some($()), "someNoMuch"), //
				Case($None(), "none") //
		);

		String str02 = Match(Option.of(3)).of(//
				Case($Some($(2)), "some2"), //
				Case($Some($()), "someNoMuch"), //
				Case($None(), "none") //
		);

		String str03 = Match(Option.none()).of(//
				Case($Some($(2)), "some2"), //
				Case($Some($()), "someNoMuch"), //
				Case($None(), "none") //
		);

		assertEquals("some3", str01);
		assertEquals("someNoMuch", str02);
		assertEquals("none", str03);

		debug();
	}

	@Test
	void validationTest() {
		var valid = new ValidationFuga();

		Validation<Seq<String>, Tuple3<String, Integer, Double>> ok = valid.validate("a", 3, 9d);
		Validation<Seq<String>, Tuple3<String, Integer, Double>> ng = valid.validate("c", -8, 7d);

		var sutok = Match(ok).of(//
				Case($Invalid($()), false), //
				Case($Valid($()), true), //
				Case($(), false));

		var sutng = Match(ng).of(//
				Case($Valid($()), false), //
				Case($Invalid($()), true), //
				Case($(), false));

		assertTrue(sutok);
		assertTrue(sutng);
	}

	@Test
	void validation2Test() {
		var valid = new ValidationFuga();

		Validation<Seq<String>, Tuple3<String, Integer, Double>> ok = valid.validate("a", 3, 9d);
		Validation<Seq<String>, Tuple3<String, Integer, Double>> ng = valid.validate("c", -8, 7d);

		var cok = Match(ok).of(//
				Case($(instanceOf(Invalid.class)), "ng"), //
				Case($(instanceOf(Valid.class)), "ok"), //
				Case($(), "ng"));

		var cng = Match(ng).of(//
				Case($(instanceOf(Valid.class)), "ng"), //
				Case($(instanceOf(Invalid.class)), "ok"), //
				Case($(), "ng"));

		assertEquals("ok", cok);
		assertEquals("ok", cng);

		debug();
	}

	@Test
	public void tryRecoverTest() {
		var t1 = Try.of(() -> {
			throw new RuntimeException("msg");
		});

		var t2 = t1.recover(v -> Match(v).of( //
				Case($(instanceOf(RuntimeException.class)), RuntimeException::getMessage), //
				Case($(instanceOf(IllegalAccessError.class)), "NG"), //
				Case($(), "default") //
		));
		assertTrue(t2.isSuccess());
		assertEquals("msg", t2.get());
	}

	@Test
	public void try4Test() {
		var t1 = Try.of(() -> {
			throw new RuntimeException("msg");
		});

		var t2 = t1.recover(v -> Match(v).of( //
				Case($(instanceOf(RuntimeException.class)), 3), //
				Case($(instanceOf(IllegalAccessError.class)), 4), //
				Case($(), 0) //
		));
		assertTrue(t2.isSuccess());
		assertEquals(3, t2.get());
	}

	@Test
	void javaOpTest() {
		// convert javaOp to vavrOp
		var vavrOp = Option.ofOptional(Optional.of(3));
		var sutCase = Match(vavrOp).of( //
				Case($Some($(99)), "ng"), //
				Case($Some($(3)), "ok"), //
				Case($None(), "ng"), //
				Case($(), "ng") // default
		);
		assertEquals("ok", sutCase);
		debug();
	}

	@Test
	public void caseTest() {

		Integer lll = 99;

		String s = Match(lll).of( //
				Case($(1), "one"), //
				Case($(2), "two"), //
				Case($(3), "three"), //
				Case($(), "def") // default
		);
		assertEquals("def", s);

		Object obj = new Object();
		Number numMinusOne = Match(obj).of(//
				Case($(instanceOf(Integer.class)), 5), //
				Case($(instanceOf(Double.class)), 5.9), //
				Case($(), -1) //
		);
		assertEquals(-1, numMinusOne);

		Option<Number> opNone = Match(obj).<Number>option(//
				Case($(instanceOf(Integer.class)), 5), //
				Case($(instanceOf(Double.class)), 5.9) //
		);
		assertEquals(Option.none(), opNone);

		debug();
	}

	void debug() {

	}

}

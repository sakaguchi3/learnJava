package com.github.sakaguchi3.jbatch002.vavr;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import static java.time.Duration.*;
import static java.util.Optional.ofNullable;
import static java.util.Collections.*;
import static java.util.Comparator.*;
import static java.util.Objects.*;
import static java.util.Optional.*;
import static java.util.stream.Collectors.*;
import static java.time.Duration.*;
import static java.util.Collections.*;
import static java.util.Comparator.*;
import static java.util.Objects.*;
import static java.util.Optional.*;
import static java.util.stream.Collectors.*;
import static java.util.stream.Stream.*;
import static io.vavr.collection.HashMap.*;
import static io.vavr.collection.HashMultimap.*;
import static io.vavr.collection.HashSet.*;
import static io.vavr.control.Try.*;
import static io.vavr.API.*;
import static io.vavr.API.Match.*;
import static io.vavr.Predicates.*;
import io.vavr.API.*;
import io.vavr.control.*;
import org.apache.logging.log4j.*;
import java.util.Collections.*;
import java.util.*;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.For;
import static io.vavr.API.Match;
import static io.vavr.API.None;
import static io.vavr.API.Some;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.google.common.base.Optional;

import io.vavr.API;
import io.vavr.Function1;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import io.vavr.collection.Array;
import io.vavr.collection.Iterator;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.collection.Stream;
import io.vavr.control.Option;
import io.vavr.control.Validation;
import testcase.ValidationFuga;

public class ForTest {

	@Test
	void validationFailForTest() {
		var valid = new ValidationFuga();

		Validation<Seq<String>, Tuple3<String, Integer, Double>> ok1 = valid.validate("a", 3, 9d);
		Validation<Seq<String>, Tuple3<String, Integer, Double>> ok2 = valid.validate("c", -8, 7d);

		var seq = For(ok1, ok2) //
				.yield((v1, v2) -> Array.of(//
						Tuple.of(v1._1(), v2._1()), //
						Tuple.of(v1._2(), v2._2()), //
						Tuple.of(v1._3(), v2._3())))//
				.flatMap(Function1.identity()) //
				.toArray();
		assertTrue(seq.isEmpty());
		debug();
	}

	@Test
	void validationForTest() {
		var valid = new ValidationFuga();

		Validation<Seq<String>, Tuple3<String, Integer, Double>> ok1 = valid.validate("a", 3, 9d);
		Validation<Seq<String>, Tuple3<String, Integer, Double>> ok2 = valid.validate("c", 8, 7d);

		var seq = For(ok1, ok2) //
				.yield((v1, v2) -> Array.of(//
						Tuple.of(v1._1(), v2._1()), //
						Tuple.of(v1._2(), v2._2()), //
						Tuple.of(v1._3(), v2._3())))//
				.flatMap(Function1.identity()) //
				.toArray();
		assertFalse(seq.isEmpty());
		debug();
	}

	@Test
	public void javaOpTest() {

		var o1 = java.util.Optional.of(1);
		var o2 = java.util.Optional.of("s2");
		var o3 = java.util.Optional.of(3.0d);

		// err. cannot use java Optional
		// var f1 = For(o1, o2, o3);
		debug();
	}

	@Test
	public void vavrOpTest() {

		var o1 = Option.of(1);
		var o2 = Option.of("s2");
		var o3 = Option.of(3.0d);

		var f1 = For(o1, o2, o3) //
				.yield(Tuple::of) //
				.map(v -> v.apply((x, y, z) -> y)) // (int, str, double)->str
		;

		var c1 = Match(f1).of( //
				Case($(None()), "ng1"), //
				Case($(Some("s9")), "ng9"), //
				Case($(Some("s2")), "ok"), //
				Case($(), "ng0") //
		);

		assertEquals(o2, f1);
		assertEquals("ok", c1);
		debug();
	}

	@Test
	public void guavaOpTest() {
		var some = Optional.of(3);
		var none = Optional.<Number>absent();
		var some2 = some.transform(v -> v + 3);

		assertEquals(3, some.get()); // Immutable
		assertEquals(6, some2.get()); //

		debug();
	}

	@Test
	public void scalaOpTest() {
		var scalaSome = akka.japi.Option.option(1);
		var scalaSome2 = akka.japi.Option.option(2);
		var scalaNone = akka.japi.Option.option(null);
		var scalaNone2 = akka.japi.Option.none();

//		System.out.println(scalaSome);
//		System.out.println(scalaSome2);
//		System.out.println(scalaNone);
//		System.out.println(scalaNone2);

		// List((1, 2))
		var for1 = For(scalaSome, scalaSome2) //
				.yield(Tuple::of) //
				.map(v -> String.format("(%d, %d)", v._1(), v._2())) //
				.toList();
		debug();
	}

	@Test
	public void vavrLstTest() {
//		System.out.println("vavrLstTest -------------------");
		var j1 = List.of(1, 2);
		var j2 = List.of(99, 98, 97, 96);
		var f1 = For(j1, j2) //
				.yield(Tuple::of) //
				// (1, 99)
//				.peek(System.out::print) //
				.map(v -> v._1() + v._2()) //
				.toList();
//		System.out.println();
		// List(100, 99, 98, 97, 101, 100, 99, 98)
//		System.out.println(f1);
		debug();
	}

	@Test
	public void javaLstTest() {
//		System.out.println("javaLstTest -------------------");
		var j1 = Arrays.asList(1, 2);
		var j2 = Arrays.asList(99, 98, 97, 96);

		var f1 = For(j1, j2) //
				.yield(Tuple::of) //
				// (1, 99)(1, 98)(1, 97)(1, 96)(2, 99)(2, 98)(2, 97)(2, 96)
//				.peek(System.out::print) //
				.map(v -> v._1() + v._2()) //
				.toList();
//		System.out.println();
		// List(100, 99, 98, 97, 101, 100, 99, 98)
//		System.out.println(f1);
		debug();
	}

	@Test
	public void vavrListForYieldTest() {

		// len = (param1.len * param2.len).
		// List(101, 103, 104, 201, 203, 204, 301, 303, 304, 401, 403, 404)
		List<Integer> ansList01 = API.For( //
				List.of(100, 200, 300, 400), //
				List.of(1, 3, 4)) //
				.yield((v1, v2) -> v1 + v2);

		assertEquals(ansList01.size(), (12));

		debug();
	}

	@Test
	public void optionForYieldTest() {

		// Some(5)
		Option<Integer> ret01 = For(//
				Option.of(3), //
				Option.of(2) //
		).yield((v1, v2) -> v1 + v2);

		assertEquals(ret01.isDefined(), (true));
		assertEquals(ret01.get(), (5));
		assertEquals(ret01, (Option.of(5)));

		debug();
	}

	@Test
	public void optionForYield2Test() {
		// Noneが含まれていると実行されない
		// None
		Option<Integer> sut01 = For(//
				Option.of(3), //
				Option.<Integer>none() //
		).yield((v1, v2) -> v1 + v2);

		// Noneが含まれていると実行されない
		Option<Integer> sut02 = For(//
				Option.of(3), //
				Option.of(4), //
				Option.<Integer>none() //
		).yield((v1, v2, v3) -> v1 + v2);

		assertEquals(sut01.isEmpty(), true);
		assertEquals(sut02, Option.none());
		debug();
	}

	@Test
	public void listForMergeTest() {

		// java list
		var jlist01 = Arrays.<Integer>asList(3, 4);
		var jlist02 = Arrays.<Integer>asList(100, 200, 300, 400);

		// convert javaList to vavrList
		List<Integer> vList01 = List.ofAll(jlist01);
		List<Integer> vList02 = List.ofAll(jlist02);

		// List(103, 203, 303, 403, 104, 204, 304, 404)
		List<Integer> ret02 = For(vList01, vList02) //
				.yield((v1, v2) -> v1 + v2);

		assertEquals(ret02.size(), (8));

		debug();
	}

	@Test
	public void test06() {

		List<Integer> ret02 = For( //
				List.ofAll(Arrays.asList(1, 9)), //
				List.ofAll(Arrays.asList(100, 300, 200)) //
		).yield((v1, v2) -> v1 + v2);

		List<Tuple2<Integer, Integer>> retTup = For( //
				List.ofAll(Arrays.asList(1, 9)), //
				List.ofAll(Arrays.asList(100, 300, 200)) //
		).yield((v1, v2) -> Tuple.of(v1, v2));

		assertEquals(ret02.size(), (6));

		debug();
	}

	@Test
	public void test07() {

		Iterator<Integer> ret02 = For( //
				(Arrays.asList(1, 9)), //
				(Arrays.asList(100, 300, 200)) //
		).yield((v1, v2) -> v1 + v2);

		debug();

		assertEquals(ret02.size(), (6));
		assertEquals(ret02.isLazy(), (true));

		debug();
	}

	@Test
	public void test08() {

		// size: 2*3=6
		// List(11, 12, 13, 19, 20, 21)
		List<Integer> ret02 = List.of(1, 9) //
				.flatMap(v1 -> List.of(10, 11, 12).map(v2 -> v1 + v2));
		debug();

		assertEquals(ret02.size(), (6));

		debug();
	}

	@Test
	public void test09() {

		Iterator<Integer> ret01 = For( //
				Stream.ofAll(Arrays.asList(1, 2)), //
				Stream.ofAll(Arrays.asList(100, 200, 300, 400)) //
		).yield((v1, v2) -> v1 + v2);

		// 評価されていない
		debug();

		assertEquals(ret01.size(), (8));
		assertEquals(ret01.isLazy(), (true));

		// 評価される
		debug();

	}

	void debug() {
	}

}

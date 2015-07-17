package com.github.sakaguchi3.jbatch002.vavr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.NoSuchElementException;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import io.vavr.Tuple3;
import io.vavr.collection.Seq;
import io.vavr.collection.Vector;
import io.vavr.control.Validation;
import testcase.ValidationFuga;

public class ValidationTest {

	private static final Logger LOG = LogManager.getLogger();

	@Test
	public void validFugaTest() {
		var valid = new ValidationFuga();

		Validation<Seq<String>, Tuple3<String, Integer, Double>> rOk = valid.validate("a", 3, 9d);
		Validation<String, Tuple3<String, Integer, Double>> jOk = rOk.mapError(Seq::mkString);

		Validation<Seq<String>, Tuple3<String, Integer, Double>> rNg = valid.validate("", -3, -8d);
		Validation<String, Tuple3<String, Integer, Double>> jNg = rNg.mapError(Seq::mkString);

		// right merge
		Validation<Seq<String>, Double> kOkRightMerge = rOk.map(v -> v._2() + v._3());
		// left merge
		Validation<Integer, Tuple3<String, Integer, Double>> kNgLeftMerge = jNg.mapError(v -> v.length());

		debug();
	}

	@Test
	public void valid1Test() {
		var valid = new PersonValidation();
		Validation<Seq<String>, Person> personOk = valid.validatePerson("aa", 58);
		Validation<Seq<String>, Person> personNg = valid.validatePerson("98 kk _", -1);

		assertTrue(personOk.isValid());
		assertFalse(personNg.isValid());

		if (personOk.isValid()) {
			// Person(aa, 58)
			var person = personOk.get();

			var person2 = Validation//
					.combine(Validation.<String, String>invalid("aa"), Validation.<String, Integer>invalid("jj")) //
					.ap((v, x) -> (new Person("", 59)));

			// applicative target
			Validation<Seq<Seq<String>>, Function<Person, Integer>> v1 = Validation.invalid(Vector.of(Vector.of("a")));
			Validation<Seq<Seq<String>>, Function<Person, Integer>> v2 = Validation.valid(p -> p.age);

			// applicative
			Validation<Seq<Seq<String>>, Integer> vap1 = personOk.ap(v1);
			Validation<Seq<Seq<String>>, Integer> vap2 = personOk.ap(v2);

			// flatten
			Validation<Seq<String>, Integer> wap1 = vap1.mapError(seq -> seq.flatMap(Function.identity()));
			Validation<Seq<String>, Integer> wap2 = vap2.mapError(seq -> seq.flatMap(Function.identity()));

			debug();

//			System.out.println(person);
		} else {
			fail();
		}
		if (personNg.isValid()) {
			fail();
		} else {
			// List(Name contains invalid characters: '89_', Age must be at least 0)
			var err = personNg.getError();
//			System.out.println(err);
		}

		debug();
	}

	@Test
	public void personToStringTest() {
		debug();
		var person = new Person("John Rock", 48);
		var perStr = person.toString();
		debug();
	}

	@Test
	public void validTest() {
		debug();
		var valid = new PersonValidation();
		var validPerson = valid.validatePerson("abc xxx", 58);
		var person = validPerson.get();

		assertTrue(validPerson.isValid());
		assertThrows(NoSuchElementException.class, () -> {
			validPerson.getError();
		});

		assertEquals(58, person.age);
		assertEquals("abc xxx", person.name);
		assertEquals(new Person("abc xxx", 58), person);
		debug();
	}

	@Test
	public void invalidTest() {
		debug();
		final var valid = new PersonValidation();

		final var invalidAge = valid.validatePerson("abc xyz", -1);
		final var invalidName = valid.validatePerson("abc 999", 10);
		final var invalidBoth = valid.validatePerson("[] abc 999", -8);

		assertFalse(invalidAge.isValid());
		assertFalse(invalidName.isValid());
		assertFalse(invalidBoth.isValid());

		assertEquals(1, invalidAge.getError().size());
		assertEquals(1, invalidName.getError().size());
		assertEquals(2, invalidBoth.getError().size());

		final var errMsgAge = invalidAge.getError();
		final var errMsgName = invalidName.getError();
		final var errMsgBoth = invalidBoth.getError();

//		LOG.trace(errMsgAge);
//		LOG.trace(errMsgName);
//		LOG.trace(errMsgBoth);

		debug();
	}

	void debug() {
	}
}

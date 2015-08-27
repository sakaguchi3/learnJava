package com.github.sakaguchi3.jbatch002.vavr;

import static io.vavr.control.Validation.invalid;
import static io.vavr.control.Validation.valid;

import io.vavr.collection.CharSeq;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

/**
 * 
 */
public class PersonValidation {

	public Validation<Seq<String>, Person> validatePerson(String name, int age) {
		var ret = Validation.combine(validateName(name), validateAge(age)) //
				.ap(Person::new);
		return ret;
	}

	private Validation<String, String> validateName(String name) {
		final var VALID_NAME_CHARS_REGEX = "[a-zA-Z ]";
		final Validation<String, String> validation = CharSeq.of(name)//
				.replaceAll(VALID_NAME_CHARS_REGEX, "") //
				.transform(seq -> seq.isEmpty() ? //
						valid(name) : invalid("Name contains invalid characters: '" + seq.distinct().sorted() + "'"));
		return validation;
	}

	private Validation<String, Integer> validateAge(int age) {
		final var MIN_AGE = 000;
		final var MAX_AGE = 130;

		if (age < MIN_AGE) {
			return Validation.invalid("Age must be at least " + MIN_AGE);
		}
		if (MAX_AGE < age) {
			return Validation.invalid("Age must be at most " + MAX_AGE);
		}

		return Validation.valid(age);
	}

}

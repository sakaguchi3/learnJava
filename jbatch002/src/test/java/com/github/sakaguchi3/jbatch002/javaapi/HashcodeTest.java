package com.github.sakaguchi3.jbatch002.javaapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

public class HashcodeTest {

	@Test
	void hashCodeTest() {
		var l1 = List.of("a");
		var l2 = List.of("a");
		var l3 = Arrays.asList("a");

		var h1 = l1.hashCode();
		var h2 = l2.hashCode();
		var h3 = l3.hashCode();

		assertTrue(l1.hashCode() == l3.hashCode());

		var hh1 = Objects.hashCode(l1);
		var hh2 = l1.hashCode();

		assertEquals(hh1, hh2);

		var j1 = Objects.hash(l1);
		var j2 = Objects.hash(l2);
		var j3 = Objects.hash(l3);

		assertTrue(Objects.hash(j1) == Objects.hash(j3));

		var d1 = l1 == l2;
		var d2 = l1 == l3;

		assertFalse(l1 == l2);

		d();
	}

	// class ------------------------------

	private void d() {
	}

}

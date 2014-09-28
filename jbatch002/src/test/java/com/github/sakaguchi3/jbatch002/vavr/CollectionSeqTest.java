package com.github.sakaguchi3.jbatch002.vavr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import io.vavr.collection.Array;
import io.vavr.collection.List;

public class CollectionSeqTest {

	@Test
	void lstAppendTest() {
		// List(1)
		var lst1 = List.of(1);
		// List(1, 2)
		var lst2 = lst1.append(2);


		assertIterableEquals(List.of(1, 2), lst2);
		
		// immutable
		assertIterableEquals(List.of(1), lst1);

		d();
	}

//	@Test
	void foldSumTest() {
		var l1 = Array.of(1, 2, 3);

		var sutfl = l1.foldLeft(0, (acc, y) -> y + acc);
		var sutfr = l1.foldRight(0, (x, acc) -> x + acc);

		assertEquals(6, sutfl);
		assertEquals(6, sutfr);

		d();
	}

//	@Test
	void foldLstTest() {
		var l1 = Array.of(1, 2, 3);

		// List(1, 2, 3)
		var sutfl = l1.foldLeft(List.of(), (acc, y) -> acc.append(y));
		// List(3, 2, 1)
		var sutfr = l1.foldRight(List.of(), (x, acc) -> acc.append(x));

		assertIterableEquals(List.of(1, 2, 3), sutfl);
		assertIterableEquals(List.of(3, 2, 1), sutfr);
		d();
	}

//	@Test
	public void vavrStreamToLstTest() {
		var l = List.of(3, 9, 10, 15).toStream() //
				.map(v -> v + 100) //
				.toList();
		var ans = List.of(103, 109, 110, 115);
		assertIterableEquals(ans, l);
		d();
	}

//	@Test
	public void convJavatoVavrLstTest() {

		// java list
		var javaList01 = Arrays.asList(3, 4);
		var javaList02 = Arrays.asList(100, 200, 300, 400);

		// vavr list
		List<Integer> vavrList01 = List.ofAll(javaList01);
		List<Integer> vavrList02 = List.ofAll(javaList02);
		d();
	}

	private void d() {
	}

}

package com.github.sakaguchi3.jbatch002.javaapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class CompareTest {

	/**
	 * lhs - rhs <br>
	 * <ul>
	 * <li>-1 : lhs < rhs</li>
	 * <li>0: lhs = rhs</li>
	 * <li>+1: lhs > rhs</li>
	 * </ul>
	 */
	@Test
	public void listSortTest() {

		System.out.println("-------------------listSortTest");

		List<Integer> l = Arrays.asList(5, 10, 3);
		List<Integer> m = null;

		List<Integer> ans01 = List.of(3, 5, 10);
		List<Integer> ans02 = List.of(10, 5, 3);

		// 3, 5, 10 ----------------------------

		l.sort((i1, i2) -> i1 - i2);
		System.out.println(l);
		assertEquals(l, (ans01));

		l.sort((i1, i2) -> i1.compareTo(i2));
		System.out.println(l);
		assertEquals(l, (ans01));

		l.sort(Comparator.naturalOrder());
		System.out.println(l);
		assertEquals(l, (ans01));

		// 10, 5, 3 ----------------------------

		l.sort((i1, i2) -> i2 - i1);
		System.out.println(l);
		assertEquals(l, (ans02));

		l.sort((i1, i2) -> i2.compareTo(i1));
		System.out.println(l);
		assertEquals(l, (ans02));

		l.sort(Comparator.reverseOrder());
		System.out.println(l);
		assertEquals(l, (ans02));

	}

	/**
	 * lhs - rhs <br>
	 * <ul>
	 * <li>-1 : lhs < rhs</li>
	 * <li>0: lhs = rhs</li>
	 * <li>+1: lhs > rhs</li>
	 * </ul>
	 */
	@Test
	public void streamSortTest() {

		System.out.println("-------------------streamSortTest");

		List<Integer> l = Arrays.asList(5, 10, 3);
		List<Integer> m = null;

		List<Integer> ans01 = List.of(3, 5, 10);
		List<Integer> ans02 = List.of(10, 5, 3);

		// 3, 5, 10 -----------------

		m = l.stream() //
				.sorted(Comparator.naturalOrder()) //
				.collect(Collectors.toList());
		System.out.println(m);
		assertEquals(m, (ans01));

		m = l.stream() //
				.sorted((i1, i2) -> i1 - i2) //
				.collect(Collectors.toList());
		System.out.println(m);
		assertEquals(m, (ans01));

		m = l.stream() //
				.sorted((i1, i2) -> i1.compareTo(i2)) //
				.collect(Collectors.toList());
		System.out.println(m);
		assertEquals(m, (ans01));

		// 10, 5, 3 --------------------

		m = l.stream() //
				.sorted(Comparator.reverseOrder()) //
				.collect(Collectors.toList());
		System.out.println(m);
		assertEquals(m, (ans02));

		m = l.stream() //
				.sorted((i1, i2) -> i2 - i1) //
				.collect(Collectors.toList());
		System.out.println(m);
		assertEquals(m, (ans02));

		m = l.stream() //
				.sorted((i1, i2) -> i2.compareTo(i1)) //
				.collect(Collectors.toList());
		System.out.println(m);
		assertEquals(m, (ans02));
	}

	/**
	 * lhs - rhs
	 */
	@Test
	public void compareToTest() {

		System.out.println("-------------------compareTest");

		Integer i10 = 10;
		Integer i20 = 20;
		Integer i20_02 = 20;
		Integer i30 = 30;

		System.out.printf("20 compare 20 := %2d\n", i20.compareTo(i20_02)); // 0
		System.out.printf("20 compare 10 := %2d\n", i20.compareTo(i10)); // +1
		System.out.printf("20 compare 30 := %2d\n", i20.compareTo(i30)); // -1

		assertEquals(i20.compareTo(i20_02), (0));
		assertEquals(i20.compareTo(i10), (1));
		assertEquals(i20.compareTo(i30), (-1));

	}

	private void debug() {

	}

}

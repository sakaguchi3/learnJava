package com.github.sakaguchi3.jbatch002.javaapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

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

	@Test
	void compareATest() {
		var p11 = new A(1);
		var p20 = new A(2);
		var p12 = new A(1);

		var c1 = p11.compareTo(p20);
		var c2 = p20.compareTo(p12);
		var c3 = p11.compareTo(p12);

		assertEquals(0, c3);
		assertEquals(1, c2);
		assertEquals(-1, c1);
	}

	@Test
	void compareBTest() {
		var a1 = new A(1);
		var a2 = new A(2);
		var diffa = a1.compareTo(a2);
		assertEquals(-1, diffa);

		var b1 = new B(1);
		var b2 = new B(2);
		var diffb = b1.compareTo(b2);
		assertEquals(1, diffb);
	}

	@Test
	void compareCTest() {
		var a1 = new C(1, 1);
		var a2 = new C(1, 2);
		var a3 = new C(2, -1);

		var d21 = a2.compareTo(a1);
		var d23 = a2.compareTo(a3);
		var d13 = a1.compareTo(a3);

		assertEquals(+1, d21);
		assertEquals(-1, d23);
		assertEquals(-1, d13);
	}

	private void debug() {
	}

	/** natural order */
	private static class A implements Comparable<A> {
		final int i;

		A(int i) {
			this.i = i;
		}

		@Override
		public int compareTo(A that) {
			return ComparisonChain.start() //
					.compare(this.i, that.i) //
					.result();
		}
	}

	/** revere order */
	private static class B implements Comparable<B> {
		final int i;

		B(int i) {
			this.i = i;
		}

		@Override
		public int compareTo(B that) {
			return ComparisonChain.start() //
					.compare(this.i, that.i, Ordering.natural().reverse()) //
					.result();
		}
	}

	/** natural order */
	private static class C implements Comparable<C> {
		final int a;
		final int b;

		C(int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(C that) {
			return ComparisonChain.start() //
					.compare(this.a, that.a) //
					.compare(this.b, that.b) //
					.result();
		}
	}

}

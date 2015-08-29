package com.github.sakaguchi3.jbatch002.javaapi;

import static java.util.Comparator.comparing;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

import org.junit.jupiter.api.Test;

public class CollectionPriorityQueueTest {

	@Test
	public void aTest() {
		debug();
	}

	@Test
	public void myCompare5Test() throws InterruptedException {
		Comparator<NoMyComparable> compare = Comparator.comparing(t -> t.priority);
		var pq = new PriorityBlockingQueue<NoMyComparable>(10, compare);

		var unsorted = List.of( //
				NoMyComparable.of(10), //
				NoMyComparable.of(20), //
				NoMyComparable.of(1), //
				NoMyComparable.of(90), //
				NoMyComparable.of(6) //
		);
		unsorted.forEach(v -> pq.add(v));

		// 1 6 10 20 90
		while (!pq.isEmpty()) {
			var aa = pq.take();
			System.out.print(aa + " ");
			debug();
		}
		
		System.out.println();
		debug();
	}

	@Test
	public void myCompare4Test() throws InterruptedException {
		var pqNatural = new PriorityBlockingQueue<MyComparable>();
		var unsorted = List.of( //
				MyComparable.of(10), //
				MyComparable.of(20), //
				MyComparable.of(1), //
				MyComparable.of(90), //
				MyComparable.of(6) //
		);
		unsorted.forEach(v -> {
			pqNatural.add(v);
		});
		System.out.println("\n" + "c4");

		// 1 6 10 20 90
		while (!pqNatural.isEmpty()) {
			var aa = pqNatural.take();
			System.out.print(aa + " ");
			debug();
		}

		System.out.println();
		debug();
	}

	@Test
	public void myCompare3Test() {
		var pq = new PriorityQueue<NoMyComparable>();
		var pqNoErr = new PriorityQueue<NoMyComparable>(comparing(t -> t.priority));
		// これは出来ません!!!!!
//		var pq = new PriorityBlockingQueue<NoMyComparable>(comparing(t -> t.priority));

		var unsorted = List.of( //
				NoMyComparable.of(10), //
				NoMyComparable.of(20), //
				NoMyComparable.of(1), //
				NoMyComparable.of(90), //
				NoMyComparable.of(6) //
		);
		assertThrows(ClassCastException.class, () -> {
			unsorted.forEach(v -> pq.add(v));
		});
		assertDoesNotThrow(() -> {
			unsorted.forEach(v -> pqNoErr.add(v));
		});

		// [1, 6, 10, 90, 20]
		System.out.println(pqNoErr);
		debug();
	}

	@Test
	public void myCompare2Test() {
		var pqNatural = new PriorityBlockingQueue<MyComparable>();
		var pqReverse = new PriorityQueue<>(comparing((MyComparable t) -> t.priority).reversed());
		var unsorted = List.of( //
				MyComparable.of(10), //
				MyComparable.of(20), //
				MyComparable.of(1), //
				MyComparable.of(90), //
				MyComparable.of(6) //
		);
		unsorted.forEach(v -> {
			pqNatural.add(v);
			pqReverse.add(v);
		});

		System.out.println("\n" + "natural order");
		// 1 6 10 20 90
		while (!pqNatural.isEmpty()) {
			System.out.print(pqNatural.poll() + " ");
		}

		System.out.println("\n" + "reverse order");

		// 90 20 10 6 1
		while (!pqReverse.isEmpty()) {
			System.out.print(pqReverse.poll() + " ");
		}

		System.out.println("\n");
		debug();
	}

	@Test
	public void myCompareTest() {
		var unsorted = List.of( //
				MyComparable.of(10), //
				MyComparable.of(20), //
				MyComparable.of(1), //
				MyComparable.of(90), //
				MyComparable.of(6) //
		);

		var sorted = new ArrayList(unsorted);
		Collections.sort(sorted);

		// [10, 20, 1, 90, 6]
		System.out.println(unsorted);
		// [1, 6, 10, 20, 90]
		System.out.println(sorted);

		debug();
	}

	@Test
	public void IntagerTest() {
		// 内部ではstackとしてデータが保存されている(?)
		var pq = new PriorityQueue();

		// 挿入
		pq.add(2);
		pq.add(3);
		pq.add(1);

		// 1 3 2
		pq.forEach(System.out::println);
		System.out.println("------------");

		// 1 3 2
		for (var j : pq) {
			System.out.println(j);
		}
		System.out.println("------------");

		// 1 2 3
		while (!pq.isEmpty()) {
			System.out.println(pq.poll());
		}
		System.out.println("------------");

		debug();
	}

	void debug() {
	}

	static class MyComparable implements Comparable<MyComparable> {
		public int priority = 0;

		public static MyComparable of(int priority) {
			var a = new MyComparable();
			a.priority = priority;
			return a;
		}

		@Override
		public int compareTo(MyComparable that) {
			return Integer.compare(this.priority, that.priority);
		}

		@Override
		public String toString() {
			return String.valueOf(priority);
		}
	}

	static class NoMyComparable {
		public int priority = 0;

		public static NoMyComparable of(int priority) {
			var a = new NoMyComparable();
			a.priority = priority;
			return a;
		}

		@Override
		public String toString() {
			return String.valueOf(priority);
		}
	}
}

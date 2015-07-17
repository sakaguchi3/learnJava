package com.github.sakaguchi3.jbatch002.javaapi;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.google.common.collect.ImmutableMap;

import io.vavr.Tuple;
import io.vavr.Tuple2;

public class StreamTest {

	@Test
	public void collect01Test() {

		List<Integer> list = List.of(1, 2, 3, 4, 5, 6);

		// 偶数、奇数グループに分ける。
		Map<Boolean, List<Integer>> m01 = list.stream() //
				.collect(Collectors.partitioningBy(n -> n % 2 == 0));

		// 3の余剰ごとのグループに分け,要素の平均値を出す。
		Map<Integer, Double> m02 = list.stream() //
				.collect(Collectors.groupingBy( //
						n -> n % 3, //
						Collectors.averagingDouble(n -> n + 0.0)));
		debug();
	}

	@Test
	public void collect02Test() {
		ImmutableMap<Integer, String> map = ImmutableMap.<Integer, String>builder() //
				.put(1, "aa") //
				.put(2, "bb") //
				.put(3, "cc") //
				.put(4, "dd") //
				.build();

		Map<Integer, String> map2 = map.entrySet().stream() //
				.collect(Collectors.toMap( //
						Map.Entry::getKey, //
						Map.Entry::getValue, //
						(v1, v2) -> v2));
	}

	@Test
	public void groupBy03Test() {

		System.out.println("groupBy03--------------------------------------------------");

		var list01 = List.of( //
				Tuple.of(3, "c"), //
				Tuple.of(1, "a8"), //
				Tuple.of(1, "a1"), //
				Tuple.of(1, "a9"), //
				Tuple.of(5, "z") //
		);

		var l02 = list01.stream() //
				.collect(Collectors.groupingBy(//
						Tuple2::_1 //
				));
		System.out.println("l02 := " + l02);

		var l03 = list01.stream() //
				.collect(Collectors.groupingBy(//
						Tuple2::_1, //
						Collectors.toList() //
				));
		System.out.println("l03 := " + l03);

		var l04 = list01.stream() //
				.collect(Collectors.groupingBy(//
						Tuple2::_1, //
						Collectors.counting() //
				));
		System.out.println("l04 := " + l04);

		var l50 = list01.stream() //
				.collect(Collectors.groupingBy( //
						Tuple2::_1, //
						Collectors.collectingAndThen(//
								Collectors.toList(), //
								tup -> tup.stream() //
										.sorted(Comparator //
												.comparingInt((Tuple2<Integer, String> t) -> t._1) //
												.thenComparing(Comparator.comparing(t -> t._2)) //
										) //
										.collect(Collectors.toList()) //
						)) //
				);

		System.out.println("l50 :=" + l50);

		debug();
	}

	@Test
	public void groupBy04Test() {

		System.out.println("groupBy04--------------------------------------------------");

		var list01 = List.of( //
				Tuple.of(3, "c"), //
				Tuple.of(1, "a8"), //
				Tuple.of(1, "a1"), //
				Tuple.of(1, "a9"), //
				Tuple.of(5, "z") //
		);

		// keyは正順にsort
		// (1, a9), (1, a8), (1, a1) valueは逆順にsort
		// (3, c),
		// (5, z)
		var l05 = list01.stream() //
				.sorted(Comparator //
//						.comparingInt(Tuple2::_1).thenComparing(Comperarotor.comparing() // 2段階 型推論ができない
//						.comparingInt((Tuple2<Integer, String> t) -> t._1).reversed() // ok
						.comparingInt((Tuple2<Integer, String> t) -> t._1) // ok
//						.thenComparing(Comparator.comparing(t -> t._2)) // ok
//						.thenComparing(Comparator.comparing(Tuple2::_2)) // ok
//						.thenComparing(Comparator.comparing(Tuple2::_2).reversed()) // 2段階 型推論ができない
						.thenComparing(Comparator//
								.comparing((Tuple2<Integer, String> t2) -> t2._2) //
								.reversed()) //
				) //
				.collect(Collectors.toList());//
		System.out.println("l05 := " + l05);

		// group by tuple.1
		// sort by tuple.1 -> tuple.2
		// 1=[(1, a9), (1, a8), (1, a1)]
		// 3=[(3, c)]
		// 5=[(5, z)]
		var l10 = list01.stream() //
				.collect(Collectors.groupingBy( //
						Tuple2::_1, //
						Collectors.collectingAndThen(//
								Collectors.toList(), //
								tup -> tup.stream() //
										.sorted(Comparator //
												.comparingInt((Tuple2<Integer, String> t) -> t._1) //
												.thenComparing(Comparator.comparing(t -> t._2)) //
												.reversed() //
										) //
										.collect(Collectors.toList()) //
						)) //
				);
		System.out.println("l10 := " + l10);

		// natural order ------------------------
		// sorted by key
		// 1=[(1, a9), (1, a8), (1, a1)]
		// 3=[(3, c)]
		// 5=[(5, z)]

		System.out.print("natural1: ");
		l10.entrySet().stream()//
				.sorted(Entry.comparingByKey(Comparator.naturalOrder()))//
				.forEach(v -> System.out.print(v + " "));
		System.out.println();

		// reverse order ------------------------
		// reverse sorted by key
		// 5=[(5, z)]
		// 3=[(3, c)]
		// 1=[(1, a9), (1, a8), (1, a1)]

		System.out.print("reverse1 ");
		l10.entrySet().stream()//
				.sorted(Collections.reverseOrder(Entry.comparingByKey()))//
				.forEach(v -> System.out.print(v + " "));
		System.out.println();

		System.out.print("reverse2 ");
		l10.entrySet().stream()//
				.sorted(Entry.comparingByKey(Comparator.reverseOrder()))//
				.forEach(v -> System.out.print(v + " "));
		System.out.println();

		debug();

	}

	private void debug() {

	}

}

package com.github.sakaguchi3.jbatch002.vavr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.Array;
import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import io.vavr.collection.Vector;

public class CollectionZipTest {

	@Test
	void zipWithIndex2Test() {
		var v1 = Vector.of("v", "w", "x", "y", "z");

		var sut = v1.zipWithIndex().filter(t -> t._2() % 2 == 0);

		var ans = Array.of(Tuple.of("v", 0), Tuple.of("x", 2), Tuple.of("z", 4));
		assertIterableEquals(ans, sut);
		debug();
	}

	@Test
	void javaLst2Test() {
		var jlst1 = Arrays.asList(0, 1, 2, 3);
		var jlst2 = Arrays.asList(0, 1, 2, 3, 4, 5, 6);

		var vlst1 = List.ofAll(jlst1);
		var vlst2 = List.ofAll(jlst2);

		// List((0, 0), (1, 1), (2, 2), (3, 3))
		var vlst3 = vlst1.zip(vlst2);

		assertEquals(4, vlst3.size());
		assertEquals(Tuple.of(2, 2), vlst3.get(2));
		debug();
	}

	@Test
	void javaLstTest() {
		var jlst1 = Arrays.asList(1, 2, 3);
		var jlst2 = Arrays.asList(1, 2, 3);

		var vstrm1 = Stream.ofAll(jlst1);
		var vstrm2 = Stream.ofAll(jlst2);

		// List((1, 1), (2, 2), (3, 3))
		var vlst = vstrm1.zip(vstrm2).toList();

		debug();
	}

	@Test
	void vavrLstTest() {
		var vLst1 = List.of(1, 2, 3);
		var vLst2 = List.of(1, 2, 3);

		// List((1, 1), (2, 2), (3, 3))
		var tpl1 = vLst1.zip(vLst2);
		debug();
	}

	@Test
	public void vavrMapTest() {
		var vMap1 = HashMap.of("k01", 01, "k02", 02, "k03", 3);
		// zipするときは小さい順に取り出される
		var vMap2 = HashMap.of("k99", 99, "k98", 98, "k97", 97, "k96", 96);

		// zipするときは小さい順に取り出される
		var vMap3 = HashMap.of("k96", 96, "k99", 99, "k98", 98, "k97", 97);

		// 小さい順でzipされる
		// List(((k01, 1), (k96, 96)), ((k02, 2), (k97, 97)), ((k03, 3), (k98, 98)))
		var lst = vMap1.zip(vMap3).toList();

		assertEquals(3, lst.size());
		assertEquals(Tuple.of(Tuple.of("k01", 01), Tuple.of("k96", 96)), lst.get(0));

		// Stream(((k01, 1), (k96, 96)), ((k02, 2), (k97, 97)), ((k03, 3), (k98, 98)))
		var vMapStrm = vMap1.zip(vMap2);

		// List(((k01, 1), (k97, 97)), ((k02, 2), (k98, 98)), ((k03, 3), (k99, 99)))
		var vLst = vMapStrm.toList();

		debug();
	}

	@Test
	public void vzip2Test() {

		var vavrList1 = List.of(1, 2, 3);
		var vavrList2 = List.of("a", "b");

		// List((1, a), (2, b)) | (int, str)
		var vavrLst3 = vavrList1.zip(vavrList2);

		debug();
	}

	@Test
	public void vavrListZippingTest() {

		var vavrList1 = List.of(1, 2, 3);
		var vavrList2 = List.of("a", "b");

		// (1, a) (2, b) : List(vavr)
		var vavrZipList = vavrList1.zip(vavrList2);
//		vavrZipList.forEach(System.out::print);

		// HashSet(1a, 2b)
		var mergeSet = vavrList1.zip(vavrList2) //
				.toStream() // vavr stream
				.map(v -> v._1() + v._2()) //
				.toSet();

		debug();
	}

	@Test
	public void zip2Test() {
		var jl1 = Arrays.asList(1, 2, 3);
		var jl2 = Arrays.asList(99, 98, 97, 96, 95);

		// [(1, 99), (2, 98), (3, 97)]
		var ans = Stream.ofAll(jl1) //
				.zip(Stream.ofAll(jl2)) //
				.toJavaList();
		debug();
	}

	@Test
	public void zipWithIndexTest() {

		var javaList = Arrays.asList("a", "2", "b");

		// List((a, 0), (2, 1), (b, 2))
		var sut1 = Stream.ofAll(javaList) //
				.zipWithIndex() //
				.toList();
		// List((0, a), (1, 2), (2, b))
		var sut2 = Stream.ofAll(javaList) //
				.zipWithIndex() //
				.map(Tuple2::swap) //
				.toList();

		// expected ---------

		var exp1 = Arrays.asList(Tuple.of("a", 0), Tuple.of("2", 1), Tuple.of("b", 2));
		var exp2 = Arrays.asList(Tuple.of(0, "a"), Tuple.of(1, "2"), Tuple.of(2, "b"));

		assertIterableEquals(exp1, sut1);
		assertIterableEquals(exp2, sut2);

		debug();
	}

	void debug() {
	}

}

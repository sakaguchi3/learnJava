package com.github.sakaguchi3.jbatch002.javaapi;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.google.common.base.Objects;

// Log4j2 & JUnit5 ----------
import org.apache.logging.log4j.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static java.time.Duration.*;

public class CollectionTest {

	@Test
	public void cTest() {
		debug();
	}

	@Test
	public void mapTest() {

		var m = Map.of(//
				"k1", "v1", //
				"k2", "v2", //
				"k3", "v3", //
				"k100", "v100");

		System.out.println(m);

		debug();
	}

	@Test
	public void flatmapTest() {
		var l = List.of(//
				List.of(), //
				List.of(1, 2, 9), //
				List.of(9, 2, 9), //
				List.of() //
		);

		var l2 = l.stream()//
				.flatMap(Collection::stream) //
				.collect(Collectors.toList());

		System.out.println(l2);
	}

	@Test
	public void collectionContainTest() {
		var l = List.of( //
				MyClass.of(3, 3), //
				MyClass.of(4, 4), //
				MyClass.of(5, 5), //
				MyClass.of(6, 6), //
				MyClass.of(7, 7) //
		);

		assertTrue(l.contains(MyClass.of(3, -1)));
		assertFalse(l.contains(MyClass.of(-1, 3)));

		debug();
	}

	/** uniqidが同じだったら同じデータだというルールにする */
	private static class MyClass {
		final int id;
		int tmpNo;

		public static MyClass of(int id, int tmpNo) {
			var o = new MyClass(id);
			o.tmpNo = tmpNo;
			return o;
		}

		private MyClass(int id) {
			this.id = id;
		}

		@Override
		public boolean equals(Object o) {
			return Optional.ofNullable(o) //
					.filter(v -> v instanceof MyClass) //
					.map(v -> (MyClass) v) //
					.filter(v -> Objects.equal(this.id, v.id)) //
					.isPresent();
		}
	}

	void debug() {

	}

}

package com.github.sakaguchi3.jbatch002.javaapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Test;

public class OptionalTest {

	/**
	 * orElse, orElseGetの違いは、 遅延評価を利用しているしていない。
	 */
	@Test
	void orElseGet() {

		var sw = StopWatch.createStarted();

		// 評価されない
		Optional.of(33).orElseGet(() -> heavy());
		var t1 = sw.getTime(TimeUnit.MILLISECONDS);

		sw.reset();
		sw.start();

		// 評価される
		Optional.of(33).orElse(heavy());
		var t2 = sw.getTime(TimeUnit.MILLISECONDS);

		assertTrue(t1 < 50);
		assertTrue(t2 >= 300);
	}

	int heavy() {
		try {
			TimeUnit.MILLISECONDS.sleep(300);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 3;
	}

	@Test
	void orTest() {
		var num1Op = Optional.of(1);
		var sut1 = num1Op.or(() -> Optional.of(2));
		assertEquals(1, sut1.get());

		var emptyOp = Optional.empty();
		var sut2 = emptyOp.or(() -> Optional.of(2));
		assertEquals(2, sut2.get());
	}

	@Test
	void optionalToStreamTest() {
		Stream<Integer> stream = Optional.of(3).stream();
		var intOp = stream.findAny();
		assertTrue(intOp.isPresent());
		assertEquals(3, intOp.get());
	}

	@Test
	void flatmapNullTest() {
		assertThrows(NullPointerException.class, () -> {
			Optional.of(1).flatMap(__ -> null);
		});

		debug();
	}

	@Test
	void mapNullTest() {
		var op = Optional.of(1).map(__ -> null);
		assertTrue(op.isEmpty());

		debug();
	}

	@Test
	void optionalStreamTest() {

		var opLst = List.of( //
				Optional.ofNullable("op1"), //
				Optional.empty(), //
				Optional.ofNullable("op9"));
		var sut = opLst.stream() //
				.flatMap(Optional::stream) //
				.collect(Collectors.toList());
		var ans = List.of("op1", "op9");
		assertIterableEquals(ans, sut);

		debug();
	}

	private void debug() {

	}

}

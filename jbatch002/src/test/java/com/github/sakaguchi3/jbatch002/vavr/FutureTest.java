package com.github.sakaguchi3.jbatch002.vavr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.github.sakaguchi3.util.UtilRandom;
import com.google.common.base.Stopwatch;

import io.vavr.concurrent.Future;
import io.vavr.control.Try;


public class FutureTest {

	static final Logger LOGGER = LogManager.getLogger();

	BiConsumer<String, Stopwatch> printTime = (s, sw) -> {
		System.out.printf("%13s time: %s\n", s, sw);
		sw.reset();
		sw.start();
	};

	@Test
	public void test01() {

		Future<Float> intFuture = Future.of(() -> 1.0f * 3 / 2) //
				.await();

		assertEquals(intFuture.isCompleted(), (true));
		assertEquals(intFuture.get(), (1.5f));

		debug();
	}

	@Test
	public void test02() {

		Future<Integer> ret01 = Future.of(() -> longTimeComputing(300));

		// 評価されていない
		debug();
		assertEquals(ret01.isCompleted(), (false));

		int reti01 = ret01.get();

		// 評価される
		debug();
	}

	@Test
	public void a20test() {
		Stopwatch sw = Stopwatch.createStarted();

		var time = 50;

		List<Future<Integer>> futures = List.of( //
				Future.of(() -> longTimeComputing(time)), //
				Future.of(() -> longTimeComputing(time)), //
				Future.of(() -> longTimeComputing(time)), //
				Future.of(() -> longTimeComputing(time)));

		printTime.accept("fast,", sw);

		List<Integer> list = futures.parallelStream() //
				.flatMap(v -> Try.of(() -> v.get()).toJavaStream()) //
				.collect(Collectors.toList());

		printTime.accept("slow,", sw);

		for (int i = 0; i < 4; i++) {
			longTimeComputing(time);
		}

		printTime.accept("VerySlow,", sw);

	}

	// --------------------------------------------------------------------------------
	// method
	// --------------------------------------------------------------------------------

	public int longTimeComputing(int time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return UtilRandom.randInt();
	}

	void debug() {

	}

}

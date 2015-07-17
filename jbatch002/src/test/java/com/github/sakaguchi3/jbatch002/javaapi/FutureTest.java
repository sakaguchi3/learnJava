package com.github.sakaguchi3.jbatch002.javaapi;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

import com.github.sakaguchi3.util.UtilRandom;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;

import io.vavr.control.Try;

public class FutureTest {

	private Consumer<Stopwatch> reset = sw -> {
		sw.reset();
		sw.start();
	};

	private BiConsumer<Stopwatch, String> measure = (sw, s) -> {
		System.out.println(s + ": " + sw);
	};

	private BiConsumer<Stopwatch, String> measure2 = (sw, s) -> {
		measure.accept(sw, s);
		reset.accept(sw);
	};

	@Test
	public void future01Test() {

		Stopwatch sw = Stopwatch.createStarted();

		ExecutorService es = Executors.newFixedThreadPool(3);

		measure.accept(sw, "p01 fast");
		reset.accept(sw);

		List<Future<Integer>> futures = List.of( //
				es.submit(createCallable()), //
				es.submit(createCallable()), //
				es.submit(createCallable()));

		measure2.accept(sw, "p20 fast");

		ImmutableList<Integer> anss = futures.stream()//
				.flatMap(v -> Try.of(() -> v.get()).toJavaStream()) //
				.collect(ImmutableList.toImmutableList());

		measure2.accept(sw, "p30 long");

		debug();
	}

	/**
	 * thread
	 */
	private Callable<Integer> createCallable() {
		Callable<Integer> c = () -> longComputing();
		return c;
	}

	private int longComputing() {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return UtilRandom.randInt();
	}

	private void debug() {

	}

}

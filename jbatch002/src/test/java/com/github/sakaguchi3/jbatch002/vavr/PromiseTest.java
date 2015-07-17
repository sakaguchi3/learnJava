package com.github.sakaguchi3.jbatch002.vavr;

import java.util.function.BiConsumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.github.sakaguchi3.util.UtilRandom;
import com.google.common.base.Stopwatch;

import io.vavr.concurrent.Future;
import io.vavr.concurrent.Promise;
import io.vavr.control.Try;

public class PromiseTest {

	static final Logger LOGGER = LogManager.getLogger();

	BiConsumer<String, Stopwatch> printTime = (s, sw) -> {
		System.out.printf("%13s time: %s\n", s, sw);
		sw.reset();
		sw.start();
	};

	@Test
	public void test01() {

		debug();
	}

	@Test
	public void test02() {

		Promise<String> promise = Promise.make();
		Future<String> future = promise.future();

		System.out.println("aaaa");

		// これはbbbb以降に呼ばれる。スレッドなので、dddの前に呼ばれるかあとに呼ばれるかは非決定的。
		future.forEach(v -> System.out.printf("やっとよばれたよ.[%s]\n", v));

		System.out.println("bbb");

		var b1 = promise.trySuccess("cccc");

		System.out.println("ddd");

		// 評価される
		debug();
	}

	@Test
	public void a20test() {
		Promise<Integer> promise = Promise.make();
		promise.complete(Try.of(() -> longTimeComputing(30)));

		Future<Integer> future = promise.future();
		future.forEach(System.out::println);
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

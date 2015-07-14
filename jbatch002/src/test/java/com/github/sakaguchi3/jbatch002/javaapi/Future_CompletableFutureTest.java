package com.github.sakaguchi3.jbatch002.javaapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import io.vavr.Function2;

public class Future_CompletableFutureTest {

	/**
	 * 使うスレッド数が少ない
	 */
	@Test
	void poolTest() {
		List<CompletableFuture<String>> list = new ArrayList<>(30);
		for (int i = 0; i < 30; i++) {
			var future = CompletableFuture.supplyAsync(() -> {
				try {
					System.out.println(Thread.currentThread().getName());
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return Thread.currentThread().getName();
			});
			list.add(future);
		}

		CompletableFuture<Void> cf = CompletableFuture.allOf(list.toArray(new CompletableFuture[list.size()]));

		cf.whenComplete((ret, ex) -> {
			System.out.println(ret);
			System.out.println(ex);
		});
		list.stream().forEach(v -> v.cancel(true));

		System.out.println("end");
	}

	/**
	 * 使うスレッド数が多い
	 */
	@Test
	void fixedTest() {
		// ExecutorService pool = Executors.newFixedThreadPool(40);
		ExecutorService pool = Executors.newCachedThreadPool();

		List<CompletableFuture<String>> list = new ArrayList<>(30);
		for (int i = 0; i < 30; i++) {
			var future = CompletableFuture.supplyAsync(() -> {
				try {
					System.out.println(Thread.currentThread().getName());
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return "__";
			}, pool);
			list.add(future);
		}

		CompletableFuture<Void> cf = CompletableFuture.allOf(list.toArray(new CompletableFuture[list.size()]));

		cf.whenComplete((ret, ex) -> {
			System.out.println(ret);
			System.out.println(ex);
		});

		list.stream().forEach(v -> v.cancel(true));
		System.out.println("end");
	}

	@Test
	void completableFutureTest() {
		Function2<Integer, Object, Integer> heavyTask = (i, ___) -> {
			try {
				TimeUnit.MILLISECONDS.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return i;
		};

		// long computing
		var future1 = CompletableFuture.supplyAsync(() -> 100) //
				.thenApplyAsync(heavyTask.apply(101));

		// fast computing
		var future2 = CompletableFuture.supplyAsync(() -> 100) //
				.thenApplyAsync(___ -> 102);

		// 先に終了した方の値が入る。この場合だとfuture2(102)
		var future3 = future1.applyToEitherAsync(future2, v -> v);
		try {
			assertEquals(102, future3.get());
		} catch (Exception e) {
			fail(e.getMessage());
		}

		debug();
	}

	/**
	 * use ExecutiveService
	 */
	@Test
	void completableFuture2Test() {
		Function2<Integer, Object, Integer> heavyTask = (i, ___) -> {
			try {
				TimeUnit.MILLISECONDS.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return i;
		};

		var es = Executors.newCachedThreadPool();

		// long computing
		var future1 = CompletableFuture.supplyAsync(() -> 100, es) //
				.thenApplyAsync(heavyTask.apply(101));

		// fast computing
		var future2 = CompletableFuture.supplyAsync(() -> 100, es) //
				.thenApplyAsync(___ -> 102);

		// 先に終了した方の値が入る。この場合だとfuture2(102)
		var future3 = future1.applyToEitherAsync(future2, v -> v, es);
		try {
			assertEquals(102, future3.get());
		} catch (Exception e) {
			fail(e.getMessage());
		}

		debug();
	}

	private void debug() {
	}
}

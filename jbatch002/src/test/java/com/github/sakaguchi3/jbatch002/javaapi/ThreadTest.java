package com.github.sakaguchi3.jbatch002.javaapi;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class ThreadTest {

	@Test
	void executiveServiceTest() throws InterruptedException {
		var pool = Executors.newCachedThreadPool();

		var run = new MyRun();
		// return value
		Future<?> future = pool.submit(run);
		// return nothing(void)
		pool.execute(run);

		for (int i = 0; i < 10; i++) {
			run.add(i);
		}

		run.stop();

		TimeUnit.MICROSECONDS.sleep(100);
		System.out.println("1");
		pool.shutdown();

		pool.awaitTermination(100, TimeUnit.MICROSECONDS);

		TimeUnit.MICROSECONDS.sleep(100);
		System.out.println("2");
		pool.shutdownNow(); // throw InterruptedException

		TimeUnit.MICROSECONDS.sleep(100);
		System.out.println("3");
	}

	private void debug() {
	}

	protected static class MyRun implements Runnable {
		BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
		boolean isrun = true;

		public void add(Integer d) {
			queue.add(d);
			System.out.println("produce:" + d);
		}

		public void stop() {
			isrun = false;
		}

		protected void work(Integer d) {
			System.out.println("consume:" + d);
		}

		@Override
		public void run() {
			while (isrun) {
				try {
					var task = queue.take();
					work(task);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}

package com.github.sakaguchi3.jbatch002.concurrent.thread2;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

public class MonitorThreadTest {

//	@Test void jTest() { }

	@Test
	void cacheTest() {
		Function<String, Runnable> f = (String s) -> () -> System.out.println(s);

		var r1 = f.apply("t1");
		var r2 = f.apply("t2----");
		var r3 = f.apply("t3--------");

		var mt = new ThreadPool();
		mt.add(r1, 1);
		mt.add(r2, 4);
		mt.add(r3, 8);

		mt.start();
		try {
			TimeUnit.SECONDS.sleep(10);
			mt.shutdown(1);
			System.out.println("shutdown");
		} catch (Exception e) {
			e.printStackTrace();
		}
		d();

	}

	private void d() {
	}

}

package com.github.sakaguchi3.jbatch002.concurrent.thread2;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class CacheTest {

//	@Test void jTest() { }

	@Test
	void monitorTest() {
		var r = ThreadLocalRandom.current();
		var cache = new CacheBuilder<String, Integer>() //
				.interval(1) //
				.update(s -> r.nextInt(100)) //
				.build();
		var a1 = cache.get("a");
		var a2 = cache.get("a");

		wait(1);

		var b1 = cache.get("a");
		cache.shutdown();
		

		d();
	}

	private void wait(int time) {
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void d() {
	}

}

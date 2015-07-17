package com.github.sakaguchi3.jbatch002.guava;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.google.common.base.Stopwatch;

public class StopWatchTest {

	@Test
	public void u02Test() {

		try {

			Stopwatch sw = Stopwatch.createStarted();

			System.out.println(sw);
			sw.reset();
			sw.start();

			Thread.sleep(100);

			System.out.println(sw);
			sw.reset();
			sw.start();

			Thread.sleep(200);

			System.out.println(sw);
			sw.reset();
			sw.start();

			Thread.sleep(100);

			System.out.println(sw);
			System.out.println(sw);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		debug();
	}

	private void debug() {

	}

}

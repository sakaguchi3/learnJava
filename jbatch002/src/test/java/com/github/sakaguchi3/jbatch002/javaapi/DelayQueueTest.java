package com.github.sakaguchi3.jbatch002.javaapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class DelayQueueTest {

	@Test
	public void overSizeTest() throws Exception {

		var queue = new LinkedBlockingQueue<String>(3);
		queue.add("b");
		queue.add("c");
		queue.add("d");

		assertThrows(IllegalStateException.class, () -> {
			// exceptionが発生する
			queue.add("e");
		});
		debug();

	}

	@Test
	public void overSize2Test() throws Exception {

		var queue = new LinkedBlockingQueue<String>(3);
		queue.add("b");
		queue.add("c");
		queue.add("d");

		// 容量制限をoverしている場合はスルーする
		queue.offer("e");
		// 容量制限がある場合、x秒後までwaitする
		queue.offer("a", 500, TimeUnit.MILLISECONDS);

		var size = queue.size();
		assertEquals(size, (3));

		debug();

	}

	static void debug() {

	}
}

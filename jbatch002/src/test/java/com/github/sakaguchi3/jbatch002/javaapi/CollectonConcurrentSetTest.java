package com.github.sakaguchi3.jbatch002.javaapi;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Test;

public class CollectonConcurrentSetTest {

	@Test
	public void aTest() {
		debug();
	}

	@Test
	public void concurrentSetTest() {
		Set<Long> set = ConcurrentHashMap.<Long>newKeySet(10_000);
		var list = new ArrayList<Long>(10_000);
		var pq = new PriorityBlockingQueue<Long>(10_000);

		StopWatch sw = StopWatch.createStarted();
		for (long i = 0; i < 10_000; i++) {
			var b = set.contains(i);
			list.add(i);
			set.add(i);
			pq.offer(i);
		}

		System.out.println(sw);
		var isSame = pq.size() == set.size();

		debug();
	}

	void debug() {
	}

}

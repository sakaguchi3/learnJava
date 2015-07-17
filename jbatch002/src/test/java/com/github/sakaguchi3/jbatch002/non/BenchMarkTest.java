package com.github.sakaguchi3.jbatch002.non;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntPredicate;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.junit.jupiter.api.Test;

public class BenchMarkTest {

	final Runnable heavyTask = () -> {
		try {
			TimeUnit.MILLISECONDS.sleep(3);
		} catch (Exception e) {
		}
	};
	DescriptiveStatistics stat = new DescriptiveStatistics();

	@Test
	public void benchMark2Test() {
		var atom = new AtomicInteger();
		/** Logging 1 out of cnt times | 5% (&hArr; 100/5 = 1 out of 20) */
		IntPredicate isTrueByCnt = cnt -> {
			var _cnt = atom.getAndIncrement();
			return ((_cnt % cnt) == 0);
		};

		stat.clear();
		stat.setWindowSize(1000);
		var sw = StopWatch.createStarted();
		for (long i = 0; i < 100; i++) {
			// hevy task
			heavyTask.run();

			long time = sw.getTime(TimeUnit.MICROSECONDS);
			stat.addValue(time);

			// 5%の確率でlog出力(<=>100/5=20回に1回)
			if (isTrueByCnt.test(100 / 5)) {
				System.out.println("Log - " + ", time:" + time + ", stopwatch:" + sw + ", stat:" + stat);
			}
			sw.reset();
			sw.start();
		}
		var p75 = stat.getPercentile(75);
		var p99 = stat.getPercentile(99);
		System.out.println(stat + "75percentile:" + p75 + "\n99percentile:" + p99);
		debug();
	}

	@Test
	public void benchMark1Test() {
		var rand = ThreadLocalRandom.current();
		/** Logging with probability p | 5% (&hArr; 100/5 = 1 out of 20) */
		IntPredicate isTruePercent = p -> (rand.nextInt(100) <= p);

		stat.clear();
		stat.setWindowSize(100);
		var sw = StopWatch.createStarted();
		for (long i = 0; i < 100; i++) {
			// hevy task
			heavyTask.run();
			long time = sw.getTime(TimeUnit.MICROSECONDS);
			stat.addValue(time);
			// 5%の確率でlog出力(<=>100/5=20回に1回)
			if (isTruePercent.test(100 / 20)) {
				System.out.println("Log - " + ", time:" + time + ", stopwatch:" + sw + ", stat:" + stat);
			}
			sw.reset();
			sw.start();
		}
		var p75 = stat.getPercentile(75);
		var p99 = stat.getPercentile(99);
		System.out.println(stat + "75percentile:" + p75 + "\n99percentile:" + p99);
		debug();
	}

	void debug() {
	}

}

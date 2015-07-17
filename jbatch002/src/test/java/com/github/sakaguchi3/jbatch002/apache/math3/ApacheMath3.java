package com.github.sakaguchi3.jbatch002.apache.math3;

import java.util.stream.IntStream;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

/**
 * <pre>
 * 	<dependency>
	    <groupId>org.apache.commons</groupId>
	    <artifactId>commons-math3</artifactId>
	    <version>3.6.1</version>
	</dependency>
 * </pre>
 */
public class ApacheMath3 {

	private final Logger log = LogManager.getLogger();

	@Test
	public void aaaTest() {
		var stats = new DescriptiveStatistics();
		IntStream.range(0, 10) //
				.mapToDouble(__ -> Math.random()) //
				.forEach(stats::addValue);
		var p75 = stats.getPercentile(75);
		var p90 = stats.getPercentile(90);

		log.info(stats + ", 75%:" + p75 + ", 90%:" + p90);
		debug();
	}

	void debug() {

	}

}

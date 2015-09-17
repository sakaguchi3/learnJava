package com.github.sakaguchi3.jbatch002.s;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * メモ化
 */
public class Memoization {

	private static final Logger log = LogManager.getLogger();

	private final Map<String, Integer> memory = new HashMap<>();

	public Integer heavyTask(String key) {
		return memory.computeIfAbsent(key, _k -> heavy(_k));
	}

	private Integer heavy(String key) {
		return key.length();
	}

	void debug() {
	}

}

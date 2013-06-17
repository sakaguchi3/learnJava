package com.github.sakaguchi3.jbatch002.javaapi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

public class MatcherTest {
	@Test
	void a() {
		String input = "jjca1jjjjca2eee cat dog cap";

		Pattern pattern = Pattern.compile("ca.");
		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			String matched = matcher.group();
			System.out.printf("[%s] is matched. Pattern:[%s] input:[%s]\n", matched, pattern, input);
			d();
		}

		d();
	}

	void d() {
	}

}

package com.github.sakaguchi3.jbatch002.javaapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class Java11Test {

	@Test
	void lambdaVarTest() {
		Function<Integer, Integer> oldStyle = (Integer x) -> x + 10;
		Function<Integer, Integer> newStyle = (var x) -> x + 10;

		assertEquals(11, oldStyle.apply(1));
		assertEquals(11, newStyle.apply(1));
	}

	@Test
	void tryVarTest() {
		try {
			var url = new URL("http://localhost:8080/server002/s003");
			var con = url.openConnection();
			con.setDoOutput(true);
			con.setDoInput(true);

			try (//
					var in = con.getInputStream();
					var is = new InputStreamReader(in);
					var br = new BufferedReader(is);
					var line = br.lines();) {
				var str = line.collect(Collectors.joining());
			}

			try (var bo = new BufferedOutputStream(con.getOutputStream())) {
				bo.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void forVarTest() {
		var strLst = List.of("a", "b", "c", "d");
		for (var str : strLst) {
		}
	}

	void debug() {
	}

}

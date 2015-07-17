package com.github.sakaguchi3.jbatch002.javaapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class StringTest {

	@Test
	public void future01Test() {
		List<String> strs = List.of("ab", "de");

		String s1 = String.join("", strs);
		assertEquals(s1, ("abde"));

		String s2 = strs.stream().collect(Collectors.joining());
		assertEquals(s2, ("abde"));

		String s3 = strs.stream() //
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append) //
				.toString();
		assertEquals(s3, ("abde"));

		String s4 = strs.stream() //
				.collect(StringBuffer::new, StringBuffer::append, StringBuffer::append) //
				.toString();
		assertEquals(s4, ("abde"));

		String s5 = strs.stream().reduce("", String::concat);
		assertEquals(s5, ("abde"));

		debug();
	}

	private void debug() {

	}

}

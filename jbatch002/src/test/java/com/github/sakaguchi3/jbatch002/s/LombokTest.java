package com.github.sakaguchi3.jbatch002.s;

import org.junit.jupiter.api.Test;

import testcase.other.PersonLombok;

public class LombokTest {

	@Test
	public void t01Test() {

		var p = new PersonLombok();
//		p.setAge(920);
//		p.setName("jhon");

		System.out.println(p);

		debug();
	}

	private void debug() {

	}

}

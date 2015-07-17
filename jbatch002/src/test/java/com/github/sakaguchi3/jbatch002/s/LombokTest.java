package com.github.sakaguchi3.jbatch002.s;

import org.junit.jupiter.api.Test;

import com.github.sakaguchi3.jbatch002.s.Person;

public class LombokTest {

	@Test
	public void t01Test() {

		var p = new Person();
//		p.setAge(920);
//		p.setName("jhon");

		System.out.println(p);

		debug();
	}

	private void debug() {

	}

//	import static org.hamcrest.CoreMatchers.*;
//	import static org.junit.Assert.*;
//	import static org.junit.Assert.*;	
//	@Test
//	public void importPackage() {
//		if (true) {
//			assertEquals(true, (true));
//		} else {
//			fail("empty");
//		}
//	}

}

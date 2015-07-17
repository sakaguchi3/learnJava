package com.github.sakaguchi3.jbatch002.guava;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.google.common.base.MoreObjects;

public class ToStringTest {

	@Test
	public void aTest() {
		debug();
	}

	@Test
	public void bTest() {
		var myObj = MyComparable.of(99);

		// MyComparable{priority=99, map={k1=v2, k2=v3}} 
		var str = myObj.toString();

		System.out.println(myObj);

		assertEquals("MyComparable{priority=99, map={k1=v2, k2=v3}}", str);

		debug();
	}

	void debug() {
	}

	private static class MyComparable {
		public int priority = 0;
		Map<String, String> map = Map.of("k1", "v2", "k2", "v3");

		public static MyComparable of(int priority) {
			var a = new MyComparable();
			a.priority = priority;

			return a;
		}

		@Override
		public String toString() {
			var s = MoreObjects.toStringHelper(this) //
					.add("priority", priority) //
					.add("map", map) //
					.toString();
			return s;
		}
	}
}

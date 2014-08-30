package com.github.sakaguchi3.jbatch002.javaapi;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Test;

import com.google.common.collect.Lists;

import lombok.Value;

public class CloneTest {

	@Test
	void shallowCopy1Test() {
		try {
			d();
			ArrayList<String> m = Lists.newArrayList("a");
			var j1 = new SharrowCopyWithNewMethod("jj", m);
			var j2 = j1.clone();

			assertFalse(j1 == j2);
			assertTrue(j1.getL() == j2.getL());
			d();
		} catch (Exception e) {
			fail(e.getMessage(), e);
		}
	}

//	@Test
	void shallowCopy2Test() {
		try {
			ArrayList<String> m = Lists.newArrayList("a");
			var j1 = new SharrowCopyWithSuperClone("jj", m);
			var j2 = j1.clone();

			assertFalse(j1 == j2);
			assertTrue(j1.getL() == j2.getL());
			d();
		} catch (Exception e) {
			fail(e.getMessage(), e);
		}
	}

//	@Test
	void deepCopy1Test() {
		try {
			// unmodifable list
			ArrayList<String> m = Lists.newArrayList("a");

			var j1 = new DeepCopy(m);
			var j2 = j1.clone();

			assertFalse(j1 == j2);
			assertFalse(j1.getL() == j2.getL());
			d();
		} catch (Exception e) {
			fail(e.getMessage(), e);
		}
	}

//	@Test
	void deepCopyWithLibraryTest() {
		try {
			// unmodifable list
			ArrayList<String> m = Lists.newArrayList("a");

			var j1 = new Hoge(m);
			var j2 = SerializationUtils.clone(j1);

			assertFalse(j1 == j2);
			assertFalse(j1.getL() == j2.getL());
			d();
		} catch (Exception e) {
			fail(e.getMessage(), e);
		} 
	}

	// class ------------------------------

	@Value
	private static class Hoge implements Serializable{
		private static final long serialVersionUID = 2037797159751196804L;
		List<String> l; 
	}



	@Value
	private static class DeepCopy implements Cloneable {
		List<String> l;

		/** deep copy する場合は、コピーを実装する必要がある */
		@Override
		protected DeepCopy clone() throws CloneNotSupportedException {
			var l2 = List.copyOf(l);
			var t = new DeepCopy(l2);
			return t;
		}
	}

	@Value
	private static class SharrowCopyWithNewMethod implements Cloneable {
		String s1;
		List<String> l;

		@Override
		protected SharrowCopyWithNewMethod clone() throws CloneNotSupportedException {
			var t = new SharrowCopyWithNewMethod(s1, l);
			return t;
		}
	}

	@Value
	private static class SharrowCopyWithSuperClone implements Cloneable {
		String s1;
		List<String> l;

		@Override
		protected SharrowCopyWithSuperClone clone() throws CloneNotSupportedException {
			return (SharrowCopyWithSuperClone) super.clone();
		}
	}

	private void d() {
	}

}

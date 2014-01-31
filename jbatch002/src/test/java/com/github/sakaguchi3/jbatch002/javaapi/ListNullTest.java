/**
 * Copyright 2021. sakaguchi3, https://github.com/sakaguchi3/learnJava
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.sakaguchi3.jbatch002.javaapi;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class ListNullTest {

//	@Test
	void p() {
		d();
	}

	@Test
	void a() {
		var lstOrg = Arrays.asList("a", null, "b", null, null, "c");

		var lstNew = new ArrayList<>(lstOrg);
		lstNew.removeIf(Objects::isNull);

		assertTrue(lstOrg.contains(null));
		assertFalse(lstNew.contains(null));

		d();
	}

//	@Test
	void guavaRemoveUnmodifiableTest() {
		var lstOrg = Arrays.asList("a", null, "b", null, null, "c");
		var lstNew = Lists.newArrayList(Iterables.filter(lstOrg, Predicates.notNull()));

		assertTrue(lstOrg.contains(null));
		assertFalse(lstNew.contains(null));

		d();
	}

//	@Test
	void exceptionUnmodifiableTest() {
		assertThrows(NullPointerException.class, () -> {
			// cannot initialize
			var lst = List.of("a", null, "b", null, null, "c");
		});

		assertThrows(UnsupportedOperationException.class, () -> {
			// 操作自体NG
			var lst = List.of("a", "b", "c");
			lst.remove("jj");
		});

		assertThrows(UnsupportedOperationException.class, () -> {
			var lst = Arrays.asList("a", null, "b", null, null, "c");
			// cannot remove
			lst.remove("a");
		});

		assertDoesNotThrow(() -> {
			var lst = Arrays.asList("a", null, "b", null, null, "c");
			// 操作自体はできる
			lst.remove("");
		});

		d();
	}

//	@Test
	void remove3Test() {
		var l = Lists.newArrayList("a", null, "b", null, null, "c");

		l.removeIf(Objects::isNull);
		assertFalse(l.contains(null));
		assertEquals(3, l.size());

		d();
	}

//	@Test
	void removeAll2Test() {
		var l = Lists.newArrayList("a", null, "b", null, null, "c");
		while (l.remove(null)) {
		}
		assertFalse(l.contains(null));
		d();
	}

//	@Test
	void removeAllTest() {
		var l = Lists.newArrayList("a", null, "b", null, null, "c");
		l.removeAll(Collections.singleton(null));
		assertFalse(l.contains(null));
		d();
	}

//	@Test
	void removeOneTest() {
		var l = Lists.newArrayList("a", null, "b", null, null, "c");
		// 1つだけ削除される
		l.remove(null);

		assertTrue(l.contains(null));
		assertEquals(5, l.size());

		d();
	}

//	@Test
	void foreatchTest() {

		var l = Lists.newArrayList("a", null, "b", "c");
		l.forEach(System.out::println);
		d();
	}

//	@Test
	void ListCopyOfTest() {
		var lstArry = Lists.newArrayList("a", null, "b", "c");
		assertThrows(NullPointerException.class, () -> {
			var l2 = List.copyOf(lstArry);
		});
	}

	private void d() {
	}

}

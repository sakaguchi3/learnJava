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
package com.github.sakaguchi3.jbatch002.apache.lang4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.collections4.ListUtils;
import org.junit.jupiter.api.Test;

public class ListUtilTest {

//	@Test public void bTest() { d(); }

	@Test
	public void intersectTest() {
		var a = List.of(1, 2, 3);
		var b = List.of(1, 4);

		var intersect = ListUtils.intersection(a, b);
		intersect.sort(Comparator.naturalOrder());

		assertIterableEquals(List.of(1), intersect);
	}

//	@Test
	public void sumTest() {
		var a = List.of(1, 2, 3);
		var b = List.of(1, 4);

		var sum = ListUtils.sum(a, b);
		sum.sort(Comparator.naturalOrder());

		assertIterableEquals(List.of(1, 2, 3, 4), sum);
	}

//	@Test
	public void unionTest() {
		var a = List.of(1, 2, 3);
		var b = List.of(1, 4);

		var union = ListUtils.union(a, b);
		union.sort(Comparator.naturalOrder());

		assertIterableEquals(List.of(1, 1, 2, 3, 4), union);
	}

//	@Test
	public void subtractWithJavaStandardLibTest() {
		var all = List.of(1, 2, 3, 4, 5);
		var allMutableLst = new ArrayList<>(all);

		var tar = List.of(1, 2, 99);

		allMutableLst.removeAll(tar);

		assertIterableEquals(List.of(3, 4, 5), allMutableLst);

	}

//	@Test
	public void subtract2Test() {
		var all = List.of(1, 2, 3, 4, 5);
		var tar = List.of(1, 2, 99);
		var sub = ListUtils.subtract(all, tar);

		assertIterableEquals(List.of(3, 4, 5), sub);

	}

//	@Test
	public void subtract1Test() {
		var all = List.of(1, 2, 3, 4, 5);
		var tar = List.of(1, 2);
		var sub = ListUtils.subtract(all, tar);

		assertIterableEquals(List.of(3, 4, 5), sub);
	}

//	@Test
	public void selectTest() {
		var numLst = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
		var envLst = ListUtils.select(numLst, v -> v % 2 == 0);

		assertIterableEquals(List.of(2, 4, 6, 8), envLst);
	}

//	@Test
	public void lazyListTest() {
		var r = ThreadLocalRandom.current();
		List<Integer> lazy = ListUtils.lazyList(new ArrayList<Integer>(), () -> r.nextInt(300));

		assertEquals(0, lazy.size());

		var i0 = lazy.get(0);
		assertEquals(1, lazy.size());

		var i0_2 = lazy.get(0);
		assertEquals(1, lazy.size());
		assertEquals(i0, i0_2);

		var i1 = lazy.get(1);
		assertEquals(2, lazy.size());

	}

	private void d() {
	}

}

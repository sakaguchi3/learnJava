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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.collections4.ListUtils;
import org.junit.jupiter.api.Test;

public class ListUtilTest {

	@Test
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

		d();
	}

	private void d() {
	}

}

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
package com.github.sakaguchi3.jbatch002.eclipsecollection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.junit.jupiter.api.Test;

public class CollectionTest {

	@Test
	public void immutableTest() {
		var l = Factories.immutable.of(1, 3, 3, 4);

		l.get(3);
		assertEquals(4, l.get(3));
		
		assertEquals(4, l.size());

	}

//	@Test
	public void mutableTest() {
		var l = Factories.mutable.of(1, 3, 3, 4);

		l.get(3);
		assertEquals(4, l.get(3));

		l.add(63);
		assertEquals(5, l.size());

	}

//	@Test
	public void u02Test() {
		ImmutableList<Integer> l = Lists.immutable.of(1, 2, 3, 4, 5);

		assertTrue(l.contains(1));
		assertFalse(l.contains(99));

		d();
	}

	private void d() {
	}

}

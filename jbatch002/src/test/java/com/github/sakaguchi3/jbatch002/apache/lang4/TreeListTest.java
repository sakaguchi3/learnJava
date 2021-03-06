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

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.list.TreeList;
import org.junit.jupiter.api.Test;

public class TreeListTest {

	@Test
	public void unmodifiableTest() {
		TreeList<Integer> t = new TreeList<>(List.of(8, 12, 20));
		var ut = Collections.unmodifiableCollection(t);
		assertThrows(UnsupportedOperationException.class, () -> {
			ut.add(3);
		});

		d();
	}

//	@Test
	public void treeTest() {
		TreeList<Integer> t = new TreeList<>(List.of(8, 12, 20));

		var ut = Collections.unmodifiableCollection(t);

		// ut is changed
		t.add(3, 11);

		assertIterableEquals(List.of(8, 12, 20, 11), ut);
		d();
	}

	private void d() {
	}

}

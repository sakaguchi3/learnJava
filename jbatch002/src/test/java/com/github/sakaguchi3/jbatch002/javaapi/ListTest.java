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

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class ListTest {

//	@Test public void bTest() { d(); }

	@Test
	public void autoIncreseCpacityTest() {
		var capacity = 5;
		var a = new ArrayList<String>(capacity);

		assertDoesNotThrow(() -> {
			for (int i = 0; i < capacity * 5; i++) {
				if (i % capacity == 0) {
					d();
				}
				a.add(Integer.toString(i));
			}
		});

		assertEquals(10, a.size());

		d();
	}

//	@Test
	void listSizeTest() {
		var a = new ArrayList<String>(30);
		assertEquals(0, a.size());

		a.add("");
		assertEquals(1, a.size());

	}

	private void d() {
	}

}

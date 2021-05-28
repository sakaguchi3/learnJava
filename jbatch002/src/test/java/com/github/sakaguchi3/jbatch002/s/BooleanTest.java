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
package com.github.sakaguchi3.jbatch002.s;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class BooleanTest {

//	@Test public void bTest() { d(); }

	@Test
	public void orTest() {
		assertTrue(true | true);
		assertTrue(true | false);
		assertFalse(false | false);

		assertTrue(true || true);
		assertTrue(true || false);
		assertFalse(false || false);
	}

	@Test
	public void andTest() {
		assertTrue(true & true);
		assertFalse(true & false);
		assertFalse(false & false);

		assertTrue(true && true);
		assertFalse(true && false);
		assertFalse(false && false);
	}

	private void d() {
	}

}

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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class SetTest {

//	@Test void xTest() { }

	@Test
	void eqSameValueTest() {
		var j1 = new a("a");
		var j2 = new a("a");

		assertTrue(Objects.equals(j1, j2));
		// require not-same
		assertThrows(IllegalArgumentException.class, () -> {
			Set.of(j1, j2);
		});

		var j3 = new a("b");
		var j4 = new a("c");
		assertFalse(Objects.equals(j3, j4));
		assertDoesNotThrow(() -> {
			Set.of(j3, j4);
		});

		d();

	}

	private static class a {
		String s;

		public a(String s) {
			this.s = s;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null)
				return false;
			if (!(obj instanceof a))
				return false;
			a that = (a) obj;

			return Objects.equals(this.s, that.s);
		}

		@Override
		public int hashCode() {
			return Objects.hash(s);
		}
	}

	private void d() {
	}
}

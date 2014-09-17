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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class DefaultValueTest {

//	@Test void s() {}

	@Test
	void nullMethodTest() {
		var c = new c();

		assertThrows(NullPointerException.class, () -> {
			c.throwException();
		});
		assertThrows(NullPointerException.class, () -> {
			c.throwException2();
		});
		assertThrows(NullPointerException.class, () -> {
			c.throwException3();
		});
		assertDoesNotThrow(() -> {
			c.noException();
		});

		d();
	}

//	@Test
	void initializeDefaultValueTest() {
		var c = new c1();

		assertNull(c.intObj);
		assertNotNull(c.intPrimitive);
		assertEquals(0, c.intPrimitive);

		assertNull(c.doubleObj);
		assertEquals(0.0d, c.doublePrimitive);

		assertNull(c.str);

		d();
	}

	private void d() {
	}

	private static class c {
		void noException() {
			Integer x = null;
			// not throw
			if (x == new Integer(3)) {
			}
		}

		// throws Exception
		void throwException3() {
			fuga();
		}

		// throws Exception
		void throwException2() {
			int x = hoge();
		}

		void throwException() {
			Integer x = null;
			// throws Exception
			if (x == 3) {
			}
		}

		private int fuga() {
			Integer a = hoge();
			return a;
		}

		private Integer hoge() {
			return null;
		}

	}

	private static class c1 {
		Integer intObj;
		int intPrimitive;

		Double doubleObj;
		double doublePrimitive;

		String str;
	}

}

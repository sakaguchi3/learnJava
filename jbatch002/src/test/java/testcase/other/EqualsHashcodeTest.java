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
package testcase.other;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;

import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;

public class EqualsHashcodeTest {

//	@Test void a() { }

	@Test
	void eqTest() {
		var j1 = new a("a", "b");
		var j2 = new a("a", "b");
		assertTrue(j1.equals(j2));

		var j4 = new a("a", "b");
		var j3 = new a("a", "c");
		assertFalse(j4.equals(j3));
	}

	private void d() {
	}

	// inner class ------------------------------

	@AllArgsConstructor
	private static class a {
		String s1, s2;

		@Override
		public int hashCode() {
			return Objects.hash(s1, s2);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			a other = (a) obj;
			if (s1 == null) {
				if (other.s1 != null)
					return false;
			} else if (!s1.equals(other.s1))
				return false;
			if (s2 == null) {
				if (other.s2 != null)
					return false;
			} else if (!s2.equals(other.s2))
				return false;
			return true;
		}
	}

}

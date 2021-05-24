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
package com.github.sakaguchi3.jbatch002.caffeine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

/**
 * cache library
 */
public class CaffeineTest {

//	@Test public void bTest() { d(); }

	@Test
	void cacheExpireTest() {
		Cache<String, String> cache = Caffeine.newBuilder() //
				.expireAfterWrite(100, TimeUnit.MILLISECONDS) //
				.build();

		// get & put
		cache.put("b", "");

		sleep(1);

		var b = cache.getIfPresent("b");
		assertNull(b);
	}

//	@Test
	void maxTest() {

		Cache<String, String> cache = Caffeine.newBuilder() //
				.maximumSize(2L) //
				.expireAfterWrite(1, TimeUnit.SECONDS) //
				.build();

		// put
		cache.put("b", "bkey");
		cache.put("c", "ckey");
		cache.put("d", "dkey");

		// get & put
		var aa = cache.get("a", k -> k + "_");

		// いずれか2つがnullになる
		var a = cache.getIfPresent("a");
		var b = cache.getIfPresent("b");
		var c = cache.getIfPresent("c");
		var d = cache.getIfPresent("d");

		var cnt = countNull(a, b, c, d);

		assertEquals("a_", aa);
		assertEquals(2, cnt);
	}

	private static int countNull(Object... os) {
		int nullCnt = 0;
		for (Object o : os) {
			if (o == null)
				nullCnt++;
		}
		return nullCnt;
	}

	private static void sleep(long time) {
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void d() {
	}

}

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
package com.github.sakaguchi3.jbatch002.concurrent.thread2;

import java.util.Objects;
import java.util.function.Function;

public class CacheBuilder<K, V> {

	private static final ThreadPool pool = new ThreadPool();

	private int interval = 10;
	private Function<K, V> update = null;

	public CacheBuilder<K, V> interval(int intervalSec) {
		this.interval = intervalSec;
		return this;
	}

	public CacheBuilder<K, V> update(Function<K, V> func) {
		this.update = func;
		return this;
	}

	public CacheImpl<K, V> build() {
		Objects.requireNonNull(update);

		var cacheImpl = new CacheImpl<K, V>(update, pool);

		pool.add(cacheImpl, interval);
		pool.start();

		return cacheImpl;
	}

}

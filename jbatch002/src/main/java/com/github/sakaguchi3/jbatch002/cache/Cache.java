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
package com.github.sakaguchi3.jbatch002.cache;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

import javax.annotation.Nullable;

import io.vavr.Tuple;
import io.vavr.Tuple3;

public class Cache<K, V> implements Runnable {

	private final Map<K, Tuple3<V, Boolean, Long>> cache = new ConcurrentHashMap<>();
	private final Function<K, V> f;
	private final AtomicBoolean isRun = new AtomicBoolean(false);
	private final int period;
	private final ScheduledExecutorService ex = Executors.newScheduledThreadPool(1);

	public Cache(Function<K, V> function, int intervalSecond) {
		this.f = function;
		this.period = intervalSecond;
	}

	@Nullable
	public V get(K k) {
		if (!cache.containsKey(k)) {
			return null;
		}

		var vOld = cache.get(k);

		// 'update_flg' on and update map
		var vNew = cache.put(k, vOld.map2(__ -> true));
		return vNew._1();
	}

	@Override
	public void run() {
		update();
	}

	public void start() {
		// already runnning
		if (isRun.get()) {
			return;
		}
		ex.scheduleAtFixedRate(this, 0, 10, TimeUnit.SECONDS);
		isRun.set(true);
	}

	public void shutdown() throws InterruptedException {
		if (Objects.isNull(ex)) {
			return;
		}

		ex.shutdown();
		try {
			if (!ex.awaitTermination(30, TimeUnit.SECONDS)) {
				ex.shutdownNow();
			}
			isRun.set(false);
		} catch (Exception e) {
			ex.shutdownNow();
			throw e;
		}
	}

	private void update() {
		long now = System.currentTimeMillis();
		long next = now + period + 1_000;

		for (var e : cache.entrySet()) {
			var k = e.getKey();
			var v = e.getValue();

			if (v._3 > now) {
				continue;
			}

			if (!v._2) {
				cache.remove(k);
				continue;
			}

			var v1New = f.apply(k);
			var vNew = Tuple.of(v1New, false, next);
			cache.put(k, vNew);
		}
	}

	private void d() {
	}

}

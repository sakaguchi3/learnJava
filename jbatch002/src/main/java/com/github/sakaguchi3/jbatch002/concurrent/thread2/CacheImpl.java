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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;

public class CacheImpl<K, V> implements Runnable {

	final ConcurrentHashMap<K, Data<V>> cache = new ConcurrentHashMap<>();
	final Function<K, V> funcUpdate;
	final Shutdown funcShutdown;

	public CacheImpl(Function<K, V> func, Shutdown funcShutdown) {
		this.funcUpdate = func;
		this.funcShutdown = funcShutdown;
	}

	public V remove(Object key) {
		if (!cache.containsKey(key)) {
			return null;
		}
		return cache.remove(key).getData();
	}

	public boolean contains(K key) {
		return cache.contains(key);
	}

	public V get(K k) {
		K key = (K) k;
		if (!cache.containsKey(key)) {
			var v = funcUpdate.apply(key);
			cache.put(key, Data.of(v));
			return v;
		}

		var d = cache.get(key);
		d.setUpdateNext();
		return d.getData();
	}

	@Override
	public void run() {
		update();
	}

	public void shutdown() {
		funcShutdown.shutdown();
	}

	@Override
	public int hashCode() {
		return Objects.hash(cache, funcUpdate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CacheImpl other = (CacheImpl) obj;

		return true //
				&& Objects.equals(funcUpdate, other.funcUpdate) //
				&& Objects.equals(cache, other.cache);
	}

	void update() {
		// rmove
		var keyRemove = cache.entrySet().stream() //
				.filter(x -> !x.getValue().isUpdateNext()) //
				.map(x -> x.getKey()) //
				.collect(Collectors.toList());
		keyRemove.forEach(x -> cache.remove(x));

		// update
		for (var e : cache.entrySet()) {
			var k = e.getKey();
			var vNew = funcUpdate.apply(k);
			cache.replace(k, Data.of(vNew));
		}
	}

	protected static class Data<V> {
		private final V data;

		private AtomicBoolean isUpdate = new AtomicBoolean(false);

		public static <V> Data<V> of(V data) {
			return new Data<V>(data);
		}

		public Data(V data) {
			this.data = data;
		}

		public boolean isUpdateNext() {
			return isUpdate.get();
		}

		public void setUpdateNext() {
			this.isUpdate.set(true);
		}

		public V getData() {
			return data;
		}
	}

}

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
package com.github.sakaguchi3.jbatch002.concurrent;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadPool<T extends Runnable> implements Runnable {

	private static final Logger log = LogManager.getLogger();

	/** (t, time, interval) */
	private final Set<Task<T>> threadPool = new ConcurrentSkipListSet<>();
	private final ScheduledExecutorService ex = Executors.newScheduledThreadPool(1);

	public void start() {
		if (!ex.isShutdown()) {
			log.error("already running.");
			return;
		}
		ex.scheduleAtFixedRate(this, 0, 1, TimeUnit.SECONDS);
	}

	public void add(T task, int interval) {
		var t = new Task<T>(task, interval);
		threadPool.add(t);
	}

	public void shutdown() {
		if (ex.isShutdown()) {
			log.error("already shutdown.");
			return;
		}
		threadPool.clear();
		shutdown(ex, 60 * 2);
	}

	@Override
	public void run() {
		execThreadPool();
	}

	protected void execThreadPool() {
		long now = System.currentTimeMillis();

		var threadsUpdateTarget = threadPool.stream() //
				.filter(v -> now > v.getExpire()) //
				.collect(Collectors.toList());
		threadPool.removeAll(threadsUpdateTarget);

		var threadsNew = threadsUpdateTarget.stream() //
				.map(v -> v.updateExpire(now + v.getExpire())) //
				.collect(Collectors.toList());
		threadPool.addAll(threadsNew);

		var ex = Executors.newCachedThreadPool();
		threadsNew.forEach(v -> ex.submit(v.getThread()));

		shutdown(ex, 5);
	}

	protected void shutdown(ExecutorService ex, int timeMinute) {
		if (Objects.isNull(ex)) {
			return;
		}

		ex.shutdown();
		try {
			if (!ex.awaitTermination(30, TimeUnit.SECONDS)) {
				ex.shutdownNow();
			}
		} catch (Exception e) {
			ex.shutdownNow();
		}
	}

	protected static class Task<T> {
		private final T thread;
		final int intervalSec;
		long expire = 0;

		public Task(T thread, int intervalSec, long expire) {
			this.thread = thread;
			this.intervalSec = intervalSec;
			this.expire = expire;
		}

		public Task(T thread, int intervalSec) {
			this.thread = thread;
			this.intervalSec = intervalSec;
		}

		@Override
		public int hashCode() {
			return Objects.hash(thread);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Task that = (Task) obj;
			return Objects.equals(this.thread, that.thread);
		}

		public Task<T> updateExpire(long expire) {
			this.expire = expire;
			return this;
		}

		public long getExpire() {
			return expire;
		}

		public T getThread() {
			return thread;
		}
	}

}

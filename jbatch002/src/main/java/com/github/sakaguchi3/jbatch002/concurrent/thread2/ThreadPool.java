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
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadPool implements Runnable, Shutdown {

	// --------------------------------------------------
	// member
	// --------------------------------------------------

	protected final ConcurrentLinkedQueue<Task> tasks = new ConcurrentLinkedQueue<>();
	protected final ScheduledExecutorService esMain = Executors.newSingleThreadScheduledExecutor();
	protected final ExecutorService esChildren = Executors.newCachedThreadPool();

	// --------------------------------------------------
	// method
	// --------------------------------------------------

	@Override
	public void run() {
		try {
			runChildren();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void add(Runnable w, int intervalSec) {
		var t = new Task(0, intervalSec * 1_000, w);
		tasks.add(t);
	}

	public void start() {
		if (esChildren.isShutdown()) {
			return;
		}
		esMain.scheduleAtFixedRate(this, 0, 100, TimeUnit.MILLISECONDS);
	}

	public void shutdown(int time) {
		tasks.clear();
		shutdown(esChildren, time);
		shutdown(esMain, time);
	}

	public void shutdown() {
		tasks.clear();
		shutdown(esChildren, 30);
		shutdown(esMain, 30);
	}

	protected void runChildren() {
		long now = System.currentTimeMillis();
		tasks.forEach(worker -> {
			if (!worker.isExpire(now)) {
				return;
			}
			if (worker.isRun()) {
				return;
			}

			long expireNext = now + worker.getInterval();
			worker.setExpire(expireNext);
			esChildren.submit(worker);
		});
	}

	protected void shutdown(ExecutorService ex, int timeSec) {
		if (Objects.isNull(ex)) {
			return;
		}

		ex.shutdown();
		try {
			if (!ex.awaitTermination(timeSec, TimeUnit.SECONDS)) {
				ex.shutdownNow();
			}
		} catch (Exception e) {
			ex.shutdownNow();
		}
	}

	// --------------------------------------------------
	// inner class
	// --------------------------------------------------

	protected static class Task implements Runnable {
		private long expire;
		private final int interval;
		private final AtomicBoolean isRun = new AtomicBoolean(false);
		private final Runnable task;

		public Task(long expire, int interval, Runnable task) {
			this.setExpire(expire);
			this.interval = interval;
			this.task = task;
		}

		public void run() {
			isRun.set(true);
			task.run();
			isRun.set(false);
		}

		@Override
		public int hashCode() {
			return Objects.hash(task);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Task other = (Task) obj;
			return Objects.equals(task, other.task);
		}

		public long getExpire() {
			return expire;
		}

		public void setExpire(long expire) {
			this.expire = expire;
		}

		public int getInterval() {
			return interval;
		}

		public boolean isExpire(long now) {
			return expire < now;
		}

		public Runnable getTask() {
			return task;
		}

		public boolean isRun() {
			return isRun.get();
		}
	}

}

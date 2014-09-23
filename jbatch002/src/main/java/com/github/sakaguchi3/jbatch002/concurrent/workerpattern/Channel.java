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
package com.github.sakaguchi3.jbatch002.concurrent.workerpattern;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Channel {

	private final BlockingQueue<Request> reqs = new LinkedBlockingQueue<>();
	private final List<Worker> workers;
	private final ExecutorService es = Executors.newCachedThreadPool();

	public Channel() {
		this.workers = IntStream.range(0, 5) //
				.mapToObj(__ -> new Worker(this)) //
				.collect(Collectors.toUnmodifiableList());
	}

	public Channel(int threadSize) {
		this.workers = IntStream.range(0, threadSize) //
				.mapToObj(__ -> new Worker(this)) //
				.collect(Collectors.toUnmodifiableList());
	}

	public void start() {
		workers.forEach(w -> es.submit(w));
	}

	public void shutdown() {
		workers.forEach(w -> w.shutdown());
		shutdown(es, 10);
	}

	public void put(Request r) throws InterruptedException {
		reqs.put(r);
	}

	public Request take() throws InterruptedException {
		return reqs.take();
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
}

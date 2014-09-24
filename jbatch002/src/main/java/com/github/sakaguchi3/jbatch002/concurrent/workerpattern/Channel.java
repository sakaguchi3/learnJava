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

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Channel {

	private final BlockingQueue<Request> reqs = new LinkedBlockingQueue<>();
	private final BlockingQueue<Worker> workers = new LinkedBlockingQueue<>();
	private final ExecutorService es = Executors.newCachedThreadPool();

	public void start() {
		for (var v : workers) {
			es.submit(v);
		}
	}

	public void shutdown() {
		workers.forEach(v -> {
			v.shutdown();
		});
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

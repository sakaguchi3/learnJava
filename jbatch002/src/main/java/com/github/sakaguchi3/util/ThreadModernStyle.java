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
package com.github.sakaguchi3.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadModernStyle implements Runnable {

	private static final Logger log = LogManager.getLogger();
	final int interval = 1;
	final ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);

	// ------------------------------------------------------------
	// method
	// ------------------------------------------------------------

	// public ----------------------------------------

	@Override
	public void run() {
		try {
			whatYouWnatDo();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public void start() {
		pool.scheduleAtFixedRate(this, 0, interval, TimeUnit.SECONDS);
	}

	public void stop() {
		try {
			shutdown(pool, 1);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	// private ----------------------------------------

	protected static void shutdown(ExecutorService ex, int sec) throws InterruptedException {
		ex.shutdown();
		try {
			if (!ex.awaitTermination(sec, TimeUnit.SECONDS)) {
				ex.shutdownNow();
			}
		} catch (Exception e) {
			ex.shutdownNow();
			throw e;
		}
	}

	protected void whatYouWnatDo() {

	}

	private void d() {
	}

}

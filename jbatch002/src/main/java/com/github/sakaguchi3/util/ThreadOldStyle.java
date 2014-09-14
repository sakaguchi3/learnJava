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

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadOldStyle implements Runnable {

	private static final Logger log = LogManager.getLogger();

	private final AtomicBoolean isRun = new AtomicBoolean(false);

	// ------------------------------------------------------------
	// method
	// ------------------------------------------------------------

	// public ----------------------------------------

	@Override
	public void run() {
		try { 
			isRun.set(true);
			loop();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public void stop() {
		isRun.set(false);
	}

	// private ----------------------------------------

	protected void loop() throws InterruptedException {
		while (isRun.get()) {
			whatYouWnatDo();
			TimeUnit.SECONDS.sleep(1);
		}
	}

	protected void whatYouWnatDo() {

	}

	private void d() {
	}

}

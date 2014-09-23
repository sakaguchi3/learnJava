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

import java.util.concurrent.atomic.AtomicBoolean;

public class Worker implements Runnable {

	final Channel channel;
	final AtomicBoolean isRun = new AtomicBoolean(true);

	public Worker(Channel channel) {
		this.channel = channel;
	}

	@Override
	public void run() {
		try {
			localExec();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void localExec() throws Exception {
		while (isRun.get()) {
			var req = channel.take();
			req.execute();
		}
	}

	void shutdown() {
		this.isRun.set(false);
	}

}

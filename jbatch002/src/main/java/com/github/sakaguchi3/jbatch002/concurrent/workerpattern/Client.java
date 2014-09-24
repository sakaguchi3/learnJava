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

/**
 * create request
 */
class Client {
	final Channel channel;

	public Client(Channel channel) {
		this.channel = channel;
	}

	public void run() {
		try {
			localExec();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	void localExec() throws Exception {
		for (int i = 0; i < 10; i++) {
			var r = new Request();
			channel.put(r);
		}
	}

}

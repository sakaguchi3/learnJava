/**
 * Copyright 2020 sakaguchi<uqw@outlook.jp>, https://github.com/sakaguchi3/
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
package com.github.sakaguchi3.serverpro.server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.sakaguchi3.serverpro.config.PorpConfig;
import com.github.sakaguchi3.serverpro.config.ResourceManager;

@WebListener
public class BaseServletContextListener implements ServletContextListener {

	/** */
	static final Logger LOG = LogManager.getLogger();
	PorpConfig config;
	ResourceManager resource;
	
	public BaseServletContextListener() {
		System.out.println("constructor");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {

		System.out.println("-------------> CONTEXT INITIALIZE <-------------");

		var confOp = PorpConfig.getInstance();
		var resourceOp = ResourceManager.getInstance();
		if (confOp.isEmpty() || resourceOp.isEmpty()) {
			LOG.error("init config, resource err. shutdown.");
			System.exit(0);
		}
		this.config = confOp.get();
		this.resource = resourceOp.get();

		debug();
	}

	public void contextDestroyed(ServletContextEvent sce) {

		System.out.println("-------------> CONTEXT DESTROYED <-------------");

		// shutdown resources
		resource.shutdown();
	}

	void debug() { 
	}

}

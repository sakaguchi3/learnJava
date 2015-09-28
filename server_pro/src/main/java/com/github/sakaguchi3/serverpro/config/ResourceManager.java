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
package com.github.sakaguchi3.serverpro.config;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.tools.picocli.CommandLine.InitializationException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Config Class This Singleton Class is responsible for loading all of the
 * general configuration options for this app.
 *
 * @author sakaguchi
 */
public class ResourceManager {

	// ---------------------------------------------------------
	// field
	// ---------------------------------------------------------

	/**
	 * The instance of Configuration that this Class is storing
	 */
	private static ResourceManager configure = null;

	/**
	 * LOGGER is an instance of the Logger class so that we can do proper logging
	 */
	protected static final Logger LOG = LogManager.getLogger();

	private final ExecutorService threadPool;

	protected final ObjectMapper MAPPER = (new ObjectMapper()) //
			.setSerializationInclusion(JsonInclude.Include.NON_EMPTY); // remove null

	// ---------------------------------------------------------
	// constructor
	// ---------------------------------------------------------

	/**
	 * Get the Instance of this class There should only ever be one instance of this
	 * class and other classes can use this static method to retrieve the instance
	 *
	 * @return Config the stored Instance of this class
	 * @throws Exception
	 * @throws IOException
	 */
	public static synchronized Optional<ResourceManager> getInstance() {

		if (configure == null) {
			try {
				configure = new ResourceManager();
			} catch (Exception e) {
				System.out.println("Config error :" + e.getMessage());
				LOG.error("Config error :" + e.getMessage(), e);
			}
		}

		return Optional.ofNullable(configure);
	}

	/**
	 * Constructor This is private so it cannot be instantiated by anyone other than
	 * this class Try and load the current Config, if no config was found, try to
	 * create a new one
	 * 
	 * @throws IOException
	 */
	private ResourceManager() throws InitializationException {
		threadPool = Executors.newCachedThreadPool();
	}

	// ---------------------------------------------------------
	// public method
	// ---------------------------------------------------------

	public ExecutorService getThreadPool() {
		return threadPool;
	}
	
	public ObjectMapper getObjectMapper() {
		return MAPPER;
	}

	// ---------------------------------------------------------
	// private method
	// ---------------------------------------------------------

	/**
	 * shutdown resources
	 */
	public void shutdown() {
		System.out.println("resource shutdown");
		try {
			getThreadPool().shutdown();
			getThreadPool().awaitTermination(500, TimeUnit.MICROSECONDS);
			getThreadPool().shutdownNow(); // throw InterruptedException
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}

	}

}

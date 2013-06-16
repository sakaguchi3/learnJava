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
package com.github.sakaguchi3.jbatch002.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Optional;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropConfig {

	// ---------------------------------------------------------
	// field
	// ---------------------------------------------------------

	/**
	 * The instance of Configuration that this Class is storing
	 */
	private static PropConfig configure = null;

	/**
	 * CONFIG_FILENAME is the file location of the configuration properties file
	 */
	private static final String CONFIG_FILENAME = "prop_for_testenv.properties";

	/**
	 * 
	 */
	private Properties properties = new Properties();

	/**
	 * LOGGER is an instance of the Logger class so that we can do proper logging
	 */
	static final Logger LOG = LogManager.getLogger();

	String csvOutputPath = null;

	// ---------------------------------------------------------
	// constructor
	// ---------------------------------------------------------

	/**
	 * Get the Instance of this class There should only ever be one instance of this
	 * class and other classes can use this static method to retrieve the instance
	 *
	 * @return Config the stored Instance of this class
	 * @throws IOException
	 */
	public static synchronized Optional<PropConfig> getInstance() {
		if (configure == null) {
			try {
				configure = new PropConfig(CONFIG_FILENAME);
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
	private PropConfig(String configFileName) throws IOException {
		loadConf(configFileName);
	}

	// ---------------------------------------------------------
	// private method
	// ---------------------------------------------------------

	/**
	 * load config specified at fileName
	 * 
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	void loadConf(String configFileName) throws IOException {

		// config file
		URL configFile = PropConfig.class.getClassLoader().getResource(configFileName);

		// load config info from properties file and set info to Properties instance.
		try (InputStream in = configFile.openStream();) {
			this.properties.load(in);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw e;
		}

		// -- load info from Properties instance --

		this.csvOutputPath = ((String) properties.get("path.csv.output")).trim();

		// -- show log --

		// export properties info to log file
		this.properties.keySet().stream() //
				.map(p -> p.toString()) //
				.forEach(key -> LOG.info(CONFIG_FILENAME + " - key=" + key + ", value=" + properties.getProperty(key)));
	}

	/**
	 * convert string to int
	 * 
	 * @param s
	 * @return
	 */
	Optional<Integer> parseInt(String s) {
		try {
			int num = Integer.parseInt(s);

			return Optional.of(num);

		} catch (Exception e) {

			LOG.error(e.getMessage(), e);

			return Optional.empty();
		}

	}

	public String getCsvOutputPath() {
		return csvOutputPath;
	}

	// ---------------------------------------------------------
	// public method
	// ---------------------------------------------------------

}

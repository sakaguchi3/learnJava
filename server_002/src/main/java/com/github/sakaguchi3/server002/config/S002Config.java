package com.github.sakaguchi3.server002.config;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Optional;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Config Class This Singleton Class is responsible for loading all of the
 * general configuration options for this app.
 *
 * @author sakaguchi
 */
public class S002Config {

	// ---------------------------------------------------------
	// field
	// ---------------------------------------------------------

	/**
	 * The instance of Configuration that this Class is storing
	 */
	private static S002Config configure = null;

	/**
	 * CONFIG_FILENAME is the file location of the configuration properties file
	 */
	private static final String CONFIG_FILENAME = "server002.properties";

	/**
	 * 
	 */
	private Properties properties = new Properties();

	/**
	 * LOGGER is an instance of the Logger class so that we can do proper logging
	 */
	static final Logger LOG = LogManager.getLogger();

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
	public static synchronized S002Config getInstance() {

		if (configure == null) {

			try {

				configure = new S002Config(CONFIG_FILENAME);

			} catch (Exception e) {

				System.out.println("Config error :" + e.getMessage());
				LOG.error("Config error :" + e.getMessage(), e);

				System.exit(0);
			}
		}

		return configure;
	}

	/**
	 * Constructor This is private so it cannot be instantiated by anyone other than
	 * this class Try and load the current Config, if no config was found, try to
	 * create a new one
	 * 
	 * @throws IOException
	 */
	private S002Config(String configFileName) throws IOException {
		loadConf(configFileName);
	}

	// ---------------------------------------------------------
	// method
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
		URL configFile = S002Config.class.getClassLoader().getResource(configFileName);

		String tmp;

		// load config info from properties file and set info to Properties instance.
		try (InputStream in = configFile.openStream();) {
			this.properties.load(in);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw e;
		}

		// -- load info from Properties instance --

		// -- show log --

		// export properties info to log file
		this.properties.keySet().stream() //
				.map(p -> p.toString()) //
				.forEach(key -> LOG.info(CONFIG_FILENAME + " - key=" + key + ", value=" + this.properties.getProperty(key))) //
		;

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

	// ---------------------------------------------------------
	// setter/getter
	// ---------------------------------------------------------

}

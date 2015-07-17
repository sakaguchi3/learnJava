package com.github.sakaguchi3.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Optional;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 */
public class SysConfig {

	// ---------------------------------------------------------------------------------------------
	// - field
	// ---------------------------------------------------------------------------------------------

	private static Logger log = LogManager.getLogger();

	/** */
	private static final String CONFIG_FILENAME = "mail.properties";
	/** (Singleton) */
	private static SysConfig INSTANCE = null;

	/** */
	private final Properties properties = new Properties();

	/** */
	private String hostname = "";
	/**  */
	private String version = "";

	// mail info ----

	String user = null;
	String pass = null;
	String host = null;
	int port;
	String from = null;
	boolean starttls;
	int connectionTimeout;
	int timeout;

	//
	private final String KEY_USER = "mail.smtp.user";
	private final String KEY_PASS = "mail.smtp.pass";
	private final String KEY_HOST = "mail.smtp.host";
	private final String KEY_PORT = "mail.smtp.port";
	private final String KEY_FROM = "mail.smtp.from";
	private final String KEY_STARTTLS = "mail.smtp.starttls.enabl";
	private final String KEY_TIMEOUT_CONNECTION = "mail.smtp.connectiontimeout";
	private final String KEY_TIMEOUT = "mail.smtp.timeout";

	// ---------------------------------------------------------------------------------------------
	// - constructor
	// ---------------------------------------------------------------------------------------------

	/**
	 * 
	 */
	private SysConfig(URL url) throws IOException {

		version = Optional.ofNullable(SysConfig.class.getPackage().getImplementationVersion()).orElse("");
		if (!version.isEmpty()) {
			this.properties.setProperty("version", version);
		}
		try (InputStream in = url.openStream()) {
			properties.load(in);
		}

		hostname = Optional.ofNullable(System.getenv("HOSTNAME")).orElse("");

		user = properties.getProperty(KEY_USER).trim();
		pass = properties.getProperty(KEY_PASS).trim();
		host = properties.getProperty(KEY_HOST).trim();
		port = Integer.parseInt(properties.getProperty(KEY_PORT).trim());
		from = properties.getProperty(KEY_FROM).trim();
		starttls = Boolean.valueOf(properties.getProperty(KEY_STARTTLS));
		connectionTimeout = Integer.parseInt(properties.getProperty(KEY_TIMEOUT_CONNECTION).trim());
		timeout = Integer.parseInt(properties.getProperty(KEY_TIMEOUT).trim());

		// print properties info
		properties.entrySet().stream()//
				.forEach(v -> log.info(CONFIG_FILENAME + " - " + v.getKey() + " = " + v.getValue()));
		log.info("→ version               = " + version);
	}

	/**
	 */
	public static void initInstance() throws IOException {
		try {
			URL url = SysConfig.class.getClassLoader().getResource(CONFIG_FILENAME);
			if (url == null) {
				throw new IllegalArgumentException("指定ファイルが存在しない");
			}
			INSTANCE = new SysConfig(url);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return;
	}

	/**
	 */
	public static synchronized SysConfig getInstance() {
		if (INSTANCE == null) {
			try {
				SysConfig.initInstance();
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage(), e);
				log.error("Fatal err. shutdown...");
				System.exit(-1);
			}
		}
		return INSTANCE;
	}

	// ---------------------------------------------------------------------------------------------
	// - method
	// ---------------------------------------------------------------------------------------------

	/**
	 * @return the hostname
	 */
	public String getHostname() {
		return hostname;
	}

	public String getUser() {
		return user;
	}

	public String getPass() {
		return pass;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getFrom() {
		return from;
	}

	public boolean isStarttls() {
		return starttls;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public int getTimeout() {
		return timeout;
	}

}

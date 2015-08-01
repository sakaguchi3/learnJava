package com.github.sakaguchi3.server002.server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.sakaguchi3.server002.config.S002Config;

@WebListener
public class BaseServletContextListener implements ServletContextListener {

	/** */
	static final Logger LOGGER = LogManager.getLogger();

	@Override
	public void contextInitialized(ServletContextEvent event) {

		System.out.println("START");

		try {

			S002Config.getInstance();

		} catch (Exception e) {

			e.printStackTrace();
			LOGGER.error(e.getMessage(), e);

			throw e;

		} finally {

		}

		debug();
	}

	void debug() {

	}

}

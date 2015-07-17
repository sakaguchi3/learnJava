package com.github.sakaguchi3.jbatch002.s;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.github.sakaguchi3.util.MongoQueryCreator;


public class TestMongoQueryCreator {

	// ------------------------------------------------------
	// field
	// ------------------------------------------------------

	static final Logger LOGGER = LogManager.getLogger();

	// ------------------------------------------------------
	// public
	// ------------------------------------------------------

	@Test
	public void aa() {
		var create = new MongoQueryCreator();
		var path = "mongo_query.txt";

		try {
			create.write(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		debug();

	}

	private void debug() {

	}
}

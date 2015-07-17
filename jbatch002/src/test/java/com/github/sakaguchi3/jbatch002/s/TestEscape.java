package com.github.sakaguchi3.jbatch002.s;

import org.apache.commons.text.StringEscapeUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestEscape {

	// ------------------------------------------------------
	// field
	// ------------------------------------------------------

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	// ------------------------------------------------------
	// public
	// ------------------------------------------------------

	@Test
	public void aa() {
		var s1 = "&  a  \u03A0";
		var s2 = StringEscapeUtils.escapeHtml4(s1);

		System.out.println(s2);
		debug();

	}

	private void debug() {

	}
}

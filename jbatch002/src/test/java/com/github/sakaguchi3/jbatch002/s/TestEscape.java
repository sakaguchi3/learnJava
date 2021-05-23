package com.github.sakaguchi3.jbatch002.s;

import org.apache.commons.text.StringEscapeUtils;
import org.junit.jupiter.api.Test;

public class TestEscape {

	// ------------------------------------------------------
	// field
	// ------------------------------------------------------

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

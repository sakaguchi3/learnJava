package com.github.sakaguchi3.jbatch002.apache.lang3;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

public class StringUtilTest {

	@Test
	public void uTest() {
		debug();
	}

	@Test
	public void isAlphanumericTest() {
		var b10 = StringUtils.isAlphanumeric("aCJ321");
		var b20 = StringUtils.isAlphanumeric("aCJ 321");
		var b30 = StringUtils.isAlphanumeric("aCJ-321");
		debug();
	}

	void debug() {
	}

}

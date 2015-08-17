package com.github.sakaguchi3.jbatch002.javaapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class ByteTest {

	@Test
	void strToByteTest() { 
		var plainStr = "test";
		var bytes = plainStr.getBytes();

		var decStr1 = new String(bytes);
		// address ex: [B@fba92d9
		var desStr2 = bytes.toString();
		// [116, 101, 115, 116]
		var desStr3 = Arrays.toString(bytes);

		assertEquals("test", decStr1);
		assertNotEquals("test", desStr2);
		assertNotEquals("test", desStr3);
	}

	void debug() {
	}

}

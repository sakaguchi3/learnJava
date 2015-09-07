package com.github.sakaguchi3.jbatch002.s;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.github.sakaguchi3.util.UtilFile;

public class UtilZipTest {

	private final Map<String, String> envMap = Collections.unmodifiableMap(System.getenv());

	@Test
	void gzipReadWriteTest() {
		var filePath = envMap.get("writefile");

		var str1 = "plain text";
		var byte1 = str1.getBytes();
		var booleanWrite = UtilFile.writeBytesGzip(filePath, byte1);
		assertTrue(booleanWrite);

		var readByteOp = UtilFile.readBytesGzip(filePath);
		assertTrue(readByteOp.isPresent());

		// byte -> str
		var str2 = readByteOp.map(String::new).orElse("ng");
		assertEquals(str1, str2);
	}

	@Test
	void gzipTest() {
		var str1 = "plain text";
		// str -> byte
		var byte1 = str1.getBytes();

		var compressOp = UtilFile.zipCompress(byte1);
		var decompressOp = compressOp.flatMap(v -> UtilFile.zipUncompress(v));

		// byte -> str
		var str2 = decompressOp.map(b -> new String(b)).orElse("err");
		assertEquals(str1, str2);
	}

	void debug() {
	}

}

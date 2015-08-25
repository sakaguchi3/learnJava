package com.github.sakaguchi3.jbatch002.s;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.github.sakaguchi3.jbatch002.api.CipherY;
import com.github.sakaguchi3.util.UtilFile;

public class FileTest {

	Map<String, String> m = System.getenv();

	@Test
	void writeByte2Test() {
		var filePath = m.get("writefile4");

		// write
		var writeByteArr = CipherY.generateIV();
		var writeBool = UtilFile.writeBytes(filePath, writeByteArr, false);

		// read
		var readByteOp = UtilFile.readBytes(filePath);
		var readByteArr = readByteOp.get();

		assertTrue(writeBool);
		assertTrue(readByteOp.isPresent());
		assertArrayEquals(writeByteArr, readByteArr);

	}

	@Test
	void writeByteTest() {
		// set env: writefile="/home/user/test.txt"
		var filePath = m.get("writefile3");
		var bytes = "caaaaaa\ndbbbbbbbb\n".getBytes();

		var b = UtilFile.writeBytes(filePath, bytes, true);
//		assertTrue(b);
	}

	@Test
	void writeStrTest() {
		// set env: writefile="/home/user/test.txt"
		var filePath = m.get("writefile1");
		var msg = "\n" + "__*猫_seeee" + "\n";
		var b = UtilFile.writeStr(filePath, msg, true);

//		assertTrue(b);
	}

	@Test
	public void readStrResourceTest() throws Exception {
		var file = "plaintxt.txt";
		var fs = UtilFile.readStrFromResource(file);
//		assertTrue(fs.isPresent());
	}

	void debug() {
	}

}

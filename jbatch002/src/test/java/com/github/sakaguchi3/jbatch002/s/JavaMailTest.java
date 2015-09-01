package com.github.sakaguchi3.jbatch002.s;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.github.sakaguchi3.util.JavaMail;
import com.github.sakaguchi3.util.UtilFile;

public class JavaMailTest {

	@Test
	public void sendMailTest() {

		var fileapath = "mail.dat";
		var contentOp = UtilFile.readStrFromResource(fileapath);
		if (contentOp.isEmpty()) {
			System.out.println("err");
			fail("Not found mail.dat");
			return;
		}

		var to = "to@example.com";
		var from = "from@example.com";
		var senderName = "_name";
		var subject = "新年のご挨拶";
		var content = contentOp.get();

		try { 
			var mail = JavaMail.newInstance();
			mail.send(to, from, senderName, subject, content);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	private void debug() {
	}
}

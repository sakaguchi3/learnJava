package com.github.sakaguchi3.jbatch002.s;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.github.sakaguchi3.util.JavaMail;
import com.github.sakaguchi3.util.UtilFile;

public class JavaMailTest {

	@Test
	public void t01Test() {

		var reader = new UtilFile();
		var fileapath = "mail.dat";

		try {

			Optional<String> contentOp = reader.readStrFromResource(fileapath);
			if (contentOp.isEmpty()) {
				System.out.println("err");
				return;
			}

			JavaMail mail = JavaMail.newInstance();

			String to = "yyy@example.com";
			String from = "xxx@example.com";
//			String from = "xxx@example.com";
//			String senderName = "ushi";
			String senderName = "_name";
			String subject = "新年のご挨拶";
			String content = contentOp.get();

			mail.send(to, from, senderName, subject, content);

		} catch (Exception e) {
			e.printStackTrace();
		}

		debug();
	}

	private void debug() {

	}
}

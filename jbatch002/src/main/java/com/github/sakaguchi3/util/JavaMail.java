package com.github.sakaguchi3.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaMail {

	// ------------------------------------------------------
	// field
	// ------------------------------------------------------

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	private static final String CONFIG_FILENAME = "mail.properties";

	Properties props = new Properties();

	// final String charset = "ISO-2022-JP";
	final String charset = "UTF-8";

	final String encoding = "base64";

	String smtpUsername = null;
	String smtpPassword = null;

	// key ----
	final static String KEY_USER = "mail.smtp.user";
	final static String KEY_PASS = "mail.smtp.pass";

	// const ---

	// ------------------------------------------------------
	// init
	// ------------------------------------------------------

	protected JavaMail() {

	}

	public static JavaMail newInstance() throws IOException {
		URL url = JavaMail.class.getClassLoader().getResource(CONFIG_FILENAME);

		JavaMail instance = new JavaMail();
		try (InputStream i = url.openStream()) {
			instance.props.load(i);
		} catch (Exception e) {
			throw e;
		}

		instance.smtpUsername = instance.props.getProperty(KEY_USER).trim();
		instance.smtpPassword = instance.props.getProperty(KEY_PASS).trim();
		instance.props.remove(KEY_USER);
		instance.props.remove(KEY_PASS);

		return instance;
	}

	// ------------------------------------------------------
	// public
	// ------------------------------------------------------

	public void send(//
			String to, //
			String from, //
			String senderName, //
			String subject, // 題名
			String content // 本文
	) {

		try {

			this.props = trim(props);
			_send(to, from, senderName, subject, content);

		} catch (MessagingException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	// ------------------------------------------------------
	// private
	// ------------------------------------------------------

	protected Properties trim(Properties p) {

		Properties p2 = new Properties();
		p.entrySet().stream() //
				.filter(v -> v.getValue() instanceof String) //
				.forEach(v -> {
					p2.put(v.getKey(), ((String) v.getValue()).trim());
				});

		return p2;
	}

	protected void _send(//
			String to, //
			String from, //
			String senderName, //
			String subject, // 題名
			String content // 本文
	) throws UnsupportedEncodingException, MessagingException {

		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(smtpUsername, smtpPassword);
			}
		});

		MimeMessage message = new MimeMessage(session);

		// Set From:
		message.setFrom(new InternetAddress(from, senderName));
		// Set ReplyTo:
		message.setReplyTo(new Address[] { new InternetAddress(from) });
		// Set To:
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));

		message.setSubject(subject, charset);
		message.setText(content, charset);

		message.setHeader("Content-Transfer-Encoding", encoding);

		Transport.send(message);

	}

	final private void nop() {

	}

}

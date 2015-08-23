package com.github.sakaguchi3.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilHttp {

	protected static final Logger log = LogManager.getLogger();

	// ---------------------------------------------------------------------------------------------
	// - public method
	// ---------------------------------------------------------------------------------------------

	public static Optional<String> post(String url, String body) {
		try {
			var resBody = _post(url, body);
			return Optional.ofNullable(resBody);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Optional.empty();
		}
	}

	public static Optional<String> get(String url) {
		try {
			var resBody = _get(url);
			return Optional.ofNullable(resBody);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Optional.empty();
		}
	}

	// ---------------------------------------------------------------------------------------------
	// - private method
	// ---------------------------------------------------------------------------------------------

	protected static String _post(String url, String body) throws IOException, InterruptedException {
		HttpRequest request = HttpRequest //
				.newBuilder(URI.create(url)) //
				.POST(BodyPublishers.ofString(body)) //
				.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8") //
				.build();

		BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8);
		HttpResponse<String> response = HttpClient //
				.newBuilder() //
				.version(Version.HTTP_1_1) //
				.build() //
				.send(request, bodyHandler);
		var resBody = response.body();
		return resBody;
	}

	protected static String _get(String url) throws IOException, InterruptedException {
		HttpRequest request = HttpRequest //
				.newBuilder(URI.create(url)) //
				.GET() //
				.build();

		BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8);
		HttpResponse<String> response = HttpClient //
				.newBuilder() //
				.version(Version.HTTP_1_1) //
				.build() //
				.send(request, bodyHandler);

		var resbody = response.body();
		return resbody;
	}

	static void debug() {
	}

}

package com.github.sakaguchi3.jbatch002.javaapi;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

//import io.vavr.API.*;
//import static io.vavr.API.*;
//import static io.vavr.API.Match.*;
//import static io.vavr.Predicates.*;
//import io.vavr.collection.*;
//import io.vavr.collection.HashMap.*;
//import io.vavr.collection.HashMultimap.*;
//import io.vavr.collection.HashSet.*;
//import static io.vavr.collection.HashMap.*;
//import static io.vavr.collection.HashMultimap.*;
//import static io.vavr.collection.HashSet.*; 
//import org.apache.logging.log4j.*; 
//import org.junit.jupiter.api.*;
//import static org.junit.jupiter.api.Assertions.*;
//import static java.time.Duration.*;

/**
 * @see https://nompor.com/2019/06/30/post-5303/
 */
public class HttpClientJava11Test {

//	@Test
//	public void aTest() {
//		debug();
//	}

	@Test
	public void postTest() throws IOException, InterruptedException {

		HttpRequest request = HttpRequest //
				.newBuilder(URI.create("http://google.com")) //
				.build();
		BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8);
		HttpResponse<String> response = HttpClient //
				.newBuilder() //
				.build() //
				.send(request, bodyHandler);

		var res = response.body();

		System.out.println(res);

		assertNotNull(res);

		debug();
	}

	void debug() {
	}

}

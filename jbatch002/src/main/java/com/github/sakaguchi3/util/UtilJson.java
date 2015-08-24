package com.github.sakaguchi3.util;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.io.IOException;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.vavr.control.Try;

public class UtilJson {

	// ---------------------------------------------------------------------------------------------
	// - filed
	// ---------------------------------------------------------------------------------------------

	private static final Logger log = LogManager.getLogger();

	private static final ObjectMapper mapper = new ObjectMapper() //
			.setSerializationInclusion(NON_EMPTY) // remove null value
	;

	// ---------------------------------------------------------------------------------------------
	// - public method
	// ---------------------------------------------------------------------------------------------

	public static ObjectMapper getMapper() {
		return mapper;
	}

	public static <T> String toJson(T t) throws JsonProcessingException {
		return mapper.writeValueAsString(t);
	}

	public static <T> Try<String> toJsonTry(T t) {
		var jsonTry = Try.of(() -> toJson(t));
		return jsonTry;
	}

	public static <T> Optional<String> toJsonOptional(T t) {
		try {
			var json = toJson(t);
			return Optional.of(json);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Optional.empty();
		}
	}

	public static <T> T toClass(String json, Class<T> clazz) throws IOException {
		return mapper.readValue(json, clazz);
	}

	public static <T> Try<T> toClassTry(String json, Class<T> clazz) {
		var objTry = Try.of(() -> toClass(json, clazz));
		return objTry;
	}

	public static <T> Optional<T> toClassOptional(String json, Class<T> clazz) {
		try {
			var objectT = toClass(json, clazz);
			return Optional.of(objectT);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return Optional.empty();
		}
	}

	// ---------------------------------------------------------------------------------------------
	// - private method
	// ---------------------------------------------------------------------------------------------

}

package com.github.sakaguchi3.jbatch002.jackson;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperTest {
	private ObjectMapper mapperRemoveNull = null;

	@BeforeEach
	void init() {
		mapperRemoveNull = new ObjectMapper();
		mapperRemoveNull.setSerializationInclusion(JsonInclude.Include.NON_EMPTY); // remove null
	}

	@Test
	public void removeNullTest() throws JsonProcessingException {
		var map = new HashMap<String, Object>();
		map.put("key1", null);
		map.put("key2", "val2");

		var json = mapperRemoveNull.writeValueAsString(map);
		assertEquals("{\"key2\":\"val2\"}", json);

		var mapperNotRemoveNull = new ObjectMapper();
		var jsonBad = mapperNotRemoveNull.writeValueAsString(map);
		assertEquals("{\"key1\":null,\"key2\":\"val2\"}", jsonBad);
		debug();
	}

	@Test
	public void mapper2Test() throws IOException {

		var d1M2 = Map.of(//
				"key3", Map.of("d3", "aaaa"), //
				"key8", List.of(29, 33, 19.9d), //
				"ke9", "ushi", //
				"key2", 3.9f);
		var d0M1 = Map.of(//
				"d1", "dddd", //
				"d0", d1M2);

		var mapper = new ObjectMapper();

		// answer --------

		// {"d0":{"key8":[29,33,19.9],"key2":3.9,"key3":{"d3":"aaaa"},"ke9":"ushi"},"d1":"dddd"}
		var jsonFromMap = mapper.writeValueAsString(d0M1);
		var mapFromJson = mapper.readValue(jsonFromMap, Map.class);

		debug();
	}

	@Test
	public void mapper1Test() throws JsonProcessingException {
		var d2_m2 = Map.of(//
				"k3", Map.of("d3", "aaaa"), //
				"eeee", List.of(29, 33, 19.9d), //
				"j", "ushi", //
				"k3.0d", 3.9f);
		var d1_m1 = Map.of( //
				"keY_k2", d2_m2, //
				"key_k3", Map.of("d2", 2));

		var d0_k0 = Map.of(//
				"m1", 3, //
				"m2", Map.of("key_m2", 2), //
				"m3", d1_m1);

		var json1 = mapperRemoveNull.writeValueAsString(d0_k0);
		debug();
	}

	void debug() {
	}

}

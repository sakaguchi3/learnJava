package com.github.sakaguchi3.jbatch002.jackson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sakaguchi3.util.UtilJson;
import com.google.common.base.MoreObjects;

public class ObjectMapperTest {

	private ObjectMapper mapperRemoveNull = null;

	@BeforeEach
	void init() {
		mapperRemoveNull = new ObjectMapper();
		mapperRemoveNull.setSerializationInclusion(JsonInclude.Include.NON_EMPTY); // remove null
	}

	@Test
	void classToJsonTest() throws IOException {
		var hoge10 = Hoge.of(3);
		var json10 = UtilJson.toJson(hoge10);
		var hoge11 = UtilJson.toClass(json10, Hoge.class);
		assertEquals(3, hoge11.num);
		assertNull(hoge11.str);
		
		var hoge20 = Hoge.of(1, "a");
		var json20 = UtilJson.toJson(hoge20);
		var hoge21 = UtilJson.toClass(json20, Hoge.class);
		assertEquals(1, hoge21.num);
		assertEquals("a", hoge21.str);

		var hoge30 = Hoge.of("s");
		var json30 = UtilJson.toJson(hoge30);
		var hoge31 = UtilJson.toClass(json30, Hoge.class);
		assertEquals("s", hoge31.str);
		assertNull(hoge31.num); 
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

	private static class Hoge {
		public Integer num = null;
		public String str = null;

		public static Hoge of(int num) {
			var a = new Hoge();
			a.num = num;
			return a;
		}

		public static Hoge of(String s) {
			var a = new Hoge();
			a.str = s;
			return a;
		}

		public static Hoge of(int num, String s) {
			var a = new Hoge();
			a.num = num;
			a.str = s;
			return a;
		}

		public String toString() {
			return MoreObjects.toStringHelper(this) //
					.add("num", num) //
					.add("str", str) //
					.toString();
		}
	}
}

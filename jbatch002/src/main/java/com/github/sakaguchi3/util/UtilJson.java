package com.github.sakaguchi3.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class UtilJson {

	private static final ObjectMapper mapper = new ObjectMapper();

	static {
		setJacksonMapperConfig(mapper);
	}

	private static ObjectMapper setJacksonMapperConfig(ObjectMapper mapper) {
		mapper //
				.setSerializationInclusion(JsonInclude.Include.USE_DEFAULTS) //
				.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS) //
				.setSerializationInclusion(JsonInclude.Include.NON_EMPTY) //
				.enable(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS) //
				.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES) //
				.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true) //
				.setVisibility(mapper //
						.getSerializationConfig() //
						.getDefaultVisibilityChecker() //
						.withFieldVisibility(JsonAutoDetect.Visibility.ANY) //
						.withGetterVisibility(JsonAutoDetect.Visibility.NONE) //
						.withSetterVisibility(JsonAutoDetect.Visibility.NONE) //
						.withCreatorVisibility(JsonAutoDetect.Visibility.NONE) //
						.withIsGetterVisibility(JsonAutoDetect.Visibility.NONE));
		return mapper;
	}
}

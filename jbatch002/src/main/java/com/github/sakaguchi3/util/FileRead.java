package com.github.sakaguchi3.util;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileRead {

	// ------------------------------------------------------
	// field
	// ------------------------------------------------------

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	// ------------------------------------------------------
	// public
	// ------------------------------------------------------

	public Optional<String> readResource(String filePath) {

		try {
			URL fileUrl = ClassLoader.getSystemResource(filePath);
			Path localeFilePath = Paths.get(fileUrl.toURI());

			String jsonStr = Files.lines(localeFilePath) //
					.collect(Collectors.joining("\n"));

			return Optional.of(jsonStr);

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage(), e);

			return Optional.empty();
		}

	}

	// ------------------------------------------------------
	// private
	// ------------------------------------------------------

	private void debug() {

	}
}

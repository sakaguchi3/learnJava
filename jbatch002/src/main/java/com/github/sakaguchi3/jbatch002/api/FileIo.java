package com.github.sakaguchi3.jbatch002.api;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileIo {

	// ------------------------------------------------------
	// field
	// ------------------------------------------------------

	private static final Logger LOG = LogManager.getLogger();

	// ------------------------------------------------------
	// public
	// ------------------------------------------------------

	/**
	 */
	public Optional<byte[]> fileAsBytes(String file) {
		try {
			var bytes = _fileAsBytes(file);
			return Optional.ofNullable(bytes);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return Optional.empty();
		}
	}

	public Optional<String> readAsString(String file) {
		try {
			var ret = _fileAsStr(file);
			return Optional.of(ret);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return Optional.empty();
		}
	}

	/**
	 * 
	 * @param filePath
	 * @return
	 */
	public Optional<String> readResources(String filePath) {
		try {
			var jsonStr = _readResources(filePath);
			var jsonOp = Optional.of(jsonStr);
			return jsonOp;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return Optional.empty();
		}

	}

	// ------------------------------------------------------
	// private
	// ------------------------------------------------------

	protected byte[] _fileAsBytes(String file) throws FileNotFoundException, IOException {
		File f = new File(file);
		try (//
				var fis = new FileInputStream(f); //
				var bis = new BufferedInputStream(fis);) {
			byte[] bytes = new byte[bis.available()];
//			var a= bis.readAllBytes();
			bis.read(bytes);
			return bytes;
		}
	}

	protected byte[] _fileAsBytes2(String file) throws FileNotFoundException, IOException {
		File f = new File(file);
		try (//
				var fis = new FileInputStream(f); //
				var bis = new BufferedInputStream(fis);) {
			var bytes = bis.readAllBytes();
			bis.read(bytes);
			return bytes;
		}
	}

	protected String _fileAsStr(String file) throws FileNotFoundException, IOException {
		File f = new File(file);
//		var p = f.toPath();
//		try (var stream = Files.lines(p)) {
//			String jsonStr = stream.collect(Collectors.joining());
//			return jsonStr;
//		}
		try (//
				var fis = new FileInputStream(f); //
				var inr = new InputStreamReader(fis);
				var bis = new BufferedReader(inr); //
				var stream = bis.lines();) {
			return stream.collect(Collectors.joining());
		}
	}

	protected String _readResources(String filePath) throws URISyntaxException, IOException {
		URL fileUrl = ClassLoader.getSystemResource(filePath);
		Path localeFilePath = Paths.get(fileUrl.toURI());

		try (var stream = Files.lines(localeFilePath)) {
			String jsonStr = stream.collect(Collectors.joining());
			return jsonStr;
		}
	}

	void debug() {
	}
}

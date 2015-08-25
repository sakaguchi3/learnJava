package com.github.sakaguchi3.util;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilFile {

	// ------------------------------------------------------
	// field
	// ------------------------------------------------------

	private static final Logger LOG = LogManager.getLogger();

	// ------------------------------------------------------
	// public
	// ------------------------------------------------------

	/**
	 */
	public static Optional<byte[]> readBytes(String fileFullPath) {
		try {
			var bytes = _readByte(fileFullPath);
			return Optional.ofNullable(bytes);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return Optional.empty();
		}
	}

	public static Optional<String> readString(String fileFullPath) {
		try {
			var ret = _readString(fileFullPath);
			return Optional.of(ret);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return Optional.empty();
		}
	}

	/**
	 * 
	 * @param filename
	 * @return
	 */
	public static Optional<String> readStrFromResource(String filename) {
		try {
			var jsonStr = _readStrFromResource(filename);
			var jsonOp = Optional.of(jsonStr);
			return jsonOp;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return Optional.empty();
		}
	}

	public static boolean writeStr(String fileName, String msg, boolean append) {
		try {
			List<OpenOption> ops = append ? List.of(APPEND) : emptyList();
			_writeStr(fileName, msg, ops);
			return true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}

	public static boolean writeBytes(String filePath, byte[] b, boolean append) {
		try {
			_writeBytes(filePath, b, append);
			return true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
	}

	// ------------------------------------------------------
	// private
	// ------------------------------------------------------

	protected static byte[] _readByte(String file) throws FileNotFoundException, IOException {
		File f = new File(file);
		try (//
				var fis = new FileInputStream(f);
				var bis = new BufferedInputStream(fis);) {
			var bytes = bis.readAllBytes();
			return bytes;
		}
	}

	protected static byte[] _fileAsBytes2(String file) throws FileNotFoundException, IOException {
		File f = new File(file);
		try (//
				var fis = new FileInputStream(f);
				var bis = new BufferedInputStream(fis);) {
			var bytes = bis.readAllBytes();
			bis.read(bytes);
			return bytes;
		}
	}

	protected static String _readString(String file) throws FileNotFoundException, IOException {
		File f = new File(file);
		try (//
				var fis = new FileInputStream(f);
				var inr = new InputStreamReader(fis);
				var bis = new BufferedReader(inr);
				var stream = bis.lines();) {
			return stream.collect(Collectors.joining());
		}
	}

	protected static String _readStrFromResource(String filePath) throws URISyntaxException, IOException {
		URL fileUrl = ClassLoader.getSystemResource(filePath);
		Path localeFilePath = Paths.get(fileUrl.toURI());

		try (var stream = Files.lines(localeFilePath)) {
			// save line code
			var str = stream.collect(Collectors.joining("\n"));
			return str;
		}
	}

	protected static void _writeStr(String fileName, String msg, List<? extends OpenOption> ops) throws URISyntaxException, IOException {
		var pf = Paths.get(fileName);
		var opAllLst = Stream.concat(ops.stream(), Stream.of(CREATE)).collect(toList());
		var opAllArry = new OpenOption[opAllLst.size()];
		opAllLst.toArray(opAllArry);
		try (var bw = Files.newBufferedWriter(pf, opAllArry);) {
			bw.write(msg);
		}
	}

	protected static void _writeBytes(String filePath, byte[] b, boolean append) throws FileNotFoundException, IOException {
		try (//
				var fo = new FileOutputStream(filePath, append);
				var bo = new BufferedOutputStream(fo);) {
			bo.write(b);
			bo.flush();
		}
	}

	static void debug() {
	}
}

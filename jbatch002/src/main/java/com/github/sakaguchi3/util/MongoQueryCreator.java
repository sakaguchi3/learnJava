package com.github.sakaguchi3.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;

import io.vavr.Function2;

public class MongoQueryCreator {

	// ------------------------------------------------------
	// field
	// ------------------------------------------------------

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	// ------------------------------------------------------
	// public
	// ------------------------------------------------------

	/**
	 * write str to filePath.
	 * 
	 * @param filePath
	 */
	public void write(String filePath) {

		var medias = List.of( //
				"xxx.com", //
				"yyy.jp" //
		);
		var aAllow = List.of("1");
		var bAllow = List.of(//
				"509", //
				"303");
		var allowSeatMap2 = Map.of(//
				"ushi", aAllow, //
				"neko", bAllow //
		);

		File file = new File(filePath);
		debug();

		try (//
				FileWriter fw = new FileWriter(file, true); //
				BufferedWriter bw = new BufferedWriter(fw); //
				PrintWriter pw = new PrintWriter(bw)) {

			for (var media : medias) {
				pw.println();
				pw.println("/** ------------ " + media + " ------------ */");
				pw.println();

				// fun: ((String, String) => String) = (dsp, seatid) => (query)
				Function2<String, List<String>, String> seatToQuery = (dsp, _seatid) -> createQuery(media, dsp, _seatid);
				var query = allowSeatMap2.entrySet().stream()//
						.map(e -> seatToQuery.apply(e.getKey(), e.getValue())) //
						.collect(Collectors.joining("\n"));
				pw.println(query);
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage(), e);
		}

	}

	// ------------------------------------------------------
	// private
	// ------------------------------------------------------

	protected String createQuery(String media, String dsp, List<String> seatIds) {
		var _seatIds = seatIds.stream()//
				.map(v -> "\"" + v + "\"") //
				.collect(Collectors.joining(","));
		var ret = "" //
				+ "db.getCollection(\"media_allowed_seat\").insert({\n" + //
				"  \"media\": \"" + media + "\",\n" + //
				"  \"seat\": {\n" + //
				"    \"" + dsp + "\": [  \n" + //
				"      " + _seatIds + "  \n" + //
				"    ]\n" + //
				"  }\n" + //
				"});\n";
		return ret;
	}

	private void debug() {

	}
}

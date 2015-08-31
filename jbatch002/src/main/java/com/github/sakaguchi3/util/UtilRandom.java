package com.github.sakaguchi3.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UtilRandom {

	// ---------------------------------------------------------------------------------------------
	// - field
	// ---------------------------------------------------------------------------------------------

	// rand
	private static final ThreadLocalRandom RAND = ThreadLocalRandom.current();

	// ---------------------------------------------------------------------------------------------
	// - public method
	// ---------------------------------------------------------------------------------------------

	public static Random getRandom() {
		return RAND;
	}

	public static double randDouble() {
		return RAND.nextDouble();
	}

	public static double randDouble(double bound) {
		return RAND.nextDouble(bound);
	}

	public static int randInt() {
		return RAND.nextInt();
	}

	public static int randInt(int bound) {
		return RAND.nextInt(bound);
	}

	/** [A-Za-z] make random alphabet with standard library */
	public static String randAlphabetic(int len) {
		final var alphabetUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		final var alphabetFull = alphabetUpperCase + alphabetUpperCase.toLowerCase();
		final var stringLen = alphabetFull.length();

		var sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			var index = RAND.nextInt(stringLen);
			var c = alphabetFull.charAt(index);
			sb.append(c);
		}
		return sb.toString();
	}

	/** [A-Za-z0-9] make random alphabet and numeric with standard library */
	public static String randomAlphaNumeric(int len) {
		final var alphabetUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		final var alphabetFull = alphabetUpperCase + alphabetUpperCase.toLowerCase() + "0123456789";
		final var stringLen = alphabetFull.length();

		var str = IntStream.range(0, len) //
				.map(__ -> RAND.nextInt(stringLen)) //
				.mapToObj(_index -> alphabetFull.charAt(_index)) //
				.map(String::valueOf) //
				.collect(Collectors.joining());
		return str;
	}

	/**
	 * 一定の確率でtrueを返す。
	 */
	public static boolean randomTrue(double percent) {
		if (100 <= percent) {
			return true;
		}
		if (percent <= 0) {
			return false;
		}
		var randNum = RAND.nextDouble(100);
		return (randNum <= percent);
	}

	// ---------------------------------------------------------------------------------------------
	// - private method
	// ---------------------------------------------------------------------------------------------

}

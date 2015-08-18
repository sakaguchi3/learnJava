package com.github.sakaguchi3.util;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.RandomStringUtils;

public class UtilRandom {

	// ---------------------------------------------------------------------------------------------
	// - field
	// ---------------------------------------------------------------------------------------------

	// rand
	private static final ThreadLocalRandom RAND = ThreadLocalRandom.current();

	private static DoubleSupplier randDouble = () -> Math.random();

	private static IntSupplier randInt = () -> RAND.nextInt();

	private static Supplier<String> randStr = () -> RandomStringUtils.randomAlphabetic(16);

	// ---------------------------------------------------------------------------------------------
	// - public method
	// ---------------------------------------------------------------------------------------------

	public static double randDouble() {
		double d = randDouble.getAsDouble();
		return d;
	}

	public static int randInt() {
		int i = randInt.getAsInt();
		return i;
	}

	public static String randStr() {
		String s = randStr.get();
		return s;
	}

	/** make random alphabet with apache library */
	public static String randomAlphabetic(int len) {
		return RandomStringUtils.randomAlphabetic(len);
	}

	/** make random alphabet with standard library */
	public static String randomAlphabetic2(int len) {
		var alphabetLow = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		var alphabetFull = alphabetLow + alphabetLow.toLowerCase() + "0123456789";

		var str = IntStream.range(0, len) //
				.map(__ -> RAND.nextInt(alphabetFull.length())) //
				.mapToObj(_index -> alphabetFull.charAt(_index)) //
				.map(String::valueOf) //
				.collect(Collectors.joining()); 
		return str;
	}

	/**
	 * 一定の確率でtrueを返す。
	 */
	public static boolean randomTrue(int percent) {
		if (100 <= percent) {
			return true;
		}
		if (percent <= 0) {
			return false;
		}
		// [0, 100]で考える
		final int randInt = RAND.nextInt(100);
		return (randInt <= percent);
	}

	// ---------------------------------------------------------------------------------------------
	// - private method
	// ---------------------------------------------------------------------------------------------

}

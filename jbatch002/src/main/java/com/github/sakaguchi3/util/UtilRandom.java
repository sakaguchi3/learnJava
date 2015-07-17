package com.github.sakaguchi3.util;

import java.security.SecureRandom;
import java.util.Random;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

import org.apache.commons.lang3.RandomStringUtils;

public class UtilRandom {

	private static Random r = new SecureRandom();

	private static DoubleSupplier randDouble = () -> Math.random();

	private static IntSupplier randInt = () -> r.nextInt();

	private static Supplier<String> randStr = () -> RandomStringUtils.randomAlphabetic(16);

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

}

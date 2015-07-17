package com.github.sakaguchi3.util;

import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.vavr.control.Try;

public class Util {

	// rand
	private static final ThreadLocalRandom RAND = ThreadLocalRandom.current();

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

	public static String toString(Object obj) {

		return Try.of(() -> ToStringBuilder.reflectionToString(obj, ToStringStyle.SHORT_PREFIX_STYLE)) //
				.getOrElse("");

	}

	public static boolean isIpv4(String ip) {
		boolean r = Pattern.matches("((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])([.](?!$)|$)){4}", ip);
		return r;
	}
}

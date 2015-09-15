package com.github.sakaguchi3.util;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.util.regex.Pattern;

import io.vavr.control.Try;

public class Util {

	// ---------------------------------------------------------------------------------------------
	// - public method
	// ---------------------------------------------------------------------------------------------

	public static String toString(Object obj) {
		return Try.of(() -> reflectionToString(obj, SHORT_PREFIX_STYLE)) //
				.getOrElse("toString err.");
	}

	public static boolean isIpv4(String ip) {
//		var ipv4Pattern = "(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])";
//		var ipv4Pattern = "^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$";
		var ipv4Pattern = "((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])([.](?!$)|$)){4}";
		boolean r = Pattern.matches(ipv4Pattern, ip);
		return r;
	}

	public static boolean isIpv6(String ip) {
		var ipv6Pattern = "([0-9a-f]{1,4}:){7}([0-9a-f]){1,4}";
		boolean r = Pattern.matches(ipv6Pattern, ip);
		return r;
	}

	// ---------------------------------------------------------------------------------------------
	// - private method
	// ---------------------------------------------------------------------------------------------

}

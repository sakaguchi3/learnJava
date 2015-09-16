/**
 * Copyright 2020 sakaguchi<uqw@outlook.jp>, https://github.com/sakaguchi3/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.sakaguchi3.util;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;

import io.vavr.control.Try;

public class Util {

	// ---------------------------------------------------------------------------------------------
	// - public method
	// ---------------------------------------------------------------------------------------------

	// ----------

	public static String toString(Object obj) {
		return Try.of(() -> reflectionToString(obj, SHORT_PREFIX_STYLE)) //
				.getOrElse("toString err.");
	}

	// date -----

	public static long unixtime() {
		return System.currentTimeMillis() / 1000;
	}

	public static long unixtime(Date d) {
		return d.getTime() / 1000;
	}

	public static Date fromUnixtimeToDate(long unixtime) {
		return new Date(unixtime * 1000);
	}

	public static LocalDateTime now() {
		return LocalDateTime.now();
	}

	public static String toDateStr(LocalDateTime ld, String fmt) {
		var f = DateTimeFormatter.ofPattern(fmt);
		return ld.format(f);
	}

	/**
	 * @param date:yyyy/MM/dd HH:mm:ss
	 * @param fmt:yyyy/MM/dd  HH:mm:ss
	 */
	public static LocalDateTime toLocaleDate(String date, String fmt) {
		var f = DateTimeFormatter.ofPattern(fmt);
		var d = LocalDateTime.parse(date, f);
		return d;
	}

	public static LocalDateTime toLocaleDate(Date date) {
		var zone = ZoneId.systemDefault();
		var instance = date.toInstant();
		var ldt = LocalDateTime.ofInstant(instance, zone);
		return ldt;
	}

	public static Date toDate(LocalDateTime ldt) {
		var instance = ldt.atZone(ZoneId.systemDefault()).toInstant();
		var date = Date.from(instance);
		return date;
	}

	// network -----

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

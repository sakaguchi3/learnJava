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

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.Strings;

import io.vavr.control.Try;

public class DiscSpace {

	// ------------------------------------------------------
	// field
	// ------------------------------------------------------

	protected static final Logger LOG = LogManager.getLogger();

	// const ---

	/** giga byte */
	protected final double GB = Math.pow(1024, 3); // 1024*1024*1024
	/** mega byte */
	protected final double MB = Math.pow(1024, 2); // 1024*1024
	/** */
	protected final int ALERT_RATE = 15;

	// ----

	// ------------------------------------------------------
	// public
	// ------------------------------------------------------

	public String calcDiskSize(String path) {

		if (Strings.isNullOrEmpty(path)) {
			String msg = "Not exsist. PATH:=" + path + "\n";
			return msg;
		}

		final File file = new File(path);
		if (!file.exists()) {
			String msg = "Not exsist. PATH:=" + path + "\n";
			return msg;
		}

		final StringBuilder sb = new StringBuilder();

		// total disk space in bytes.
		long totalSpace = file.getTotalSpace();
		// unallocated/free disk space in bytes.
		long usableSpace = file.getUsableSpace();
		// unallocated/free disk space in bytes.
		long freeSpace = file.getFreeSpace();

		sb.append(" === Partition Detail ===\n");
		sb.append(" === bytes ===\n");
		sb.append("Total size : ").append(totalSpace).append(" bytes\n");
		sb.append("Space free : ").append(usableSpace).append(" bytes\n");
		sb.append("Space free : ").append(freeSpace).append(" bytes\n");

		sb.append(" === mega bytes ===\n");
		sb.append("Total size : ").append(totalSpace / MB).append(" mb\n");
		sb.append("Space free : ").append(usableSpace / MB).append(" mb\n");
		sb.append("Space free : ").append(freeSpace / MB).append(" mb\n");
		sb.append("Space free : ").append(freeSpace / MB).append(" mb\n");

		sb.append(" === giga bytes ===\n");
		sb.append("Total size : ").append(totalSpace / GB).append(" gb\n");
		sb.append("Space free : ").append(usableSpace / GB).append(" gb\n");
		sb.append("Space free : ").append(freeSpace / GB).append(" gb\n");

		double freeSizeRate = 100.0d * usableSpace / totalSpace;

		var bdTry = Try.of(() -> BigDecimal.valueOf(freeSizeRate)) //
				.map(b -> b.setScale(5, RoundingMode.UP)) //
				.onFailure(v -> LOG.error(v.getMessage(), v));

		if (bdTry.isFailure()) {
			var str = sb.toString();
			return str;
		}

		var bd = bdTry.get();

		sb.append(" === percent ===\n");
		sb.append("Disk available(%) : ").append(bd).append("\n");

		sb.append(" === note ===\n");

		if (bd.intValue() < ALERT_RATE) {
			sb.append("Not enough space!!\n");
		} else {
			sb.append("Enough space.\n");
		}

		var str = sb.toString();
		return str;

	}

	// ------------------------------------------------------
	// private
	// ------------------------------------------------------

}

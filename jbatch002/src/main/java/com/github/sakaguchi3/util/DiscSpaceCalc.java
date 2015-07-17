package com.github.sakaguchi3.util;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

import io.vavr.control.Try;

public class DiscSpaceCalc {

	// ------------------------------------------------------
	// field 
	// ------------------------------------------------------

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	// const ---

	/** giga byte */
	protected final double GB = Math.pow(1024, 3); // 1024*1024*1024
	/** mega byte */
	protected final double MB = Math.pow(1024, 2); // 1024*1024
	/** */
	protected final int ALERT_RATE = 15;

	// ----

	// Function ----

	/** */
	protected final StringBuilder sb = new StringBuilder();

	final String ERR_MSG = "Not exsist. PATH:=";

	// ------------------------------------------------------
	// public 
	// ------------------------------------------------------

	public String calcDiskSize(String path) {

		if (Strings.isNullOrEmpty(path)) {
			String msg = ERR_MSG + path + "\n";
			return msg;
		}

		final File file = new File(path);
		if (!file.exists()) {
			String msg = ERR_MSG + path + "\n";
			return msg;
		}

		// clear
		sb.setLength(0);

		// total disk space in bytes.
		long totalSpace = file.getTotalSpace();
		// unallocated/free disk space in bytes.
		long usableSpace = file.getUsableSpace();
		// unallocated/free disk space in bytes.
		long freeSpace = file.getFreeSpace();

		sb.append(" === Partition Detail ===\n");
		sb.append(" === bytes ===\n");
		sb.append("Total size : " + totalSpace + " bytes\n");
		sb.append("Space free : " + usableSpace + " bytes\n");
		sb.append("Space free : " + freeSpace + " bytes\n");

		sb.append(" === mega bytes ===\n");
		sb.append("Total size : " + totalSpace / MB + " mb\n");
		sb.append("Space free : " + usableSpace / MB + " mb\n");
		sb.append("Space free : " + freeSpace / MB + " mb\n");

		sb.append(" === giga bytes ===\n");
		sb.append("Total size : " + totalSpace / GB + " gb\n");
		sb.append("Space free : " + usableSpace / GB + " gb\n");
		sb.append("Space free : " + freeSpace / GB + " gb\n");

		double freeSizeRate = 100.0d * usableSpace / totalSpace;

		var bdTry = Try.of(() -> BigDecimal.valueOf(freeSizeRate).setScale(5, RoundingMode.UP)) //
				.onFailure(v -> LOGGER.error(v.getMessage(), v));

		if (bdTry.isFailure()) {
			var str = sb.toString();
			return str;
		}

		var bd = bdTry.get();

		sb.append(" === percent ===\n");
		sb.append("Disk available(%) : " + bd + "\n");

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

	private void debug() {

	}
}

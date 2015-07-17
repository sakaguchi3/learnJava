package com.github.sakaguchi3.jbatch002.s;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Test;

public class DiskSizeTest {

	/** giga byte */
	final double giga = Math.pow(1024, 3); // 1024*1024*1024
	/** mega byte */
	final double mega = Math.pow(1024, 2); // 1024*1024

	/**
	 * show display
	 */
	@Test
	public void t02Test() {

		String dir = "c:";
		File file = new File(dir);

		// total disk space in bytes.
		long totalSpace = file.getTotalSpace();
		// unallocated/free disk space in bytes.
		long usableSpace = file.getUsableSpace();
		// unallocated/free disk space in bytes.
		long freeSpace = file.getFreeSpace();
		System.out.println(" === Partition Detail ===");

		System.out.println(" === bytes ===");
		System.out.println("Total size : " + totalSpace + " bytes");
		System.out.println("Space free : " + usableSpace + " bytes");
		System.out.println("Space free : " + freeSpace + " bytes");

		System.out.println(" === mega bytes ===");
		System.out.println("Total size : " + totalSpace / mega + " mb");
		System.out.println("Space free : " + usableSpace / mega + " mb");
		System.out.println("Space free : " + freeSpace / mega + " mb");

		double usableSpacePercent = 100.0d * usableSpace / totalSpace;

		BigDecimal bd = BigDecimal.valueOf(usableSpacePercent);
		BigDecimal bdScaled = bd.setScale(5, RoundingMode.UP);

		System.out.println(" === percent ===");
		System.out.println("Disk usage(%) : " + bdScaled);

		System.out.println(" === note ===");
		if (bdScaled.intValue() > 85) {
			System.out.println("Not enough disk space!!");
		} else {
			System.out.println("Enough disk space.");
		}
	}

	private void debug() {

	}

}

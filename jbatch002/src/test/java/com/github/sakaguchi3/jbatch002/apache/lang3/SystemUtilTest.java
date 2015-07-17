package com.github.sakaguchi3.jbatch002.apache.lang3;

import org.apache.commons.lang3.JavaVersion;
import org.apache.commons.lang3.SystemUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

public class SystemUtilTest {

	private final Logger LOG = LogManager.getLogger();

	@Test
	public void Test() {
		var s = new SystemUtils();

		var envKey = s.getEnvironmentVariable("key", "default"); // default
		var host = s.getHostName(); // ZEUS
		var javaHome = s.getJavaHome(); // C:/p/openjdk/jdk-11
		var tmpDir = s.getJavaIoTmpDir(); // C:\Users\myname\AppData\Local\Temp
		var userDir = s.getUserDir(); // F:\dir\develop\program\git\xxx
		var userHome = s.getUserHome(); // C:\Users\myname
		var awtHead = s.isJavaAwtHeadless(); // false
		var bJavaVersion = s.isJavaVersionAtLeast(JavaVersion.JAVA_1_8); // true
		var bLinux = s.IS_OS_LINUX;
		var bWin = s.IS_OS_WINDOWS_10;

		debug();
	}

	void debug() {

	}

}

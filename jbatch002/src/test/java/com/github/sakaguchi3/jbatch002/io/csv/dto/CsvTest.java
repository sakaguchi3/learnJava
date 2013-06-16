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
package com.github.sakaguchi3.jbatch002.io.csv.dto;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.github.sakaguchi3.jbatch002.util.PropConfig;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

public class CsvTest {
	protected static final Logger LOG = LogManager.getLogger();

	@Test
	void writeTest() {

		var items = IntStream.range(0, 10) //
				.map(v -> v + 1) //
				.mapToObj(v -> {
					var e = new CsvItemEntity();
					e.setId("id_" + v);
					e.setName("name_a_,_\\_" + v);
					e.setAmount(v + 10);
					e.setPrice(v * 100);
					return e;
				}) //
				.collect(Collectors.toList());

		var df = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_[SSS]");

		final var fileNameTmp = "output.tmp";
		final var fileNameCsv = "output";
		final var fileNameExtension = ".csv";

		try {
			var prop = PropConfig.getInstance().get();

//			File logDirFile = new File(prop.getCsvOutputPath());
//			if (!logDirFile.exists()) {
//				logDirFile.mkdirs();
//			} else if (!logDirFile.isDirectory()) {
//				fail("not directory");
//			}

			var p1 = Paths.get(prop.getCsvOutputPath());
			if (!Files.exists(p1, LinkOption.NOFOLLOW_LINKS)) {
				Files.createDirectories(p1);
			} else if(!Files.isDirectory(p1, LinkOption.NOFOLLOW_LINKS)) {
				fail("not directory");
			}

			var pathOld = Paths.get(prop.getCsvOutputPath() + fileNameTmp);
			try (var w = Files.newBufferedWriter(pathOld)) {
				StatefulBeanToCsv<CsvItemEntity> beanToCsv = new StatefulBeanToCsvBuilder<CsvItemEntity>(w).build();
				beanToCsv.write(items);
			}
			var pathNew = Paths.get(prop.getCsvOutputPath() + fileNameCsv + LocalDateTime.now().format(df) + fileNameExtension);
//			var pathNew = Paths.get(prop.getCsvOutputPath() + "output.csv");
			Files.move(pathOld, pathNew, StandardCopyOption.REPLACE_EXISTING);

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		nothing();
	}

	static void nothing() {
	}
}

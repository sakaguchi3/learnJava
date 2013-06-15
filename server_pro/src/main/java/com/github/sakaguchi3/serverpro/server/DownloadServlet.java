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
package com.github.sakaguchi3.serverpro.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sakaguchi3.serverpro.config.ResourceManager;

import io.vavr.Tuple;
import io.vavr.Tuple2;

/**
 * 
 */
public class DownloadServlet extends HttpServlet {

	// ---------------------------------------------------------
	// field
	// ---------------------------------------------------------

	/** */
	private static final long serialVersionUID = 8907340394573966L;
	/** Log instance */
	private static final Logger log = LogManager.getLogger();

	private static final ObjectMapper MAPPER = ResourceManager.getInstance().get().getObjectMapper();

//	private final String RES_CONTENT_TYPE = "application/zip";
//	private final String RES_CONTENT_TYPE = "text/csv";
	private final String RES_CONTENT_TYPE = "text/plain";
//	private final String RES_HEADER = "attachment; filename=sample.zip";
//	private final String RES_HEADER_DISPOSITION = "attachment; filename=\"sample.txt\"";
	private final Tuple2<String, String> RES_HEADER_DISPOSITION = Tuple.of("Content-Disposition", "attachment; filename=\"sample.txt\"");

	private String RES_DATA;

	// ---------------------------------------------------------
	// http method
	// ---------------------------------------------------------

	public void init() throws ServletException {
		super.init();
		try {
			var record = Map.of("id", 10, "user", "sample user");
			var resMap = Map.of("status", 200, "record", record);
			this.RES_DATA = MAPPER.writeValueAsString(resMap);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServletException(e);
		}
	}

	/** get */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) {
		// response
		res.setContentType(RES_CONTENT_TYPE);
		res.setHeader(RES_HEADER_DISPOSITION._1(), RES_HEADER_DISPOSITION._2());

		try (//
				var os = res.getOutputStream();
				var bos = new BufferedOutputStream(os);) {
			bos.write(RES_DATA.getBytes());
		} catch (IOException e1) {
			log.info(e1.getMessage(), e1);
			try {
				res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			} catch (Exception e2) {
				log.error(e2.getMessage(), e2);
			}
		}
	}

	// ---------------------------------------------------------
	// private method
	// ---------------------------------------------------------

	void debug() {
	}

}

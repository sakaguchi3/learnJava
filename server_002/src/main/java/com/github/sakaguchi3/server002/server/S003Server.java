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
package com.github.sakaguchi3.server002.server;

import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 */
public class S003Server extends HttpServlet {

	// ---------------------------------------------------------
	// field
	// ---------------------------------------------------------

	/** */
	private static final long serialVersionUID = 8907340394573966L;

	/** Log instance */
	protected static final Logger LOG = LogManager.getLogger();

	// ---------------------------------------------------------
	// public method
	// ---------------------------------------------------------

	// ---------------------------------------------------------
	// private method
	// ---------------------------------------------------------

	/**
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest httpReq, HttpServletResponse httpRes) {
		LOG.trace("get");

		// request ----

		var reqOp = getResp(httpReq);
		if (reqOp.isEmpty()) {
			try {
				httpRes.sendError(SC_INTERNAL_SERVER_ERROR);
			} catch (Exception e) {
				LOG.error(e.getMessage());
			}
			return;
		}

		// response -----

		try (var w = httpRes.getWriter()) {
			w.append("get response");
			w.flush();
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
	}

	/** */
	@Override
	protected void doPost(HttpServletRequest httpReq, HttpServletResponse httpRes) {
		LOG.trace("post");

		// request ----

		var reqOp = getResp(httpReq);
		if (reqOp.isEmpty()) {
			try {
				httpRes.sendError(SC_INTERNAL_SERVER_ERROR);
			} catch (Exception e) {
				LOG.error(e.getMessage());
			}
			return;
		}

		var reqStr = reqOp.get();
		LOG.trace("{}", reqStr);

		// response ----

		try (var w = httpRes.getWriter()) {
			w.append("post response");
			w.flush();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}

	}

	/** */
	Optional<String> getResp(HttpServletRequest httpReq) {
		try (//
				BufferedReader br = httpReq.getReader(); //
				Stream<String> stream = br.lines();) {

			httpReq.setCharacterEncoding("UTF-8");
			var resStr = stream.reduce("", String::concat);
			return Optional.of(resStr);
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	void debug() {
	}

}

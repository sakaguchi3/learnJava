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

import java.io.IOException;
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
public class TokenServlet extends HttpServlet {

	// ---------------------------------------------------------
	// field
	// ---------------------------------------------------------

	/** */
	private static final long serialVersionUID = 8907340394573966L;
	/** Log instance */
	private static final Logger log = LogManager.getLogger();

	private static final ObjectMapper MAPPER = ResourceManager.getInstance().get().getObjectMapper();

	private final String APPLICATION_JSON = "application/json";

	private String RES_ERR_JSON;

	// ---------------------------------------------------------
	// http method
	// ---------------------------------------------------------

	public void init() throws ServletException {
		super.init();
		try {
			var errMap = Map.of("status", 500, "msg", "err");
			this.RES_ERR_JSON = MAPPER.writeValueAsString(errMap);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServletException(e);
		}
	}

	/** get */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) {
		// response
		res.setContentType(APPLICATION_JSON);
		res.setCharacterEncoding("utf-8");

		try (var w = res.getWriter()) {
			var resultTup = response(req);
			w.append(resultTup._2());
			res.setStatus(resultTup._1());
			w.flush();
		} catch (IOException e1) {
			log.info(e1.getMessage(), e1);
		}
	}

	// ---------------------------------------------------------
	// private method
	// ---------------------------------------------------------

	protected Tuple2<Integer, String> response(HttpServletRequest req) {
		// check

		var b1 = Optional.ofNullable(req.getHeader("Accept")) //
				.filter(s -> s.contains(APPLICATION_JSON)) //
				.isPresent();
		var b2 = Optional.ofNullable(req.getParameter("grant_type")) //
				.filter(s -> s.equals("client_credentials")) //
				.isPresent();

		var path = req.getPathInfo();
		// github
		var token = req.getHeader("Authorization");

		// basic
		var userPrincipal = req.getUserPrincipal();
		var remoteUser = req.getRemoteUser();
		var authTye = req.getAuthType();

		// request fail ----

		if (!b1 || !b2) {
			return Tuple.of(HttpServletResponse.SC_BAD_REQUEST, RES_ERR_JSON);
		}

		// resposne success ----
		var resMap = Map.of("access_token", "secret_hogehoge");

		try {
			var resJson = MAPPER.writeValueAsString(resMap);
			return Tuple.of(HttpServletResponse.SC_OK, resJson);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			// internal err
			return Tuple.of(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, RES_ERR_JSON);
		}
	}

	void debug() {
	}

}

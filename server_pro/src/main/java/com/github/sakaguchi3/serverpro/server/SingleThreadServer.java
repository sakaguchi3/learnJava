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

import static java.util.Collections.emptyMap;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Semaphore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sakaguchi3.serverpro.api.Datasource;
import com.github.sakaguchi3.serverpro.db.DummyTableDto;

/**
 * 
 */
public class SingleThreadServer extends HttpServlet {

	// ---------------------------------------------------------
	// field
	// ---------------------------------------------------------

	/** */
	private static final long serialVersionUID = 8907340394573966L;
	/** Log instance */
	protected static final Logger LOG = LogManager.getLogger();

	protected static final ObjectMapper MAPPER = (new ObjectMapper()) //
			.setSerializationInclusion(JsonInclude.Include.NON_EMPTY); // remove null

	/** */
	protected Datasource db = new Datasource();

	// ---------------------------------------------------------
	// public method
	// ---------------------------------------------------------

	// ---------------------------------------------------------
	// private method
	// ---------------------------------------------------------

	/** get */
	@Override
	protected void doGet(HttpServletRequest httpReq, HttpServletResponse httpRes) {

		Optional<DummyTableDto> dto = selectUserInfoWhereId(4);
		var pt = dto.map(v -> v.getPoint());
		var name = dto.map(v -> v.getUserName());

		var resMap = Map.of("username", name, "point", pt);

		// resposne ----

		try (var w = httpRes.getWriter()) {
			var resJson = MAPPER.writeValueAsString(resMap);
			w.append(resJson);
			w.flush();
		} catch (IOException e1) {
			LOG.info(e1.getMessage(), e1);
			try {
				httpRes.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			} catch (Exception e2) {
				LOG.info(e2.getMessage(), e2);
			}
		}
	}

	/** post */
	@Override
	protected void doPost(HttpServletRequest httpReq, HttpServletResponse httpRes) {

		db.insert(emptyMap());

		var resMap = Map.of("status", 200);

		// response ----
		try (var w = httpRes.getWriter()) {
			var resJson = MAPPER.writeValueAsString(resMap);
			w.append(resJson);
			w.flush();
		} catch (IOException e1) {
			LOG.info(e1.getMessage(), e1);
			try {
				httpRes.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			} catch (Exception e2) {
				LOG.info(e2.getMessage(), e2);
			}
		}
	}

	/** */
	@Override
	protected void doPut(HttpServletRequest httpReq, HttpServletResponse httpRes) {
		var reqMap = Map.<String, Object>of("id", 4);
		Semaphore semaphore = new Semaphore(1);

		// critical section
		db.update(reqMap);

		semaphore.release();

		Optional<DummyTableDto> dto = selectUserInfoWhereId(4);
		var resMap = dto //
				.map(v -> Map.of("username", v.getUserName(), "point", v.getPoint())) //
				.orElse(emptyMap());

		// response ----
		try (var w = httpRes.getWriter()) {
			var resJson = MAPPER.writeValueAsString(resMap);
			w.append(resJson);
			w.flush();
		} catch (IOException e1) {
			LOG.info(e1.getMessage(), e1);
			try {
				httpRes.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
			} catch (Exception e2) {
				LOG.info(e2.getMessage(), e2);
			}
		}
	}

	/**  */
	@Override
	protected void doDelete(HttpServletRequest httpReq, HttpServletResponse httpRes) {
		// response ----
		// TODO: response
		try (var w = httpRes.getWriter()) {
			httpRes.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	Optional<DummyTableDto> selectUserInfoWhereId(int id) {
		var ret = db.selectWhereId(id);
		return ret;
	}

	List<DummyTableDto> selectUserInfoWhereId(List<Integer> id) {
		var ret = db.selectWhereId(id);
		return ret;
	}

	void debug() {
	}

}

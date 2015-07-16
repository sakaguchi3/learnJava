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

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;

/**
 * Send BidRequest and Receive BidResponse.
 * 
 * @author sakaguchi
 */
public class S002Server extends HttpServlet {

	// ---------------------------------------------------------
	// field
	// ---------------------------------------------------------

	/** */
	private static final long serialVersionUID = 518900544211491462L;
	/** Log instance */
	static final Logger LOG = LogManager.getLogger();
	/** */
	String ngJson;
	/** */
	String okJson;

	// ---------------------------------------------------------
	// public method
	// ---------------------------------------------------------

	public void init() throws ServletException {
		super.init();

		var okNode = ImmutableMap.<String, String>builder()//
				.put("country", "jp") //
				.put("capital", "tokyo") //
				.build();
		var okNode2 = ImmutableMap.<String, Object>builder()//
				.put("location", okNode) //
				.put("ip", "localhost") //
				.build();
		var okPayload = ImmutableMap.<String, Object>builder()//
				.put("status", "ok") //
				.put("msg", "server002 ccc") //
				.put("node", okNode2) //
				.build();

		var errNode = ImmutableMap.<String, Object>builder()//
				.put("location", "Authorization") //
				.put("domain", "global") //
				.build();
		var ngPayload = ImmutableMap.<String, Object>builder()//
				.put("error", errNode) //
				.put("code", 401) //
				.put("msg", "aaaaaaaaaaaa") //
				.build();

		try {
			ObjectMapper mapper = new ObjectMapper();
			okJson = mapper.writeValueAsString(okPayload);
			ngJson = mapper.writeValueAsString(ngPayload);

			debug();
		} catch (JsonProcessingException e) {
			LOG.error(e.getMessage(), e);
			throw new ServletException();
		}
	}

	/**
	 */
	@Override
	protected void doGet(HttpServletRequest httpReq, HttpServletResponse httpRes) throws IOException {
		LOG.debug("get");

		httpRes.setContentType("application/json; charset=utf8");
		httpRes.setCharacterEncoding("utf8");

		try {
			httpRes.getWriter().print(okJson);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);

			throw e;
		}

		debug();
	}

	/**
	 */
	@Override
	public void doPost(HttpServletRequest httpReq, HttpServletResponse httpRes) throws IOException {
		LOG.debug("post -------------------");

		httpRes.setContentType("application/json; charset=utf8");
		httpRes.setCharacterEncoding("utf8");

		try {
			httpRes.getWriter().print(okJson);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw e;
		}

		debug();

	}

	@Override
	public void doDelete(HttpServletRequest httpReq, HttpServletResponse httpRes) throws IOException {
		String msg = "ccc. delete.";
		LOG.debug(msg + "-------------------");
		httpRes.getWriter().print(msg);

	}

	@Override
	public void doPut(HttpServletRequest httpReq, HttpServletResponse httpRes) throws IOException {
		String msg = "ccc. put.";
		LOG.debug(msg + "-------------------");
		httpRes.getWriter().print(msg);
	}

	// ---------------------------------------------------------
	// private method
	// ---------------------------------------------------------

	void debug() {

	}

}

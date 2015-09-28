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

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 */
public class DefaultServlet extends HttpServlet {

	// ---------------------------------------------------------
	// field
	// ---------------------------------------------------------

	/** */
	private static final long serialVersionUID = 8907340394573966L;
	/** Log instance */
	protected static final Logger LOG = LogManager.getLogger();

	// ---------------------------------------------------------
	// private method
	// ---------------------------------------------------------

	/** get */
	@Override
	protected void doGet(HttpServletRequest httpReq, HttpServletResponse httpRes) {
		try {
			httpRes.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} catch (IOException e1) {
			LOG.info(e1.getMessage(), e1);
		}
	}

	/** get */
	@Override
	protected void doPost(HttpServletRequest httpReq, HttpServletResponse httpRes) {
		try {
			httpRes.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} catch (IOException e1) {
			LOG.info(e1.getMessage(), e1);
		}
	}

	/** get */
	@Override
	protected void doPut(HttpServletRequest httpReq, HttpServletResponse httpRes) {
		try {
			httpRes.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} catch (IOException e1) {
			LOG.info(e1.getMessage(), e1);
		}
	}

	/** get */
	@Override
	protected void doDelete(HttpServletRequest httpReq, HttpServletResponse httpRes) {
		try {
			httpRes.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} catch (IOException e1) {
			LOG.info(e1.getMessage(), e1);
		}
	}

	void debug() {
	}

}

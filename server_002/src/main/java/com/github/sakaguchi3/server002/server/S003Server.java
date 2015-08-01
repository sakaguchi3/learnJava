package com.github.sakaguchi3.server002.server;

import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import javax.servlet.ServletException;
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
	static final Logger LOG = LogManager.getLogger();

	// ---------------------------------------------------------
	// public method
	// ---------------------------------------------------------

	public void init() throws ServletException {
		super.init();
	}

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

	// ---------------------------------------------------------
	// private method
	// ---------------------------------------------------------

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

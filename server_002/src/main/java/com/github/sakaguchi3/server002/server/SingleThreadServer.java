package com.github.sakaguchi3.server002.server;

import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 */
public class SingleThreadServer extends HttpServlet {

	// ---------------------------------------------------------
	// field
	// ---------------------------------------------------------

	/** */
	private static final long serialVersionUID = 8907340394573966L;

	/** Log instance */
	protected static final Logger LOG = LogManager.getLogger();

	/** counter */
	protected static AtomicInteger atomicInteger = new AtomicInteger();

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

		int cnt = atomicInteger.getAndIncrement();
		System.out.println("cnt:" + cnt);

		Semaphore semaphore = new Semaphore(1);
		try {
			// critical secton
			sleep(cnt);
			System.out.println("Critical section:" + cnt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}

		try (var w = httpRes.getWriter()) {
			w.append("success");
			w.flush();
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
	}

	/** */
	@Override
	protected void doPost(HttpServletRequest httpReq, HttpServletResponse httpRes) {
		// response ----
		try (var w = httpRes.getWriter()) {
			httpRes.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	// ---------------------------------------------------------
	// private method
	// ---------------------------------------------------------

	protected void sleep(final int cnt) {
		try {
			if (cnt == 0) {
				TimeUnit.SECONDS.sleep(10);
			} else if (cnt == 1) {
				TimeUnit.SECONDS.sleep(8);
			} else if (cnt == 2) {
				TimeUnit.SECONDS.sleep(8);
			} else if (cnt == 3) {
				TimeUnit.SECONDS.sleep(8);
			} else {
				TimeUnit.MILLISECONDS.sleep(30);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void debug() {

	}

}

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
package com.github.sakaguchi3.server002.api;

import static java.util.Collections.emptyMap;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Datasource {

	// ---------------------------------------------------------
	// field
	// ---------------------------------------------------------

	// static ----------------

	protected static final Logger LOG = LogManager.getLogger();

	// member ----------------

	// ---------------------------------------------------------
	// constructor
	// ---------------------------------------------------------

	public Map<String, Object> select(int id) {
		return emptyMap();
	}

	// ---------------------------------------------------------
	// protected method
	// ---------------------------------------------------------

	void debug() {
	}

	// ---------------------------------------------------------
	// public method
	// ---------------------------------------------------------

}

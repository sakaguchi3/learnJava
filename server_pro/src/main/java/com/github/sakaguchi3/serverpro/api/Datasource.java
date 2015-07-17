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
package com.github.sakaguchi3.serverpro.api;

import static java.util.Collections.emptyList;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.sakaguchi3.serverpro.db.DummyTableDto;

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

	public List<DummyTableDto> selectWhereId(List<Integer> id) {
		return emptyList();
	}

	public Optional<DummyTableDto> selectWhereId(int id) {
		return Optional.empty();
	}

	public void update(Map<String, Object> d) {

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

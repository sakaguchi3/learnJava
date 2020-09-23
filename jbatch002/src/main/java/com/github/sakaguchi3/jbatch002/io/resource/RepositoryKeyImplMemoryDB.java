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
package com.github.sakaguchi3.jbatch002.io.resource;

import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.github.sakaguchi3.jbatch002.io.resource.dto.KeyDto;

public class RepositoryKeyImplMemoryDB implements RepositoryKey {

	// ------------------------------------------------------
	// field
	// ------------------------------------------------------

	protected final List<KeyDto> list;

	// ------------------------------------------------------
	// constructor
	// ------------------------------------------------------

	public RepositoryKeyImplMemoryDB(List<KeyDto> list) {
		this.list = ofNullable(list).orElseGet(() -> new ArrayList<>());
	}

	// ------------------------------------------------------
	// private
	// ------------------------------------------------------

	void debug() {
	}

	// ------------------------------------------------------
	// public
	// ------------------------------------------------------

	@Override
	public Optional<KeyDto> selectWhereId(long id) {
		var ret = list.stream() //
				.filter(v -> Objects.equals(id, v.id)) //
				.findAny();
		return ret;
	}

	@Override
	public boolean insert(KeyDto record) {
		var isExsist = list.stream().anyMatch(v -> Objects.equals(v.id, record.id));
		if (isExsist)
			return false;

		// generate id
		if (isNull(record.id)) {
			var maxId = list.stream().mapToLong(v -> v.id).max().orElse(0);
			record.id = maxId + 1;
		}

		list.add(record);
		return true;
	}

	@Override
	public boolean update(KeyDto record) {
		var oldRecordOp = list.stream().filter(v -> Objects.equals(record.id, v.id)).findAny();
		if (oldRecordOp.isEmpty())
			return false;

		var oldRecord = oldRecordOp.get();

		if (isNotEmpty(record.corpName)) {
			oldRecord.corpName = record.corpName;
		}
		if (isNotEmpty(record.secretKey)) {
			oldRecord.secretKey = record.secretKey;
		}
		if (isNotEmpty(record.iv)) {
			oldRecord.iv = record.iv;
		}

		return true;
	}

	@Override
	public long size() {
		return list.size();
	}

	@Override
	public List<KeyDto> selectAll() {
		return Collections.unmodifiableList(list);
	}

}

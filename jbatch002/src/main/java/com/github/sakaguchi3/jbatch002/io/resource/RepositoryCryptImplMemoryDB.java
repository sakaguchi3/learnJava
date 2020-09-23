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
import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.github.sakaguchi3.jbatch002.io.resource.dto.CryptDto;

public class RepositoryCryptImplMemoryDB implements RepositoryCrypt {

	// ------------------------------------------------------
	// field
	// ------------------------------------------------------

	protected final List<CryptDto> list;

	// ------------------------------------------------------
	// constructor
	// ------------------------------------------------------

	public RepositoryCryptImplMemoryDB(List<CryptDto> list) {
		this.list = ofNullable(list).orElseGet(() -> new ArrayList<>());
	}

	// ------------------------------------------------------
	// public
	// ------------------------------------------------------

	@Override
	public Optional<CryptDto> selectWhereId(Long id) {
		return list.stream().filter(v -> Objects.equals(id, v.id)).findAny();
	}

	@Override
	public List<CryptDto> selectWhereKeyId(Long keyId) {
		return list.stream().filter(v -> Objects.equals(keyId, v.keyId)).collect(Collectors.toList());
	}

	@Override
	public boolean insert(CryptDto d) {
		boolean isExsist = list.stream().anyMatch(v -> Objects.equals(d.id, v.id));
		if (isExsist) {
			return false;
		}

		if (isNull(d.id)) {
			var maxid = list.stream().mapToLong(v -> v.id).max().orElse(0);
			d.id = maxid + 1;
		}
		list.add(d);

		return true;
	}

	@Override
	public boolean update(CryptDto newRecord) {
		var dtoOp = list.stream().filter(v -> Objects.equals(newRecord.id, v.id)).findAny();
		if (dtoOp.isEmpty()) {
			return false;
		}

		var oldREcord = dtoOp.get();
		if (nonNull(newRecord.crypted)) {
			oldREcord.crypted = newRecord.crypted;
		}
		if (nonNull(newRecord.keyId)) {
			oldREcord.keyId = newRecord.keyId;
		}

		return true;
	}

	@Override
	public boolean delete(Long id) {
		var removeTargetLst = list.stream().filter(v -> Objects.equals(id, v.id)).collect(Collectors.toList());
		list.removeAll(removeTargetLst);
		return true;
	}

	@Override
	public boolean delete(List<Long> ids) {
		var removeTargetLst = list.stream().filter(v -> ids.contains(v.id)).collect(Collectors.toList());
		list.removeAll(removeTargetLst);
		return true;
	}

	// ------------------------------------------------------
	// private
	// ------------------------------------------------------

	void debug() {
	}
}

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
package com.github.sakaguchi3.jbatch002.io.resource.dao;

import java.util.List;
import java.util.Optional;

import com.github.sakaguchi3.jbatch002.io.resource.RepositoryCrypt;
import com.github.sakaguchi3.jbatch002.io.resource.dto.CryptDto;

public class CryptDao implements RepositoryCrypt {

	// ------------------------------------------------------
	// field
	// ------------------------------------------------------

	final RepositoryCrypt delegate;

	// ------------------------------------------------------
	// constructor
	// ------------------------------------------------------

	public CryptDao(RepositoryCrypt repo) {
		this.delegate = repo;
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
	public Optional<CryptDto> selectWhereId(Long id) {
		return delegate.selectWhereId(id);
	}

	@Override
	public List<CryptDto> selectWhereKeyId(Long keyId) {
		return delegate.selectWhereKeyId(keyId);
	}

	@Override
	public boolean insert(CryptDto d) {
		return delegate.insert(d);
	}

	@Override
	public boolean update(CryptDto newRecord) {
		return delegate.update(newRecord);
	}

	@Override
	public boolean delete(Long id) {
		return delegate.delete(id);
	}

	@Override
	public boolean delete(List<Long> ids) {
		return delegate.delete(ids);
	}

}

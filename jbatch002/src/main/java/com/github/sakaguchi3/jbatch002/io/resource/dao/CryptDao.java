package com.github.sakaguchi3.jbatch002.io.resource.dao;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.sakaguchi3.jbatch002.io.resource.RepositoryCrypt;
import com.github.sakaguchi3.jbatch002.io.resource.dto.CryptDto;

public class CryptDao implements RepositoryCrypt {

	// ------------------------------------------------------
	// field
	// ------------------------------------------------------

	private static final Logger LOG = LogManager.getLogger();

	final RepositoryCrypt repo;

	// ------------------------------------------------------
	// constructor
	// ------------------------------------------------------

	public CryptDao(RepositoryCrypt repo) {
		this.repo = repo;
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
		return repo.selectWhereId(id);
	}

	@Override
	public List<CryptDto> selectWhereKeyId(Long keyId) {
		return repo.selectWhereKeyId(keyId);
	}

	@Override
	public boolean insert(CryptDto d) {
		return repo.insert(d);
	}

	@Override
	public boolean update(CryptDto newRecord) {
		return repo.update(newRecord);
	}

	@Override
	public boolean delete(Long id) {
		return repo.delete(id);
	}

	@Override
	public boolean delete(List<Long> ids) {
		return repo.delete(ids);
	}

}

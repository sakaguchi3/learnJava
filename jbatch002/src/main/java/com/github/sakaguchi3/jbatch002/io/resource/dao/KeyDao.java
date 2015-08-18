package com.github.sakaguchi3.jbatch002.io.resource.dao;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.sakaguchi3.jbatch002.io.resource.RepositoryKey;
import com.github.sakaguchi3.jbatch002.io.resource.dto.KeyDto;

public class KeyDao implements RepositoryKey {

	// ------------------------------------------------------
	// field
	// ------------------------------------------------------

	private static final Logger LOG = LogManager.getLogger();

	final RepositoryKey repo;

	// ------------------------------------------------------
	// constructor
	// ------------------------------------------------------

	public KeyDao(RepositoryKey repo) {
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

	public Optional<KeyDto> selectWhereId(long id) {
		return repo.selectWhereId(id);
	}

	public boolean insert(KeyDto record) {
		return repo.insert(record);
	}

	public boolean update(KeyDto record) {
		return repo.update(record);
	}

	@Override
	public long size() {
		return repo.size();
	}

	@Override
	public List<KeyDto> selectAll() {
		return repo.selectAll();
	}
}

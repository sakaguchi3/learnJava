package com.github.sakaguchi3.jbatch002.io.resource;

import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.sakaguchi3.jbatch002.io.resource.dto.KeyDto;

public class RepositoryImplLocalKey implements RepositoryKey {

	// ------------------------------------------------------
	// field
	// ------------------------------------------------------

	private static final Logger LOG = LogManager.getLogger();

	protected final List<KeyDto> list;

	// ------------------------------------------------------
	// constructor
	// ------------------------------------------------------

	public RepositoryImplLocalKey(List<KeyDto> list) {
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

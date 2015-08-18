package com.github.sakaguchi3.jbatch002.io.resource;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.sakaguchi3.jbatch002.io.resource.dto.CryptDto;

public class RepositoryImplLocalCrypt implements RepositoryCrypt {

	// ------------------------------------------------------
	// field
	// ------------------------------------------------------

	private static final Logger LOG = LogManager.getLogger();

	protected final List<CryptDto> list;

	// ------------------------------------------------------
	// constructor
	// ------------------------------------------------------

	public RepositoryImplLocalCrypt(List<CryptDto> list) {
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

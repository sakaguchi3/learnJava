package com.github.sakaguchi3.jbatch002.io.resource;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class _RepositoryImplLocal {

	// ------------------------------------------------------
	// field
	// ------------------------------------------------------

	private static final Logger LOG = LogManager.getLogger();

	protected final List<?> list;

	// ------------------------------------------------------
	// constructor
	// ------------------------------------------------------

	public _RepositoryImplLocal(List<?> list) {
		this.list = ofNullable(list).orElseGet(() -> new ArrayList<>());
	}

	// ------------------------------------------------------
	// public
	// ------------------------------------------------------

	public Optional<?> zOp() {
		return null;
	}

	public List<?> zLst() {
		return null;
	}

	public Map<?, ?> zMap() {
		return null;
	}

	// ------------------------------------------------------
	// private
	// ------------------------------------------------------
	
	void importP() {
		var a = isNull(null);
		var b = Objects.equals(null, null);
		var c = nonNull(null);
		var d = isEmpty(null);
		var e = isNotEmpty(null);
	}

	void debug() {
	}
}

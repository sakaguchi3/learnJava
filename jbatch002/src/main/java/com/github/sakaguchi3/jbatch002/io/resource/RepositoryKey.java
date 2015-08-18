package com.github.sakaguchi3.jbatch002.io.resource;

import java.util.List;
import java.util.Optional;

import com.github.sakaguchi3.jbatch002.io.resource.dto.KeyDto;

public interface RepositoryKey {

	// ------------------------------------------------------
	// field
	// ------------------------------------------------------

	// ------------------------------------------------------
	// constructor
	// ------------------------------------------------------

	// ------------------------------------------------------
	// public
	// ------------------------------------------------------

	boolean insert(KeyDto record);

	boolean update(KeyDto record);

	Optional<KeyDto> selectWhereId(long id);
	
	List<KeyDto> selectAll();
	
	long size();

	// ------------------------------------------------------
	// private
	// ------------------------------------------------------

}

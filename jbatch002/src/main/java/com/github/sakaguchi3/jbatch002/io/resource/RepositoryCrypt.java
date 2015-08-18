package com.github.sakaguchi3.jbatch002.io.resource;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.sakaguchi3.jbatch002.io.resource.dto.CryptDto;
import com.github.sakaguchi3.jbatch002.io.resource.dto.KeyDto;

public interface RepositoryCrypt {


	// ------------------------------------------------------
	// field
	// ------------------------------------------------------

	// ------------------------------------------------------
	// constructor
	// ------------------------------------------------------

	// ------------------------------------------------------
	// public
	// ------------------------------------------------------

	Optional<CryptDto> selectWhereId(Long id);

	boolean insert(CryptDto d);

	boolean update(CryptDto newRecord);

	List<CryptDto> selectWhereKeyId(Long keyId);

	boolean delete(Long id);

	boolean delete(List<Long> ids);

	// ------------------------------------------------------
	// private
	// ------------------------------------------------------

}

package com.github.sakaguchi3.jbatch002.io.resource.dto;

import static java.util.Objects.nonNull;

import java.io.Serializable;
import java.util.Base64;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.MoreObjects;

public class CryptDto implements Serializable, Cloneable {

	// ------------------------------------------------------
	// field
	// ------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 8325810844617232282L;

	private static final Logger LOG = LogManager.getLogger();

	@Nonnull
	public Long id = null;
	@Nonnull
	public Long keyId = null;
	@Nonnull
	public byte[] crypted = null;

	// ------------------------------------------------------
	// public
	// ------------------------------------------------------

	public static CryptDto of(Long keyId, byte[] crypted) {
		var o = new CryptDto();
		o.keyId = keyId;
		o.crypted = crypted;
		return o;
	}

	public boolean isValid() {
		boolean checkNoNullObject = true //
				&& nonNull(keyId) //
				&& nonNull(crypted) //
				&& true;
		return checkNoNullObject;
	}

	@Override
	public CryptDto clone() {
		var clone = SerializationUtils.clone(this);
		return clone;
	}

	@Override
	public String toString() {
		var strHelper = MoreObjects.toStringHelper(this) //
				.add("id", id) //
				.add("keyId", keyId);

		if (nonNull(crypted)) {
			var base64 = Base64.getEncoder().encodeToString(crypted);
			strHelper.add("crypted(base64)", base64);
		}

		var str = strHelper.toString();
		return str;
	}

	// ------------------------------------------------------
	// private
	// ------------------------------------------------------

	void debug() {
	}
}

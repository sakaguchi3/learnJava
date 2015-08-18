package com.github.sakaguchi3.jbatch002.io.resource.dto;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.io.Serializable;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.MoreObjects;

public class KeyDto implements Serializable, Cloneable {

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
	public String corpName = null;
	@Nonnull
	public String secretKey = null;
	@Nonnull
	public String iv = null;

	// ------------------------------------------------------
	// public
	// ------------------------------------------------------

	public static KeyDto of(Long id, String corpName, String secretKey, String iv) {
		var o = of(corpName, secretKey, iv);
		o.id = id;
		return o;
	}

	public static KeyDto of(String corpName, String secretKey, String iv) {
		var o = new KeyDto();
		o.corpName = corpName;
		o.secretKey = secretKey;
		o.iv = iv;
		return o;
	}

	public boolean isValid() {
		boolean checkNoNullObject = true //
				&& isNotEmpty(corpName) //
				&& isNotEmpty(secretKey) //
				&& isNotEmpty(iv);
		return checkNoNullObject;
	}

	@Override
	public KeyDto clone() {
		var clone = SerializationUtils.clone(this);
		return clone;
	}

	@Override
	public String toString() {
		var s = MoreObjects.toStringHelper(this) //
				.add("id", id) //
				.add("corpName", corpName) //
				.add("secretKey", secretKey) //
				.add("iv", iv) //
				.toString();
		return s;
	}

	// ------------------------------------------------------
	// private
	// ------------------------------------------------------

	void debug() {
	}
}

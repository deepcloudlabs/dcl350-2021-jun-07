package com.example.hr.domain;

import java.util.Base64;
import java.util.Objects;

@ValueObject
public final class Photo {
	private final byte[] value;

	private Photo(byte[] value) {
		this.value = value;
	}

	public byte[] getValue() {
		return value;
	}

	public String getBase64Value() {
		return Base64.getEncoder().encode(value).toString();
	}

	public static Photo of(byte[] value) {
		Objects.requireNonNull(value);
		return new Photo(value);
	}

	public static Photo of(String base64Value) {
		Objects.requireNonNull(base64Value);
		return new Photo(Base64.getDecoder().decode(base64Value));
	}

}

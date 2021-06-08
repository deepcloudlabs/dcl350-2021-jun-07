package com.example.hr.domain;

@ValueObject
public final class BirthYear {
	private final int value;

	private BirthYear(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static BirthYear valueOf(int value) {
		// validation, constraint, invariances, pre/post conditions, ...
		if (!isOver16YearsOfAge(value)) {
			throw new IllegalArgumentException("Must be over 16!");
		}
		return new BirthYear(value);
	}

	private static boolean isOver16YearsOfAge(int value) {
		return (2021 - value) >= 16;
	}

	@Override
	public String toString() {
		return "BirthYear [value=" + value + "]";
	}

}

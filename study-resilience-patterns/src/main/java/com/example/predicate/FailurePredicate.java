package com.example.predicate;

import java.util.function.Predicate;

public class FailurePredicate implements Predicate<Object> {

	@Override
	public boolean test(Object returnValue) {
		return returnValue.toString().equals("failure");
	}

}

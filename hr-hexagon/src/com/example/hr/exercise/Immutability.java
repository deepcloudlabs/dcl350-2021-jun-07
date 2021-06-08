package com.example.hr.exercise;

public class Immutability {

	public static void main(String[] args) {
		Integer x = Integer.valueOf(42);
		Integer y = 42; // auto boxing
		Integer u = 549;
		Integer v = 549;
		System.err.println("x==y? " + (x == y)); // true
		System.err.println("u==v? " + (u == v)); // false
	}

}

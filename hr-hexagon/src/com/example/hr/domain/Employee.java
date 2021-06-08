package com.example.hr.domain;

// Domain-Driven Design: Bounded Context, Ubiquitous Language: Employee, TcKimlikNo, FullName, Iban, Money 
// Entity: i) Persistent ii) Identity iii) Mutable
@Entity(identity = "tcKimlikNo")
public class Employee {
	private TcKimlikNo tcKimlikNo;
	private FullName fullname;
	private Iban iban;
	private Money salary;
	private BirthYear birthYear;
	private Photo photo;
	private Set<Department> department;
	private JobStyle style;
}

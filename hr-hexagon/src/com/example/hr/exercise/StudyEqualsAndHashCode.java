package com.example.hr.exercise;

import java.util.HashSet;
import java.util.Set;

// Effective Java
public class StudyEqualsAndHashCode {

	public static void main(String[] args) {
		Set<Customer> customers = new HashSet<>();
		customers.add(new Customer("1", "jack bauer"));
		customers.add(new Customer("2", "kate austen"));
		customers.add(new Customer("3", "james sawyer"));
		System.err.println(customers);
		System.err.println(customers.contains(new Customer("2", "kate austen")));
	}

}

class Customer {
	private String id;
	private String name;

	public Customer(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + "]";
	}

}

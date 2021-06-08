package com.example.hr.domain;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

// Effective Java (3rd Edition)
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
	private Set<Department> departments;
	private JobStyle style;

	public Employee(TcKimlikNo tcKimlikNo) {
		this.tcKimlikNo = tcKimlikNo;
	}

	public Employee(Builder builder) { // builder is always valid!
		this.tcKimlikNo = builder.tcKimlikNo;
		this.fullname = builder.fullname;
		this.iban = builder.iban;
		this.salary = builder.salary;
		this.photo = builder.photo;
		this.birthYear = builder.birthYear;
		this.departments = builder.departments;
		this.style = builder.style;
	}

	public TcKimlikNo getTcKimlikNo() {
		return tcKimlikNo;
	}

	public void setTcKimlikNo(TcKimlikNo tcKimlikNo) {
		this.tcKimlikNo = tcKimlikNo;
	}

	public FullName getFullname() {
		return fullname;
	}

	public void setFullname(FullName fullname) {
		this.fullname = fullname;
	}

	public Iban getIban() {
		return iban;
	}

	public void setIban(Iban iban) {
		this.iban = iban;
	}

	public Money getSalary() {
		return salary;
	}

	public void setSalary(Money salary) {
		this.salary = salary;
	}

	public BirthYear getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(BirthYear birthYear) {
		this.birthYear = birthYear;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public Set<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

	public JobStyle getStyle() {
		return style;
	}

	public void setStyle(JobStyle style) {
		this.style = style;
	}

	// Flow API: method chaining -> jQuery, Java SE 7/8+
	public static class Builder {
		private TcKimlikNo tcKimlikNo;
		private FullName fullname;
		private Iban iban;
		private Money salary;
		private BirthYear birthYear;
		private Photo photo;
		private Set<Department> departments;
		private JobStyle style;

		public Builder(String value) {
			this.tcKimlikNo = TcKimlikNo.of(value);
		}

		public Builder fullname(String firstName, String lastName) {
			this.fullname = FullName.valueOf(firstName, lastName);
			return this;
		}

		public Builder iban(String value) {
			this.iban = Iban.of(value);
			return this;
		}

		public Builder salary(double value) {
			this.salary = Money.of(value);
			return this;
		}

		public Builder salary(double value, FiatCurrency currency) {
			this.salary = Money.of(value, currency);
			return this;
		}

		public Builder birthYear(int value) {
			this.birthYear = BirthYear.valueOf(value);
			return this;
		}

		public Builder photo(byte[] value) {
			this.photo = Photo.of(value);
			return this;
		}

		public Builder photo(String value) {
			this.photo = Photo.of(value);
			return this;
		}

		public Builder departments(String... departments) {
			this.departments = Arrays.stream(departments).map(Department::valueOf).collect(Collectors.toSet());
			return this;
		}

		public Builder style(JobStyle style) {
			this.style = style;
			return this;
		}
		
		public Employee build() {
			//validation/constraint/business rule/pre-condition
			return new Employee(this);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tcKimlikNo == null) ? 0 : tcKimlikNo.hashCode());
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
		Employee other = (Employee) obj;
		if (tcKimlikNo == null) {
			if (other.tcKimlikNo != null)
				return false;
		} else if (!tcKimlikNo.equals(other.tcKimlikNo))
			return false;
		return true;
	}

}

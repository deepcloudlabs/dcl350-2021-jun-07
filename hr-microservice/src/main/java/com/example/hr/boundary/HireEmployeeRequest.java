package com.example.hr.boundary;

import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;
import com.example.validation.Iban;
import com.example.validation.TcKimlikNo;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HireEmployeeRequest {
	@JsonProperty("kimlik_no")
	@TcKimlikNo
	private String identity;
	@Size(min = 3)
	@JsonProperty("first_name")
	@NotEmpty
	private String firstName;
	@Size(min = 2)
	@JsonProperty("last_name")
	@NotEmpty
	private String lastName;
	@Iban
	private String iban;
	@Max(2006)
	@JsonProperty("birth_year")
	private int birthYear;
	@Min(3500)
	private double salary;
	private FiatCurrency currency;
	private String photo;
	private Set<String> departments;
	private JobStyle style;

	public HireEmployeeRequest() {
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public Set<String> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<String> departments) {
		this.departments = departments;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public FiatCurrency getCurrency() {
		return currency;
	}

	public void setCurrency(FiatCurrency currency) {
		this.currency = currency;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public JobStyle getStyle() {
		return style;
	}

	public void setStyle(JobStyle style) {
		this.style = style;
	}

	@Override
	public String toString() {
		return "HireEmployeeRequest [identity=" + identity + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", iban=" + iban + ", birthYear=" + birthYear + ", salary=" + salary + ", currency=" + currency
				+ ", photo=" + photo + ", departments=" + departments + ", style=" + style + "]";
	}

}

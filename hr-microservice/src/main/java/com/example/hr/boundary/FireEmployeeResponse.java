package com.example.hr.boundary;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FireEmployeeResponse {

	@JsonProperty("kimlik_no")
	private String identity;
	@JsonProperty("first_name")
	private String firstName;
	@JsonProperty("last_name")
	private String lastName;
	private String status = "success";

	public FireEmployeeResponse() {
	}

	public FireEmployeeResponse(String status) {
		this.status = status;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

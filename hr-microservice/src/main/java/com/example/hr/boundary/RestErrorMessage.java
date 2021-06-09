package com.example.hr.boundary;

public class RestErrorMessage {

	private String message;
	private int errorId;
	
	public RestErrorMessage(String message) {
		this.message = message;
	}

	
	public RestErrorMessage(String message, int errorId) {
		this.message = message;
		this.errorId = errorId;
	}


	public String getMessage() {
		return message;
	}


	public int getErrorId() {
		return errorId;
	}

}

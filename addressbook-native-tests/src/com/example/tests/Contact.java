package com.example.tests;

public class Contact {

	private String firstName;
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Contact setFristName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public Contact setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

}

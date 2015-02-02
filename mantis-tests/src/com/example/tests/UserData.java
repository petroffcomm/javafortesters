package com.example.tests;

public class UserData {
	
	public String id;
	public String login;
	public String email;
	public String password;
	
	public void setId(String id) {
		this.id = id;
	}

	public UserData setLogin(String login) {
		this.login = login;
		return this;
	}
	
	public UserData setPassword(String password) {
		this.password = password;
		return this;
	}

	public UserData setEmail(String email) {		
		this.email = email;
		return this;
	}
	
	public String getId() {
		return id;
	}
	
	public String getLogin() {
		return login;
	}

	public String getEmail() {
		return email;
	}

}

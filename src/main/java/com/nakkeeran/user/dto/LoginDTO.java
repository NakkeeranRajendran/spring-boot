package com.nakkeeran.user.dto;

public class LoginDTO {
	private String email;
	
	private String password;

	private boolean b;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setLoggedIn(boolean b) {
		this.b = b;
	}
	
	
}

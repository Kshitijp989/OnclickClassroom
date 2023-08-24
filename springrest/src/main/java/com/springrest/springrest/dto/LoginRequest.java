package com.springrest.springrest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoginRequest {
	 @NotBlank(message = "Email is required")
	    @Email(message = "Invalid email format")
	    private String emailId;

	    @NotBlank(message = "Password is required")
	    @NotNull(message = "Password cannot be null")
	    private String password;
	public LoginRequest(String emailId, String password) {
		super();
		this.emailId = emailId;
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	


	
}

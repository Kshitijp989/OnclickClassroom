package com.springrest.springrest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ResetPasswordDTO {

	@NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String emailId;

    @NotBlank(message = "Password is required")
    @NotNull(message = "Password cannot be null")
    private String password;
    
    @NotBlank(message = "New Password is required")
    @NotNull(message = "New Password cannot be null")
    private String newPassword;
    
    
    public ResetPasswordDTO(String emailId, String password,String newPassword) {
	super();
	this.emailId = emailId;
	this.password = password;
	this.newPassword=newPassword;
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

public String getnewPassword() {
	return newPassword;
}

public void setnewPassword(String newPassword) {
	this.newPassword = newPassword;
}


}

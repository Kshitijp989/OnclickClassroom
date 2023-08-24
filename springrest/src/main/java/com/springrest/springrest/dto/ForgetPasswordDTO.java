package com.springrest.springrest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ForgetPasswordDTO {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String emailId;

    public ForgetPasswordDTO() {
        // Default constructor
    }

    public ForgetPasswordDTO(String emailId) {
        this.emailId = emailId;
    }

    public String getEmailId() {
        return emailId;
    }
}
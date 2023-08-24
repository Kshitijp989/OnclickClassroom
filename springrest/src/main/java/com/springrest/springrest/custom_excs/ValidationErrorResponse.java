package com.springrest.springrest.custom_excs;

import java.util.ArrayList;
import java.util.List;

import com.springrest.springrest.Entity.ValidationError;

public class ValidationErrorResponse {
	 public List<ValidationError> errors = new ArrayList<>();

	    public void addValidationError(String field, String message) {
	        errors.add(new ValidationError(field, message));
	    }

	    public List<ValidationError> getErrors() {
	        return errors;
	    }

		
}

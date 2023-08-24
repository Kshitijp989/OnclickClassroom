	package com.springrest.springrest.Contoller;
	
	import java.util.ArrayList;
	import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.dao.DataIntegrityViolationException;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.validation.BindingResult;
	import org.springframework.validation.FieldError;
	import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
	
	import com.springrest.springrest.Entity.Users;
	import com.springrest.springrest.Services.IUserService;
	import com.springrest.springrest.custom_excs.CustomerHandlingException;
import com.springrest.springrest.dto.ForgetPasswordDTO;
import com.springrest.springrest.dto.LoginRequest;
import com.springrest.springrest.dto.ResetPasswordDTO;

import jakarta.validation.Valid;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RestController
	@RequestMapping("/user")
	public class UserContoller {
		@Autowired
		private IUserService userService;
		public UserContoller() {
			System.out.println("int const " + getClass().getName());
		}
		

		@PostMapping("/register")
		public ResponseEntity<?> registerUser(@RequestBody @Valid Users user, BindingResult bindingResult) throws CustomerHandlingException{
			System.out.print("register start");
			
			user.setUserType("student");
		    if (bindingResult.hasErrors()) {
		        List<String> errorMessages = new ArrayList<>();
	
		        for (FieldError error : bindingResult.getFieldErrors()) {
		            errorMessages.add(error.getDefaultMessage());
		        }
	
		     
		        return ResponseEntity.badRequest().body(errorMessages);
		    } else {
		        try {
		            Users registeredUser = userService.registerUser(user);

		            return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
		        } catch (DataIntegrityViolationException e) {
		            
		            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicate email detected.");
		        } catch (Exception e) {
		          //  e.printStackTrace();
		           
		        	//throw new CustomerHandlingException("Duplicate email detected.");
		            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Duplicate email detected.");
		        }
		    }
		}
		
		@PostMapping("/login")
	    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginRequest request,BindingResult bindingResult) 
		{
			 if (bindingResult.hasErrors()) {
			        List<String> errorMessages = new ArrayList<>();
		
			        for (FieldError error : bindingResult.getFieldErrors()) {
			            errorMessages.add(error.getDefaultMessage());
			        }
		
			     
			        return ResponseEntity.badRequest().body(errorMessages);
			    } else {
	        try {
	           
	        	
	            Users loggedInUser = userService.loginUser(request.getEmailId(),request.getPassword());

	            if (loggedInUser != null) {
	              
	                return ResponseEntity.ok(loggedInUser);
	            } else {
	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	            }
	        } catch (CustomerHandlingException e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login.");
	        }
			    }
	    }
		
		@PostMapping("/sendotp")
	    public ResponseEntity<?> SendOTP(@RequestBody @Valid ForgetPasswordDTO request,BindingResult bindingResult) 
		{
			 if (bindingResult.hasErrors()) {
			        List<String> errorMessages = new ArrayList<>();
		
			        for (FieldError error : bindingResult.getFieldErrors()) {
			            errorMessages.add(error.getDefaultMessage());
			        }
		
			     
			        return ResponseEntity.badRequest().body(errorMessages);
			    } 
			int result =  userService.sendOTP(request.getEmailId());
			if(result!=0)
				return ResponseEntity.ok(result);
			else
				return ResponseEntity.badRequest().body("Not a registered user");
		}
		
		@GetMapping("/verifyotp")
		public ResponseEntity<?> verifyOTP(@RequestParam int OTP) {
		    boolean status =  userService.verifyOTP(OTP);
		    if(status)
		        return ResponseEntity.ok(status);
		    else
		        return ResponseEntity.badRequest().body("OTP Not Matched");
		}
		
		@PutMapping("/setpassword")
	    public ResponseEntity<?> setPassword(@RequestBody @Valid LoginRequest request,BindingResult bindingResult) 
		{
			 if (bindingResult.hasErrors()) {
			        List<String> errorMessages = new ArrayList<>();
		
			        for (FieldError error : bindingResult.getFieldErrors()) {
			            errorMessages.add(error.getDefaultMessage());
			        }
		
			     
			        return ResponseEntity.badRequest().body(errorMessages);
			    } else {
	        try {
	           
	        	
	            int rows = userService.setPassword(request.getEmailId(),request.getPassword());

	            if (rows != 0) {
	              
	                return ResponseEntity.ok(rows);
	            } else {
	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	            }
	        } catch (CustomerHandlingException e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
	        }
			    }
	    }
		
		@PutMapping("/resetpassword")
	    public ResponseEntity<?> resesetPassword(@RequestBody @Valid ResetPasswordDTO request,BindingResult bindingResult) 
		{
			 if (bindingResult.hasErrors()) {
			        List<String> errorMessages = new ArrayList<>();
		
			        for (FieldError error : bindingResult.getFieldErrors()) {
			            errorMessages.add(error.getDefaultMessage());
			        }
		
			     
			        return ResponseEntity.badRequest().body(errorMessages);
			    } else {
	        try {
	           
	        	
	            int rows = userService.resetsetPassword(request.getEmailId(),request.getPassword(),request.getnewPassword());

	            if (rows != 0) {
	              
	                return ResponseEntity.ok(rows);
	            } else {
	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	            }
	        } catch (CustomerHandlingException e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
	        }
			    }
	    }

	@GetMapping("/getuserbyId/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable int userId) {
        Users user = userService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

}

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
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.Entity.Users;
import com.springrest.springrest.Services.IAdminService;
import com.springrest.springrest.custom_excs.CustomerHandlingException;

import jakarta.validation.Valid;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private IAdminService adminService;

	public AdminController() {
		
	}
	 @GetMapping("/getAllUsers")
		public  ResponseEntity<List<Users>> GetAllUsers() 
		{
			
		 List<Users> allUsers = adminService.GetAllUser();
		        return new ResponseEntity<>(allUsers, HttpStatus.OK);
		}
	 
	// http://localhost:8080/admin/approveUser/{userId}

	 @PutMapping("/approveUser/{userId}")
	    public Users approveUser(@PathVariable int userId) {
	        Users approvedUser = adminService.approveUser(userId);
	        if (approvedUser != null) {
	            return approvedUser;
	        } else {
	            
	            return null;
	        }
	}
	 
	 @PostMapping("/createTeacher")
		public ResponseEntity<?> CreateTeacher(@RequestBody @Valid Users user, BindingResult bindingResult) throws CustomerHandlingException{
			System.out.print("CreateTeacher start");
			
			user.setUserType("Teacher");
		    if (bindingResult.hasErrors()) {
		        List<String> errorMessages = new ArrayList<>();
	
		        for (FieldError error : bindingResult.getFieldErrors()) {
		            errorMessages.add(error.getDefaultMessage());
		        }

		        return ResponseEntity.badRequest().body(errorMessages);
		    } else {
		        try {
		            Users createdUser = adminService.createTeacher(user);
	
		           
		            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
		        } catch (DataIntegrityViolationException e) {
		            
		            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicate email detected.");
		        } catch (Exception e) {
		        
		            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Duplicate email detected.");
		        }
		    }
		}
}
	


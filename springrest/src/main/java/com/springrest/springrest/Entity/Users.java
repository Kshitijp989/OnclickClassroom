package com.springrest.springrest.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;




@Entity
@Table(name = "Users")
public class Users{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	@NotEmpty(message = "First name is required")
	@Size(max = 255, message = "First name must be less than or equal to 255 characters")
	@Column(name = "first_name")
	private String firstName;

	@NotEmpty(message = "Last name is required")
	@Size(max = 255, message = "Last name must be less than or equal to 255 characters")
	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email_id", unique = true, nullable = false)
	@NotEmpty(message = "Email is required")
	@Email(message = "Invalid email format")
	private String emailId;

	@NotEmpty(message = "Password is required")
	private String password;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
	@JsonIgnore
	private List<Courses> courselist;

	@Column(name = "user_type")
	private String userType;

	@NotEmpty(message = "Contact number is required")
	@Size(min = 10, max = 15, message = "Contact number must be between 10 and 15 characters")
	@Pattern(regexp = "^[0-9]*$", message = "Contact number must contain only digits")
	@Column(name = "contact_number", length = 15)
	private String contactNumber;

	private String IsApprove="Approve";


	public Users() {
		super();
	}
	
	public List<Courses> getCourselist() {
		return courselist;
	}

	public void setCourselist(List<Courses> courselist) {
		this.courselist = courselist;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getIsApprove() {
		return IsApprove;
	}
	public void setIsApprove(String IsApprove) {
		this.IsApprove = IsApprove;
	}

}
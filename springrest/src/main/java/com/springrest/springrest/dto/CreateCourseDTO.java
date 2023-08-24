package com.springrest.springrest.dto;

import com.springrest.springrest.Entity.Users;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class CreateCourseDTO {

	private int courseId;
	private String courseName;
	private String courseDescription;
	private String video;
    public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	private int userId;
	
	
	
	public CreateCourseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CreateCourseDTO(int courseId, String courseName, String courseDescription, int userId, String video) {
		
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.userId = userId;
		this.video=video;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String  getCourseDescription() {
		return courseDescription;
	}
	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}

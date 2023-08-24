package com.springrest.springrest.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Courses")
public class Courses {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int courseId;
	private String courseName;
	private String courseDescription;
	@Column(name = "video_path")
	private String videoPath;
	public String getVideoPath() {
		return videoPath;
	}
	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="user_Id_fk")
    private Users user;
	
	
	
	
	
	public Courses() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Courses(int courseId, String courseName, String courseDescription, String videoPath, Users user) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.videoPath = videoPath;
		this.user = user;
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
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
	
	
}

package com.springrest.springrest.Services;

import java.util.List;

import com.springrest.springrest.Entity.Courses;
import com.springrest.springrest.dto.CreateCourseDTO;


public interface ICourseService {

	List<Courses> getAllCourses();
	List<Courses> getAllCoursesByEducator(int userId);
	Courses getCourseById(int courseID);
	Courses addCourse(CreateCourseDTO course);
	int updateCourse(Courses scourse);
	void deleteCourse(int id);
	
}


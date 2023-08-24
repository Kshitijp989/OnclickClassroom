package com.springrest.springrest.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springrest.springrest.Entity.Courses;

public interface CourseDAO extends JpaRepository<Courses, Integer>
{
	
	@Query("select c from Courses c where c.user.userId = :userId")
    List<Courses> GetAllCoursesbyEducator(int userId);


}

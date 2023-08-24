package com.springrest.springrest.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.springrest.Entity.Courses;
import com.springrest.springrest.Entity.Users;
import com.springrest.springrest.Repository.CourseDAO;
import com.springrest.springrest.Repository.UserDao;
import com.springrest.springrest.dto.CreateCourseDTO;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CourseService implements ICourseService
{
	@Autowired
	private CourseDAO courseDao;
	@Autowired
	private UserDao userDao;
	

	@Override
	public List<Courses> getAllCourses() {
		
		return courseDao.findAll();
	}

	@Override
	public List<Courses> getAllCoursesByEducator(int userId) {
		return courseDao.GetAllCoursesbyEducator(userId);
	}

	@Override
	public Courses getCourseById(int courseID) {
		Optional<Courses> course = courseDao.findById(courseID);
		return course.get();
	}
	@Override
	public Courses addCourse(CreateCourseDTO coursedto) {
		Optional<Users> user = userDao.findById(coursedto.getUserId());
		Users user1=user.get();
		Courses course = new Courses(coursedto.getCourseId(),coursedto.getCourseName(),coursedto.getCourseDescription(),coursedto.getVideo(),user1);
          return courseDao.save(course);
	}

	@Override
	public int updateCourse(Courses course) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteCourse(int id) {
		Courses course = getCourseById(id);
		courseDao.delete(course);
	}

}

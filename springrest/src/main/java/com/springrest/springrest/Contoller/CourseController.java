package com.springrest.springrest.Contoller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springrest.springrest.Entity.Courses;
import com.springrest.springrest.Entity.Users;
import com.springrest.springrest.Repository.EnrollmentRequest;
import com.springrest.springrest.Services.ICourseService;
import com.springrest.springrest.Services.IUserService;
import com.springrest.springrest.dto.CreateCourseDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/course")
@Transactional
public class CourseController {
	
	@Autowired
	private ICourseService courseService;
	@PersistenceContext
    private EntityManager entityManager;


 @Autowired
    private IUserService userRepository;

@PostMapping("/enroll")
public ResponseEntity<Void> enrollUserToCourse(@RequestBody EnrollmentRequest enrollmentRequest) {
	  int userId = enrollmentRequest.getUserId();
      int courseId = enrollmentRequest.getCourseId();
	Courses course = courseService.getCourseById(courseId);
    Users user = userRepository.getUserById(userId);

    if (course!=null && user!=null) {
        enrollUserInCourse(userId, courseId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

@Transactional
public void enrollUserInCourse(int userId, int courseId) {
    entityManager.createNativeQuery("INSERT INTO user_course (user_id, course_id) VALUES (?, ?)")
        .setParameter(1, userId)
        .setParameter(2, courseId)
        .executeUpdate();
}

    @GetMapping("/deleteUserCourse")
    public ResponseEntity<Void> deleteUserCourse(@RequestParam int courseId) {
        deleteUserCourseInDatabase(courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Transactional
    public void deleteUserCourseInDatabase(int courseId) {
    	
    	entityManager.createNativeQuery("DELETE FROM user_course WHERE course_id = ?1").setParameter(1, courseId).executeUpdate();
    }

	
	  @GetMapping("/allCourses")
	    public ResponseEntity<List<Courses>> getAllCourses() {
	        List<Courses> courses = courseService.getAllCourses();
	        return new ResponseEntity<>(courses, HttpStatus.OK);
	    }
	  
	  @GetMapping("/getAllCoursesByEducator")
	    public ResponseEntity<List<Courses>> getAllCoursesByEducator(@RequestParam int userId) {
        List<Courses> courses = courseService.getAllCoursesByEducator(userId);
	        return new ResponseEntity<>(courses, HttpStatus.OK);
	    }
	
	  @GetMapping("/getcourse")
	    public ResponseEntity<Courses> getCourseById(@RequestParam int courseID) {
	        Courses course = courseService.getCourseById(courseID);
	        if (course != null) {
	            return new ResponseEntity<>(course, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	  

	  @PostMapping("/addCourse")
	  public ResponseEntity<Courses> addCourse(@RequestParam("video") String videoPath,
	                                           @RequestParam("courseName") String courseName,
	                                           @RequestParam("courseDescription") String courseDescription,
	                                           @RequestParam("userId") int userId) {
	      CreateCourseDTO courseDTO = new CreateCourseDTO(0, courseName, courseDescription, userId, videoPath);
	      Courses newCourse = courseService.addCourse(courseDTO);
	      return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
	  }


	  
	    @DeleteMapping("/deletecourse")
	    public ResponseEntity<Void> deleteCourse(@RequestParam int courseId) {
	        courseService.deleteCourse(courseId);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

}

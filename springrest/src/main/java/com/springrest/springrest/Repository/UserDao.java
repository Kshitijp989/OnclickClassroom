package com.springrest.springrest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.springrest.springrest.Entity.Users;

public interface UserDao extends JpaRepository<Users, Integer> {

	 Users findByUserId(int userId);
	@Query("select u from Users u where u.emailId = :email and u.password = :password")
	Users findByEmailIdAndPassword(String email, String password);
	
	@Modifying
	@Query("Update Users set password= :newPassword where emailId = :email ")
	int setPassword(String email,String newPassword);

	@Modifying
	@Query("Update Users set password= :getnewPassword where emailId = :emailId AND password=:password ")
	int resetPassword(String emailId, String password, String getnewPassword);
	
	@Query("select u from Users u where u.emailId = :EmailId")
	Users findbyEmailId(String EmailId);
}

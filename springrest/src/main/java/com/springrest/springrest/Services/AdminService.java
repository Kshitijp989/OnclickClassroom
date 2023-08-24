package com.springrest.springrest.Services;

import java.util.List;

import org.apache.catalina.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.springrest.Entity.Users;
import com.springrest.springrest.Repository.UserDao;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class AdminService implements IAdminService{
	@Autowired
	private UserDao userRepo;
	@Override
	public List<Users> GetAllUser() {
		List<Users> allUsers = userRepo.findAll();
		return allUsers;
	}

	public Users approveUser(int userId) {

		Users user = userRepo.findById(userId).orElse(null);

		if (user != null && user.getIsApprove().equalsIgnoreCase("Pending")) {

			user.setIsApprove("Approve");

			userRepo.save(user);
			return user;
		} else {

			return null;
		}
	}
	@Override
	public Users createTeacher(Users user) {
		try
		{

			if (user != null) {
				return userRepo.save(user);
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());

		}
		return user;
	}
}

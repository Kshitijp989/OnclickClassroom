package com.springrest.springrest.Services;

import java.util.List;

import org.apache.catalina.User;

import com.springrest.springrest.Entity.Users;

public interface IAdminService {
	List<Users> GetAllUser();
	Users approveUser(int userId);
	Users createTeacher(Users user);
}

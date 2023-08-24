package com.springrest.springrest.Services;

import com.springrest.springrest.Entity.Users;

public interface IUserService {
	Users registerUser(Users user);
    Users loginUser(String email, String password);
    Users getUserById(int userId);
    int sendOTP(String email);
    boolean verifyOTP(int EOTP);
    int setPassword(String email,String newPassword);
	int resetsetPassword(String emailId, String password, String getnewPassword);
}

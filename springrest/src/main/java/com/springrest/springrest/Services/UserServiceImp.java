package com.springrest.springrest.Services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.springrest.springrest.Entity.Users;
import com.springrest.springrest.Repository.UserDao;

import jakarta.transaction.Transactional;

@Service
@Transactional
public  class UserServiceImp implements IUserService {


	@Autowired
	private UserDao userRepo;
	@Autowired
	private JavaMailSender emailSender;

	@Override
	public Users registerUser(Users user) {
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

	@Override
	public Users loginUser(String email, String password) {
		try {

			Users foundUser = userRepo.findByEmailIdAndPassword(email, password);


			String app=foundUser.getIsApprove();
			if (foundUser != null ) {
				if("Approve".equals(app))
					return foundUser;
			}
		} catch (Exception e) {

			e.printStackTrace();
		}


		return null;
	}

	@Override
	public Users getUserById(int userId) {
		 return userRepo.findByUserId(userId);
	}
	static int OTP=0;
    static  long millis=0;
	@Override
	public int sendOTP(String email) {
		if(userRepo.findbyEmailId(email)!=null)
		{
		Random random = new Random();
		millis=System.currentTimeMillis()+1000*120;
		OTP = (int)(Math.random()*10000);
		if(OTP<1000)
			OTP=OTP*10;
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("patilgamer292@gmail.com");
		message.setTo(email);
		message.setSubject("OTP for Reset Password");
    	message.setText(String.valueOf(OTP));
		
		emailSender.send(message);
		return OTP;
		}
		return 0;
	}

	@Override
	public boolean verifyOTP(int EOTP) {
		if(EOTP==OTP&&millis>System.currentTimeMillis())
			return true;
		return false;
	}

	@Override
	public int setPassword(String email,String newPassword) {
		return userRepo.setPassword(email,newPassword);
	}

	@Override
	public int resetsetPassword(String emailId, String password, String getnewPassword) {
		return userRepo.resetPassword(emailId,password,getnewPassword);
	}
	
}

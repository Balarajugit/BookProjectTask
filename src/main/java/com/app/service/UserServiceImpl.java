package com.app.service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.domain.User;
import com.app.repo.IUserRepository;
@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserRepository repo;
	


	
	@Override
	public boolean isExist(String name) {
		int i = repo.checkingUser(name);
		if(i>0) {
			return true;
		}
		else
		return false;
	}
	
	
	@Override
	public int saveUser(User u) {
		String enc=Base64.getEncoder().encodeToString(u.getPassword().getBytes());
		u.setPassword(enc);
		return repo.save(u).getId();
	}
	
	@Override
	public User loginHandling(String name, String password) {
		// TODO Auto-generated method stub
		String encode = Base64.getEncoder().encodeToString(password.getBytes());
		return repo.login(name, encode);
	}
	
	
	@Override
	public boolean isExist(Integer id) {
		// TODO Auto-generated method stub
		return repo.existsById(id)
				;
	}
	
	@Override
	public String deleteUser(Integer id) {
		repo.deleteById(id);
		return "user has been deleted sucessfully";
	}
	
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}

package com.app.service;

import java.util.List;

import com.app.domain.User;

public interface IUserService {
	
	public boolean isExist(String name); 
	public int saveUser(User u);
	
	public User loginHandling(String name,String password);
	
	public boolean isExist(Integer id);
	
	public String deleteUser(Integer id);
	
	public List<User> getAllUsers();
	

}

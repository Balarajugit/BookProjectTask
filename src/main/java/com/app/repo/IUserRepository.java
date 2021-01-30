package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.domain.User;

public interface IUserRepository extends JpaRepository<User, Integer> {
	@Query("select count(*) from com.app.domain.User where name=?1")
	public int checkingUser(String name);
	
	@Query("from com.app.domain.User where name=?1 AND password=?2")
	public User login(String name,String password);
	
	

}

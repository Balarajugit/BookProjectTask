package com.app.restcontroller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.domain.User;
import com.app.service.IUserService;


@RestController
@RequestMapping("/admin")
public class AdminRestController {
	@Autowired
	private IUserService serv;
	
	
	@PostMapping("/save")
	public String saveUser(@RequestBody User u) {
		boolean exist = serv.isExist(u.getName());
		 if(exist) {
			 return u.getName()+" alredy exist ! please use another name";
		 }
		 else {
			 int i = serv.saveUser(u);
			 return " user details has been saved sucessfully "+i;
		 }
		 
		 
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteUserByAdmin(@PathVariable("id") int id,HttpSession session) {
	String role=(String)session.getAttribute("role");
	if(role.equalsIgnoreCase("admin")) {
		String deleteUser = serv.deleteUser(id);
		return deleteUser;
	}
	else
	return "you are not a admin you don't have the permission to delete user";
	}
	
	@PutMapping("/update/{id}")
	public String updateUserData(@PathVariable("id") int id1,@RequestBody User u,HttpSession session) {
		
		if(serv.isExist(id1)) {
			serv.saveUser(u);
			return "user details has been updated sucessfully";
		}
		else
		return "there is no user with the "+id1;
	
	}
	
	@GetMapping("/alluser")
	public ResponseEntity<?> getAllUSer(HttpSession session) {
		ResponseEntity<?> res=null;
		String role=(String)session.getAttribute("role");
		if(role.equalsIgnoreCase("admin")) {
		List<User> allUsers = serv.getAllUsers();
		res=new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
		}
		else {
		res=new ResponseEntity<String>("you dont have permission to get data", HttpStatus.BAD_REQUEST);
		}
		return res;
	}
  

}

package com.app.restcontroller;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/user")
public class UserRestController {
	@Autowired
	private IUserService service;
	
	
	@PostMapping("/save")
	public String saveUser(@RequestBody User u) {
		boolean exist = service.isExist(u.getName());
		 if(exist) {
			 return u.getName()+" alredy exist ! please use another name";
		 }
		 else {
			 int i = service.saveUser(u);
			 return " user details has been saved sucessfully "+i;
		 }
		 
		 
	}
	
	@GetMapping("/login/{name}/{password}")
	public String login(@PathVariable("name") String name1,@PathVariable("password") String password1,HttpSession session) {
		String mess=null;
		User user = service.loginHandling(name1, password1);
		if (user==null) {
			mess="Login Failed! Enter valid credentials";
		}
		else {
			addUserInSession(user, session);
			mess="login sucessfully";
		}
		return mess;
	}
	
	
	public void addUserInSession(User u,HttpSession session) {
		session.setAttribute("user", u);
		session.setAttribute("userId", u.getId());
		session.setAttribute("role", u.getRole());
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "logout sucessully";
	}
	
	
	
	@PutMapping("/update")
	public String updateUserData(@RequestBody User u,HttpSession session) {
		int id=(int)session.getAttribute("userId");
		if(service.isExist(id)) {
			service.saveUser(u);
			return "user details has been updated sucessfully";
		}
		return null;
		
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") Integer id) {
		String deleteUser = service.deleteUser(id);
		return deleteUser;
	}
	
	
	

}

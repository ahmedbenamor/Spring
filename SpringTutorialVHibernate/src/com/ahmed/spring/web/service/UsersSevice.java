package com.ahmed.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;




import com.ahmed.spring.web.dao.User;
import com.ahmed.spring.web.dao.UsersDao;

@Service("usersSevice")
public class UsersSevice {

	private UsersDao usersDao;
	
	public void create(User user) {
		usersDao.create(user);
		
	}

	@Autowired
	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public boolean exists(String username) {
		
		return usersDao.exists(username);
	}

	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		
		return usersDao.getAllUsers();
	}
	
	
	
	
}

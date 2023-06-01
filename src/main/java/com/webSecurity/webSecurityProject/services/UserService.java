package com.webSecurity.webSecurityProject.services;

import java.util.List;

import com.webSecurity.webSecurityProject.model.User;

public interface UserService {

	public User addUser(User user);
	public List<User> getAllUsers();
	public User updateUser(Long id,User user);
	public void deleteUser(User user);
	public User SearchUserById(Long id);
}

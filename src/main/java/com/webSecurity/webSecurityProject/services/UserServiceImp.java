package com.webSecurity.webSecurityProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webSecurity.webSecurityProject.model.User;
import com.webSecurity.webSecurityProject.repositories.UserRepository;

@Service
public class UserServiceImp implements UserService{

	@Autowired
	private UserRepository  userRepository;

	@Override
	public User addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User updateUser(Long id,User user) {
		// TODO Auto-generated method stub
		User u=userRepository.findById(id).get();
		//u.setId(id);
		u.setNom(user.getNom());
		u.setPrenom(user.getPrenom());
		u.setAge(user.getAge());
		return userRepository.save(u);
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		userRepository.delete(user);
	}

	@Override
	public User SearchUserById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).get();
	}
}
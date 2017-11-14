package com.example.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User create(User user,String rawPassword){
		//パスワードをハッシュ化して保存
		String encodedPassword  = passwordEncoder.encode(rawPassword);
		user.setPassword(encodedPassword);
		return userRepository.save(user);
	}
}
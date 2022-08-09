package com.terminusgroup.assessment.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.terminusgroup.assessment.Repositories.UserRepository;
import com.terminusgroup.assessment.models.User;

@RestController
@RequestMapping("api/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") Long id) {
		return userRepository.getReferenceById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public void createUser(@RequestBody User user) {
		userRepository.save(user);
	}
	
	
}

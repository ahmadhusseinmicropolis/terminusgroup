package com.terminusgroup.assessment.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.terminusgroup.assessment.modelsDtos.UserDto;
import com.terminusgroup.assessment.services.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<UserDto> getUsers() {
		return userService.getAll();
	}
	
	@GetMapping("/{id}")
	public UserDto getUserById(@PathVariable("id") Long id) {
		return userService.getById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public UserDto createUser(@RequestBody UserDto user) {
		return userService.create(user);
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public UserDto updateUser(@RequestBody UserDto user) {
		return userService.update(user);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") Long id) {
		userService.delete(id);
	}
	
}

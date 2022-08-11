package com.terminusgroup.assessment.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.crossstore.ChangeSetPersister.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.terminusgroup.assessment.modelsDtos.UserDto;
import com.terminusgroup.assessment.services.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {
	/**
	 * User controller
	 */
	@Autowired
	private UserService userService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<UserDto> getUsers() {
		/**
		 * Get all users.
		 */
		return userService.getAll();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public UserDto getUserById(@PathVariable("id") Long id) throws NotFoundException {
		/**
		 * Get user by id. 
		 */
		if (userService.getById(id) == null)
			throw new NotFoundException();
		return userService.getById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public UserDto createUser(@RequestBody UserDto user) throws NotFoundException {
		/**
		 * Create new user.
		 */
		if (user == null || user.getFirstName() == null)
			throw new NotFoundException();
		return userService.create(user);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public UserDto updateUser(@RequestBody UserDto user) throws NotFoundException {
		/**
		 * Update existing user.
		 */
		if (user == null || user.getId() == null)
			throw new NotFoundException();
		return userService.update(user);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteUser(@PathVariable("id") Long id) throws NotFoundException {
		/**
		 * Delete user.
		 */
		if (userService.getById(id) == null)
			throw new NotFoundException();
		userService.delete(id);
	}

}

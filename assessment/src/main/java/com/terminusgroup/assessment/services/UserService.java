package com.terminusgroup.assessment.services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.terminusgroup.assessment.Repositories.UserRepository;
import com.terminusgroup.assessment.models.User;
import com.terminusgroup.assessment.modelsDtos.UserDto;

@Service
public class UserService implements IUserService{
	
	private UserRepository userRepository;
	private ModelMapper mapper;
	
	public UserService(UserRepository userRepository, ModelMapper mapper) {
		this.userRepository= userRepository;
		this.mapper = mapper;
	}
	
	@Override
	public UserDto create(UserDto userDto) {
		User user = dtoToUser(userDto);
		User newUser = userRepository.save(user);
		return userToDto(newUser);
	}
	
	@Override
	public List<UserDto> getAll() {
		List<User> users =  userRepository.findAll();
		List<UserDto> userDtos = users.stream().map(user ->userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public UserDto getById(Long id) {
		User user = userRepository.getReferenceById(id);
		return userToDto(user);
	}

	@Override
	public UserDto update(UserDto userDto) {
		User user = userRepository.getReferenceById(userDto.getId());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setOccupation(userDto.getOccupation());
		user.setAge(userDto.getAge());
		User newUser = userRepository.save(user);
		return userToDto(newUser);
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
		
	}
	
	private UserDto userToDto(User user) {
		return mapper.map(user, UserDto.class);
	}
	
	private User dtoToUser(UserDto dto) {
		return mapper.map(dto, User.class);
	}
	
}

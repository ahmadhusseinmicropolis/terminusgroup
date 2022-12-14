package com.terminusgroup.assessment.services;

import static java.util.Optional.ofNullable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.terminusgroup.assessment.Repositories.UserRepository;
import com.terminusgroup.assessment.models.User;
import com.terminusgroup.assessment.modelsDtos.UserDto;

@Service
public class UserService implements IUserService {
	/**
	 * User service implementation.
	 */
	private UserRepository userRepository;
	private ModelMapper mapper;
	private final CacheManager cacheManager;
	public UserService(UserRepository userRepository, ModelMapper mapper,CacheManager cacheManager ) {
		/**
		 * Inject user repository and model mapper.
		 */
		this.userRepository = userRepository;
		this.mapper = mapper;
		this.cacheManager = cacheManager;
	}

	@Override
	public UserDto create(UserDto userDto) {
		/**
		 * Map usrDto to user Save the new user in DB Re-map and return the usrDto
		 */
		User user = dtoToUser(userDto);
		User newUser = userRepository.save(user);
		clearCache();
		return userToDto(newUser);
	}

	@Override
	@Cacheable(value = "usersCache" )
	public List<UserDto> getAll() {
		/**
		 * Get the users from DB Map users list to userDtos
		 * cash the results in memory
		 */
		List<User> users = userRepository.findAll();
		List<UserDto> userDtos = users.stream().map(user -> userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public UserDto getById(Long id) {
		/**
		 * Get the user from DB by id Map user to userDtos
		 */
		User user = userRepository.getReferenceById(id);
		return userToDto(user);
	}

	@Override
	public UserDto update(UserDto userDto) {
		/**
		 * Get the user from DB by id Update the properties Save the updated user
		 */
		User user = userRepository.getReferenceById(userDto.getId());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setOccupation(userDto.getOccupation());
		user.setAge(userDto.getAge());
		User newUser = userRepository.save(user);
		clearCache();
		return userToDto(newUser);
	}

	@Override
	public void delete(Long id) {
		/**
		 * Delete user from DB
		 */
		userRepository.deleteById(id);
		clearCache();
	}

	private UserDto userToDto(User user) {
		/**
		 * Map from user to userDto
		 */
		return mapper.map(user, UserDto.class);
	}

	private User dtoToUser(UserDto dto) {
		/**
		 * Map from userDto to user
		 */
		return mapper.map(dto, User.class);
	}
	
	private void clearCache() {
		cacheManager.getCacheNames()
        .stream()
        .map(cacheName -> ofNullable(cacheManager.getCache(cacheName)))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .forEach(Cache::clear);
	}

}

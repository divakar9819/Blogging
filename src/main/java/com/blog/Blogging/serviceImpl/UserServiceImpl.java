package com.blog.Blogging.serviceImpl;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blog.Blogging.entites.User;
import com.blog.Blogging.exception.ResourceNotFoundException;
import com.blog.Blogging.payload.PostDto;
import com.blog.Blogging.payload.PostResponse;
import com.blog.Blogging.payload.UserDto;
import com.blog.Blogging.repository.UserRepo;
import com.blog.Blogging.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = userDtoToUser(userDto);
		User savedUser = userRepo.save(user);
		return userToUserDto(savedUser);
	}
	
	

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("user", "id", userId));
		
		return userToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		//Pageable p = PageRequest.of(pageNumber, pageSize);
		//Page<User> page = userRepo.findAll(p);
		List<User> users = userRepo.findAll();
		List<UserDto> userDto = users.stream().map(ud->userToUserDto(ud)).collect(Collectors.toList());
		return userDto;
	}

	@Override
	public UserDto updateUser(UserDto userDto,Integer userId) {
		User user = userRepo.findById(userId).get();
		User updatedUser = null;
		if(user!=null) {
			user.setName(userDto.getName());
			user.setUserName(userDto.getUserName());
			user.setPassword(userDto.getPassword());
			user.setAbout(userDto.getAbout());
			updatedUser = userRepo.save(user);
		}
		return userToUserDto(updatedUser);
	}

	@Override
	public String deleteUser(Integer userId) {
		User user = userRepo.findById(userId).get();
		if(user!=null) {
			this.userRepo.delete(user);
			return "User deleted successfully";
		}
		return "User is not found";
	}
	
	public User userDtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
//		user.setUserId(userDto.getUserId());
//		user.setName(userDto.getName());
//		user.setUserName(userDto.getUserName());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;
		
	}
	
	public UserDto userToUserDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
//		userDto.setUserId(user.getUserId());
//		userDto.setName(user.getName());
//		userDto.setUserName(user.getUserName());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}

}

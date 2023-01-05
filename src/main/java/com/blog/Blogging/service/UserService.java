package com.blog.Blogging.service;

import java.util.List;

import com.blog.Blogging.entites.User;
import com.blog.Blogging.payload.UserDto;

public interface UserService {
	
	public UserDto createUser(UserDto userDto);
	public UserDto getUserById(Integer userId);
	public List<UserDto> getAllUsers();
	public UserDto updateUser(UserDto user,Integer userId);
	public String deleteUser(Integer userId);
	
	
	

}

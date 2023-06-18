package com.blog.app.service;

import com.blog.app.payload.UserDto;

import java.util.List;

public interface UserService {

    public UserDto createUser(UserDto userDto);
    public UserDto updateUser(UserDto userDto,int userId);
    public UserDto getUserById(int userId);
    public List<UserDto> getAllUser();
    public void deleteUser(int userId);


}

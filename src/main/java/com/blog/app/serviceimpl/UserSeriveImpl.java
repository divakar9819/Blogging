package com.blog.app.serviceimpl;

import com.blog.app.entites.User;
import com.blog.app.exception.ResourceNotFoundException;
import com.blog.app.payload.UserDto;
import com.blog.app.repository.UserRepo;
import com.blog.app.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserSeriveImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto,User.class);
        User createdUser = userRepo.save(user);
        return this.modelMapper.map(createdUser,UserDto.class);

    }

    @Override
    public UserDto updateUser(UserDto userDto,int userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        if(userDto.getName()!=null){
            user.setName(userDto.getName());
        }
        if(userDto.getUsername()!=null){
            user.setUsername(userDto.getUsername());
        }
        if(userDto.getPassword()!=null){
            user.setPassword(userDto.getPassword());
        }

        User updatedUser = userRepo.save(user);

        return this.modelMapper.map(updatedUser,UserDto.class);
    }

    @Override
    public UserDto getUserById(int userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        return this.modelMapper.map(user,UserDto.class);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos = users.stream().map((user)->modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(int userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        this.userRepo.delete(user);

    }
}

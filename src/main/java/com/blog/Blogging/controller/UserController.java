package com.blog.Blogging.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.Blogging.payload.ApiResponse;
import com.blog.Blogging.payload.UserDto;
import com.blog.Blogging.serviceImpl.UserServiceImpl;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@GetMapping("/home")
	public String home() {
		return "home page";
	}
	
	@PostMapping("/createUser")
	public ResponseEntity<UserDto>createUser(@Valid @RequestBody UserDto userDto){
		UserDto createdUser = this.userServiceImpl.createUser(userDto);
		return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
	}
	
	@GetMapping("/getUserById/{userId}")
	public ResponseEntity<UserDto>getUserById(@PathVariable Integer userId){
		return ResponseEntity.ok(this.userServiceImpl.getUserById(userId));
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(this.userServiceImpl.getAllUsers());
	}
	
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<UserDto>updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId){
		UserDto updatedUser = this.userServiceImpl.updateUser(userDto,userId);
		return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<ApiResponse>deleteUser(@PathVariable Integer userId){
		this.userServiceImpl.deleteUser(userId);
		return new ResponseEntity(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
	}
	
	

}

package com.blog.app.controller;

import com.blog.app.exception.ResourceNotFoundException;
import com.blog.app.payload.ApiResponse;
import com.blog.app.payload.UserDto;
import com.blog.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserCotroller {


    @Autowired
    UserService userService;
    @GetMapping("/home")
    public String home(){
        return "Home page";
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto createdUser = this.userService.createUser(userDto);
        return  new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("getUserById/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return  ResponseEntity.ok(this.userService.getAllUser());
    }

    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable Integer userId){
        UserDto updatedUser = this.userService.updateUser(userDto,userId);
        return new ResponseEntity<>(updatedUser,HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
        this.userService.deleteUser(userId);
        return ResponseEntity.ok(new ApiResponse("User deleted successfully",true));
    }




}

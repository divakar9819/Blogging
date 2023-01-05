package com.blog.Blogging.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.Blogging.entites.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}

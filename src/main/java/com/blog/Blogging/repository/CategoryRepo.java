package com.blog.Blogging.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.Blogging.entites.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}

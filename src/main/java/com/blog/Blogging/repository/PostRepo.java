package com.blog.Blogging.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.Blogging.entites.Category;
import com.blog.Blogging.entites.Post;
import com.blog.Blogging.entites.User;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer>{
	
	public Page<Post> findByUser(User user,Pageable p);
	
	public Page<Post> findByCategory(Category category,Pageable p);
	
	public List<Post> findByTitleContaining(String keywords);
}

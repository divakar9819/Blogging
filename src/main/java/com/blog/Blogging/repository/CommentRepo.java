package com.blog.Blogging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.Blogging.entites.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment,Integer> {

}

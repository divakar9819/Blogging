package com.blog.Blogging.service;

import java.util.List;

import com.blog.Blogging.payload.PostDto;
import com.blog.Blogging.payload.PostResponse;

public interface PostService {
	
	//create post
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	//update post
	
	public PostDto updatePostByPostId(PostDto postDto , Integer postId);
	public PostDto updatePost(PostDto postDto,Integer userId,Integer categoryId);
	
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	public PostDto getPostById(Integer postId);
	
	public PostResponse getPostByUser(Integer userId,Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	public PostResponse getPostByCategory(Integer categoryId,Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	//search post
	public List<PostDto> searchPost(String keywords);
	
	public void deletePost(Integer postId);
	
	


}

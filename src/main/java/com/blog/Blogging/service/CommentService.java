package com.blog.Blogging.service;

import com.blog.Blogging.payload.CommentDto;

public interface CommentService {
	
	public CommentDto createComment(CommentDto commentDto, Integer postId);
	
	public void deleteComment(Integer commentId);

}

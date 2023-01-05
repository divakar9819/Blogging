package com.blog.Blogging.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.Blogging.entites.Comment;
import com.blog.Blogging.entites.Post;
import com.blog.Blogging.exception.ResourceNotFoundException;
import com.blog.Blogging.payload.CommentDto;
import com.blog.Blogging.repository.CommentRepo;
import com.blog.Blogging.repository.PostRepo;
import com.blog.Blogging.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	PostRepo postRepo ;
	
	@Autowired
	CommentRepo commentRepo;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postid", postId));
		
		Comment comment = modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment savedComment = this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "commentId", commentId));
		this.commentRepo.delete(comment);
		
	}

}

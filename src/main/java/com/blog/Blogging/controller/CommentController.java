package com.blog.Blogging.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.Blogging.payload.ApiResponse;
import com.blog.Blogging.payload.CommentDto;
import com.blog.Blogging.service.CommentService;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@GetMapping("/home")
	public String commentHomePage() {
		return "Commnet Home Page";
	}
	
	@PostMapping("/create/{postId}")
	public ResponseEntity<CommentDto> createComment(
			@RequestBody CommentDto commentDto,
			@PathVariable Integer postId){
			
		CommentDto commentDto1 = this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(commentDto1,HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/delete/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully",true),HttpStatus.OK);
		
	}

}

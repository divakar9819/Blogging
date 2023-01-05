package com.blog.Blogging.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.blog.Blogging.entites.Category;
import com.blog.Blogging.entites.Comment;
import com.blog.Blogging.entites.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	
	private Integer postId;
	private String title;
	private String content;
	private String image;
	private Date createdAt;
	private UserDto user;
	private CategoryDto category;
	
	private Set<CommentDto> comments = new HashSet<>();
	

}

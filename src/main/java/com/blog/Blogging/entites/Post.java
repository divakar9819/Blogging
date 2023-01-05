package com.blog.Blogging.entites;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.blog.Blogging.payload.CategoryDto;
import com.blog.Blogging.payload.UserDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Post {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	
	@NotEmpty(message="Title can't be empty")
	@Column(length=500)
	private String title;

	@NotEmpty(message = "content can't be empty")
	@Column(length=1000)
	private String content;
	
	
	private String image;
	
	private Date createdAt;
	
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToMany(mappedBy= "post",cascade = CascadeType.ALL)
	private Set<Comment> comments = new HashSet<>();
	

}

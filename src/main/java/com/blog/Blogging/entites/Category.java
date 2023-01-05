package com.blog.Blogging.entites;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.blog.Blogging.payload.PostDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer categoryId;
	
	@NotEmpty(message = "category title can't be empty")
	@Size(min=4,max=32)
	private String categoryTitle;
	
	@NotEmpty(message = "category description can't be empty")
	@Column(length=1000)
	private String categoryDescription;
	
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Post> allPost = new ArrayList<>();

}

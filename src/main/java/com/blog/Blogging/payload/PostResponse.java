package com.blog.Blogging.payload;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PostResponse {
	
	private List<PostDto> content ;
	private Integer pageNumber;
	private Integer pageSize;
	private Integer totalElement;
	private Integer totalPage;
	private boolean lastPage;
	
	
	

}

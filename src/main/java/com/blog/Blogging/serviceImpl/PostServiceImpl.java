package com.blog.Blogging.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.Blogging.entites.Category;
import com.blog.Blogging.entites.Post;
import com.blog.Blogging.entites.User;
import com.blog.Blogging.exception.ResourceNotFoundException;
import com.blog.Blogging.payload.PostDto;
import com.blog.Blogging.payload.PostResponse;
import com.blog.Blogging.repository.CategoryRepo;
import com.blog.Blogging.repository.PostRepo;
import com.blog.Blogging.repository.UserRepo;
import com.blog.Blogging.service.FileService;
import com.blog.Blogging.service.PostService;


@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "userId", userId));
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "categoryId", categoryId));
		
		Post post = this.modelMapper.map(postDto,Post.class);
		post.setCategory(category);
		post.setUser(user);
		post.setCreatedAt(new Date());
		post.setImage("default.png");
		
		Post newPost = this.postRepo.save(post);
		
		return this.modelMapper.map(newPost,PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "userId", userId));
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "categoryId", categoryId));
		
		return null;
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		
		Sort sort = sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

		Pageable p = PageRequest.of(pageNumber,pageSize,sort);
		
		Page<Post> pagePosts = this.postRepo.findAll(p);
		List<Post> allPost = pagePosts.getContent();
		List<PostDto> postDtos = allPost.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalElement((int) pagePosts.getTotalElements());
		postResponse.setTotalPage(pagePosts.getTotalPages());
		postResponse.setLastPage(pagePosts.isLast());
		return postResponse ;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post id", postId));

		return this.modelMapper.map(post, PostDto.class);
	}

	//get by user
	@Override
	public PostResponse getPostByUser(Integer userId,Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "user id", userId));
//		System.out.println("=====Hello======");
//		System.out.println(user.toString());
		Sort sort = sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable p = PageRequest.of(pageNumber, pageSize,sort);
		Page<Post> userPage = this.postRepo.findByUser(user, p);
		List<Post> pageUser = userPage.getContent();
		List<PostDto> postDtos = pageUser.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(userPage.getNumber());
		postResponse.setPageSize(userPage.getSize());
		postResponse.setTotalPage(userPage.getTotalPages());
		postResponse.setTotalElement((int) userPage.getTotalElements());
		postResponse.setLastPage(userPage.isLast());
		
		
		return postResponse;
	}

	//get by category
	@Override
	public PostResponse getPostByCategory(Integer categoryId,Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "category id", categoryId));
		Sort sort = sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable p = PageRequest.of(pageNumber, pageSize,sort);
		Page<Post> categoryPage = this.postRepo.findByCategory(category, p);
		List<Post>  posts = categoryPage.getContent();
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(categoryPage.getNumber());
		postResponse.setPageSize(categoryPage.getSize());
		postResponse.setTotalPage(categoryPage.getTotalPages());
		postResponse.setTotalElement((int) categoryPage.getTotalElements());
		postResponse.setLastPage(categoryPage.isLast());
		return postResponse;
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post id", postId));
		this.postRepo.delete(post);
		
	}

	@Override
	public List<PostDto> searchPost(String keywords) {
		
		List<Post> posts = this.postRepo.findByTitleContaining(keywords);
		List<PostDto> postDto = posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public PostDto updatePostByPostId(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
		post.setImage(postDto.getImage());
		Post savedPost = this.postRepo.save(post);
		return this.modelMapper.map(savedPost, PostDto.class);
	}

	

}

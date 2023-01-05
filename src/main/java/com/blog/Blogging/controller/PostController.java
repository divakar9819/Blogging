package com.blog.Blogging.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.Blogging.config.AppConstants;
import com.blog.Blogging.payload.ApiResponse;
import com.blog.Blogging.payload.PostDto;
import com.blog.Blogging.payload.PostResponse;
import com.blog.Blogging.service.FileService;
import com.blog.Blogging.service.PostService;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Value("${project.image}")
	private String filePath;
	
	@Autowired
	private FileService fileService;
	
	@PostMapping("user/{userId}/category/{categoryId}/createPost")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId){
		
		PostDto savedPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity(savedPost,HttpStatus.CREATED);
		
		
	}
	
	
	@GetMapping("/getPostByUser/{userId}")
	public ResponseEntity<PostResponse> getPostByUser(@PathVariable Integer userId,
			@RequestParam(value = "pageNumber",defaultValue = AppConstants.Page_Number, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstants.Page_Size,required = false) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.Sort_By,required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstants.Sort_Dir,required = false) String sortDir
			){
		PostResponse postDtos = this.postService.getPostByUser(userId,pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(postDtos,HttpStatus.OK);
	}
	
	@GetMapping("/getPostByCategory/{categoryId}")
	public ResponseEntity<PostResponse> getPostByCategory(@PathVariable Integer categoryId,
			@RequestParam(value = "pageNumber",defaultValue = AppConstants.Page_Number,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstants.Page_Size,required = false) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.Sort_By,required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstants.Sort_Dir,required = false) String sortDir
			){
		PostResponse postDtos = this.postService.getPostByCategory(categoryId,pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(postDtos,HttpStatus.OK);
	}
	
	
	@GetMapping("/getAllPost")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber",defaultValue = AppConstants.Page_Number,required = false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue = AppConstants.Page_Size,required = false)Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.Sort_By,required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstants.Sort_Dir,required = false) String sortDir
			){
		PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
	
	@GetMapping("/getPostById/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		PostDto postDto = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	
	@GetMapping("/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPost(@PathVariable String keywords){
		return ResponseEntity.ok(this.postService.searchPost(keywords));
	}
	
	@DeleteMapping("/deletePostById/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully",true),HttpStatus.OK);
		
		
	}
	
	@PostMapping("upload/image/{postId}")
	public ResponseEntity<PostDto> uploadFile(
			@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException{
		
			PostDto postDto = postService.getPostById(postId);
			String fileName = this.fileService.uploadImage(filePath, image);
			postDto.setImage(fileName);
			PostDto updatedPostDto = this.postService.updatePostByPostId(postDto, postId);
			return new ResponseEntity<PostDto>(updatedPostDto,HttpStatus.OK);
	}
	
	@GetMapping(value = "/download/{imageName}" , produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
			@PathVariable String imageName,
			HttpServletResponse response
			) throws IOException {
		
		InputStream resource = this.fileService.getResources(filePath, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
		
		
		
	
		
	}
	
	

}

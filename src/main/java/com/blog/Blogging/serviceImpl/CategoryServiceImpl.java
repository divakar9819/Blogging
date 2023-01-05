package com.blog.Blogging.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.Blogging.entites.Category;
import com.blog.Blogging.exception.ResourceNotFoundException;
import com.blog.Blogging.payload.CategoryDto;
import com.blog.Blogging.repository.CategoryRepo;
import com.blog.Blogging.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelmapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category createdCategory = this.categoryDtoToCategory(categoryDto);
		return categoryToCategoryDto(this.categoryRepo.save(createdCategory));
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).
				orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
		
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		this.categoryRepo.save(category);
		return this.categoryToCategoryDto(category);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).
				orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
		return categoryToCategoryDto(category);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = categoryRepo.findAll();
		List<CategoryDto> categoryDtoList= new ArrayList<>();
		categoryDtoList = categories.stream().map((cat)->this.categoryToCategoryDto(cat)).collect(Collectors.toList());
		
		return categoryDtoList;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).
				orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
		
		this.categoryRepo.delete(category);
		
	}
	
	public CategoryDto categoryToCategoryDto(Category category) {
		return this.modelmapper.map(category,CategoryDto.class);
	}
	
	public Category categoryDtoToCategory(CategoryDto categoryDto) {
		return this.modelmapper.map(categoryDto,Category.class);
	}

}

package com.blog.Blogging.service;

import java.util.List;

import com.blog.Blogging.payload.CategoryDto;

public interface CategoryService {
	
	public CategoryDto createCategory(CategoryDto categoryDto);
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	public CategoryDto getCategoryById(Integer categoryId);
	public List<CategoryDto> getAllCategory();
	public void deleteCategory(Integer categoryId);

}

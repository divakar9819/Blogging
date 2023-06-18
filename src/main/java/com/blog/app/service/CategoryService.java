package com.blog.app.service;

import com.blog.app.payload.CategoryDto;

import java.util.List;

public interface CategoryService {

    public CategoryDto createCategory(CategoryDto categoryDto);
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
    public CategoryDto getCategoryById(Integer categoryId);
    public List<CategoryDto> getAllCategory();
    public void  deleteCategory(Integer categoryId);


}

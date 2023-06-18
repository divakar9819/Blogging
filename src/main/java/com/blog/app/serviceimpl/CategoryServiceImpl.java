package com.blog.app.serviceimpl;

import com.blog.app.entites.Category;
import com.blog.app.exception.ResourceNotFoundException;
import com.blog.app.payload.CategoryDto;
import com.blog.app.repository.CategoryRepo;
import com.blog.app.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto,Category.class);
        Category createdCategory = this.categoryRepo.save(category);
        return this.modelMapper.map(createdCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
        if(categoryDto.getCategoryTitle()!=null){
            category.setCategoryTitle(categoryDto.getCategoryTitle());
        }
        if(categoryDto.getCategoryDescription()!=null){
            category.setCategoryDescription(categoryDto.getCategoryDescription());
        }
        Category updatedCategory = this.categoryRepo.save(category);
        return this.modelMapper.map(updatedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = this.categoryRepo.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map((category -> this.modelMapper.map(category,CategoryDto.class))).collect(Collectors.toList());
        return categoryDtos;
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
        this.categoryRepo.delete(category);

    }
}

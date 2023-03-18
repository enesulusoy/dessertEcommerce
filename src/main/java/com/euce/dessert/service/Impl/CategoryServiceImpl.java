package com.euce.dessert.service.Impl;

import com.euce.dessert.dto.CategoryDto;
import com.euce.dessert.exception.RecordAlreadyExistsException;
import com.euce.dessert.model.Category;
import com.euce.dessert.repository.CategoryRepository;
import com.euce.dessert.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category saveCategory(CategoryDto categoryDto) {
        if (categoryRepository.existsByName(categoryDto.getName())) {
            throw new RecordAlreadyExistsException("There is already a category with this name");
        }

        Category category = Category.builder()
                .name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .build();

        return categoryRepository.save(category);
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No Such Category Exists")
        );
    }

    @Override
    public Category updateCategory(Long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No Such Category Exists")
        );

        if (categoryRepository.existsByName(categoryDto.getName())) {
            throw new RecordAlreadyExistsException("There is already a category with this name");
        }

        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No Such Category Exists")
        );
        categoryRepository.delete(category);
    }
}

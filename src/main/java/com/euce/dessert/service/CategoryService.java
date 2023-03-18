package com.euce.dessert.service;

import com.euce.dessert.dto.CategoryDto;
import com.euce.dessert.model.Brand;
import com.euce.dessert.model.Category;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryService {
    List<Category> getCategories();

    Category saveCategory(CategoryDto categoryDto);

    Category getCategory(Long id);

    Category updateCategory(Long id, CategoryDto categoryDto);

    void deleteCategory(Long id);
}

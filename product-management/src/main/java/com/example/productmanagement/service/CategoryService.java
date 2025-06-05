package com.example.productmanagement.service;

import com.example.productmanagement.entity.Category;
import com.example.productmanagement.repository.CategoryRepository;
import com.example.productmanagement.repository.ICategoryRepository;

import java.util.List;

public class CategoryService implements ICategoryService{
    private ICategoryRepository categoryRepository = new CategoryRepository();

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}

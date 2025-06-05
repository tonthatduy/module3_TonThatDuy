package com.example.productmanagement.repository;

import com.example.productmanagement.entity.Category;

import java.util.List;

public interface ICategoryRepository {
    List<Category>findAll();
}

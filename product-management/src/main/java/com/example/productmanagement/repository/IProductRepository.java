package com.example.productmanagement.repository;

import com.example.productmanagement.dto.ProductDtoResponse;
import com.example.productmanagement.entity.Product;

import java.util.List;

public interface IProductRepository {
    List<ProductDtoResponse> findAll();

    boolean add(Product product);

    Product findById(int id);

    void update(int id, Product product);

    void remove(int id);

    List<Product> searchByName(String name);
}

package com.example.productmanagement.repository;

import com.example.productmanagement.dto.ProductDtoResponse;
import com.example.productmanagement.entity.Product;

import java.util.List;

public interface IProductRepository {
    List<ProductDtoResponse> findAll();

    boolean add(Product product);

    Product findById(int id);

    boolean update(Product product);

    boolean deleteById(int id);

    List<ProductDtoResponse> searchByNameAndCateGory(String searchName, String searchCategory);

    List<ProductDtoResponse> findPaginated(int limit, int offset);

    int countTotalProducts();

    List<ProductDtoResponse> searchByCategory(String searchCategory);

    List<ProductDtoResponse> searchByName(String searchName);
}

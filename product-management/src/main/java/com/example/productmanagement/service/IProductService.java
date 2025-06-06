package com.example.productmanagement.service;

import com.example.productmanagement.dto.ProductDtoResponse;
import com.example.productmanagement.entity.Product;

import java.util.List;

public interface IProductService {
    List<ProductDtoResponse> findAll();

    boolean add(Product product);

    Product findById(int id);

    boolean update( Product product);

    boolean deleteById(int id);

    List<ProductDtoResponse> searchByName(String searchName, int idCategory);

    List<ProductDtoResponse> findPaginated(int limit, int offset);
    int countTotalProducts();

}

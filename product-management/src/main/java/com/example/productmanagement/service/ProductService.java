package com.example.productmanagement.service;

import com.example.productmanagement.dto.ProductDtoResponse;
import com.example.productmanagement.entity.Product;
import com.example.productmanagement.repository.IProductRepository;
import com.example.productmanagement.repository.ProductRepository;


import java.util.List;


public class ProductService implements IProductService {
    private IProductRepository productRepository = new ProductRepository();

    @Override
    public List<ProductDtoResponse> findAll() {
        return productRepository.findAll();
    }

    @Override
    public boolean add(Product product) {
      return   productRepository.add(product);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public boolean update( Product product) {
       return productRepository.update(product);
    }

    @Override
    public boolean deleteById(int id) {
       return productRepository.deleteById(id);
    }

    @Override
    public List<ProductDtoResponse> searchByName(String name, String category) {
        return productRepository.searchByName(name,category);
    }
}

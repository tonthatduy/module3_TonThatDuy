package com.example.productmanagement.service;

import com.example.productmanagement.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService implements IProductService {
    private static Map<Integer, Product> products;

    static {
        products = new HashMap<>();
        products.put(1, new Product(1, "The Great Gatsby", 15.99, "Classic novel by F. Scott Fitzgerald", "Scribner"));
        products.put(2, new Product(2, "1984", 12.50, "Dystopian novel by George Orwell", "Secker & Warburg"));
        products.put(3, new Product(3, "Clean Code", 39.99, "A Handbook of Agile Software Craftsmanship", "Prentice Hall"));
        products.put(4, new Product(4, "Effective Java", 45.00, "Best practices for the Java platform", "Addison-Wesley"));
        products.put(5, new Product(5, "Harry Potter and the Sorcerer's Stone", 29.99, "Fantasy novel by J.K. Rowling", "Bloomsbury"));
        products.put(6, new Product(6, "Atomic Habits", 20.00, "Tiny changes, remarkable results", "Avery"));
        products.put(7, new Product(7, "The Lean Startup", 25.50, "Entrepreneurship guide by Eric Ries", "Crown Publishing"));
        products.put(8, new Product(8, "To Kill a Mockingbird", 18.75, "Pulitzer Prize-winning novel", "J.B. Lippincott & Co."));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void update(int id, Product product) {
        products.put(id, product);
    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }
}

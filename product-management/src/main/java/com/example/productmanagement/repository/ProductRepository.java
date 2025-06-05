package com.example.productmanagement.repository;

import com.example.productmanagement.dto.ProductDtoResponse;
import com.example.productmanagement.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductRepository implements IProductRepository {
    List<Product> products = new ArrayList<>();
    private final String SELECT_ALL = "select * from product;";
    private final String SELECT_ALL_JOIN = "select p.* , c.name_category " +
                                            "from product p " +
                                            "join category c " +
                                            "on c.id_category = p.id_category;";
    private final String INSERT_INTO = "insert into product(name,price,description,publisher,idCategory values(?,?,?,?,?)";



    @Override
    public List<ProductDtoResponse> findAll() {
        List<ProductDtoResponse> productDtoResponses = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_JOIN);) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                String publisher = resultSet.getString("publisher");
                String nameCategory = resultSet.getString("name_category");
                ProductDtoResponse productDtoResponse = new ProductDtoResponse(id, name, price, description, publisher, nameCategory);
                productDtoResponses.add(productDtoResponse);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productDtoResponses;
    }

    @Override
    public boolean add(Product product) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO);){
            preparedStatement.setString(1,product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setString(3,product.getDescription());
            preparedStatement.setString(4,product.getPublisher());
            preparedStatement.setInt(5,product.getIdCategory());
            int effectRow = preparedStatement.executeUpdate();
            return effectRow == 1;
        }catch (SQLException e){
            System.out.println("Lỗi kết nối DB ");
        }
        return false;
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void update(int id, Product product) {

    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }

    @Override
    public List<Product> searchByName(String name) {
//        List<Product> result = new ArrayList<>();
//        for (Product product : products.values()) {
//            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
//                result.add(product);
//            }
//        }
        return null;
    }
}

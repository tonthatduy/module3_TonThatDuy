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
    private final String SELECT_ALL_JOIN =  "select p.* , c.name_category " +
                                            "from product p " +
                                            "join category c " +
                                            "on c.id_category = p.id_category;";
    private final String INSERT_INTO = "insert into product(name,price,description,publisher,id_category) values(?,?,?,?,?)";
    private final String DELETE = "delete from product where id = ?";
    private final String UPDATE_PRODUCT = "UPDATE product SET name=?, price=?, description=?, publisher=?, id_category=? WHERE id=?";
    private final String FIND_BY_ID = "select *from product WHERE id=?";
    private final String SEARCH_BY_NAME_CATEGORY =  "SELECT p.*, c.name_category " +
                                                    "FROM product p " +
                                                    "JOIN category c " +
                                                    "ON p.id_category = c.id_category " +
                                                    "WHERE p.name LIKE ? AND c.name_category LIKE ?";


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
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO);) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setString(4, product.getPublisher());
            preparedStatement.setInt(5, product.getIdCategory());
            int effectRow = preparedStatement.executeUpdate();
            return effectRow == 1;
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối DB ");
        }
        return false;
    }

    @Override
    public Product findById(int id) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                String publisher = resultSet.getString("publisher");
                int id_category = resultSet.getInt("id_category");
                return new Product(id,name,price,description,publisher,id_category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean update(Product product) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT);) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setString(4, product.getPublisher());
            preparedStatement.setInt(5, product.getIdCategory());
            preparedStatement.setInt(6, product.getId());
            int effectRow = preparedStatement.executeUpdate();
            return effectRow == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE);) {
            preparedStatement.setInt(1, id);
            int effectRow = preparedStatement.executeUpdate();
            return effectRow == 1;
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối DB");
        }
        return false;
    }

    @Override
    public List<ProductDtoResponse> searchByName(String name, String category) {
        List<ProductDtoResponse> resultList = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement ps = connection.prepareStatement(SEARCH_BY_NAME_CATEGORY)) {
            ps.setString(1, "%" + name + "%");
            ps.setString(2, "%" + category + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String productName = rs.getString("name");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                String publisher = rs.getString("publisher");
                String categoryName = rs.getString("name_category");
                resultList.add(new ProductDtoResponse(id, productName, price, description, publisher, categoryName));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }
}

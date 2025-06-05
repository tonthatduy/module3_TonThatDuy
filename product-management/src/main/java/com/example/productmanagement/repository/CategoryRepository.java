package com.example.productmanagement.repository;

import com.example.productmanagement.entity.Category;
import com.example.productmanagement.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements ICategoryRepository {
    private final String SELECT_ALL = "select * from category;";

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idCategory = resultSet.getInt("id_category");
                String nameCategory = resultSet.getString("name_category");
                Category category = new Category(idCategory, nameCategory);
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }
}

package com.example.mvc_2dt.repository.classs;

import com.example.mvc_2dt.entity.Class;
import com.example.mvc_2dt.repository.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassRepository implements  IClassRepository {
    private final String SELECT_ALL = "select * from class";
    @Override
    public List<Class> findAll() {
        List<Class> classList = new ArrayList<>();
        try(Connection connection = BaseRepository.getConnectDB();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int idClass = resultSet.getInt("id_class");
                String nameClass = resultSet.getString("name_class");
                classList.add(new Class(idClass,nameClass));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return classList;
    }
}

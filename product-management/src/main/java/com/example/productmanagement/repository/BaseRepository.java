package com.example.productmanagement.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/product_management"; // sửa lại tên của csdl
    private static final String USER = "root";// mặc định của mysql
    private static final String PASS = "codegym123456789";// do cài đặt khi cài đặt mysql

    public static Connection getConnectDB() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            System.out.println("Không tìm thấy JDBC Driver.");
            e.printStackTrace();
        } catch (SQLException throwables) {
            System.out.println("Kết nối thất bại: " + throwables.getMessage());
            throwables.printStackTrace();
        }
        return connection;
    }
}

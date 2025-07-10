package com.example.thi_module_3.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/trai_heo"; // sửa lại tên của csdl
    private static final String USER = "root";
    private static final String PASS = "codegym123456789";

    public static Connection getConnectDB() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            System.out.println("Không tìm thấy JDBC Driver.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Kết nối thất bại: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

}
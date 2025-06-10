package com.example.productmanagement.repository;

import com.example.productmanagement.dto.ProductDtoResponse;
import com.example.productmanagement.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository {
    private final String SELECT_ALL = "select * from product;";
    private final String SELECT_ALL_JOIN = "select p.* , c.name_category " +
            "from product p " +
            "join category c " +
            "on c.id_category = p.id_category;";
    private final String INSERT_INTO = "insert into product(name,price,description,publisher,id_category) values(?,?,?,?,?)";
    private final String DELETE = "delete from product where id = ?";
    private final String UPDATE_PRODUCT = "UPDATE product SET name=?, price=?, description=?, publisher=?, id_category=? WHERE id=?";
    private final String FIND_BY_ID = "select *from product WHERE id=?";
    private final String SEARCH_BY_NAME_AND_CATEGORY = "SELECT p.*, c.name_category " +
            "FROM product p " +
            "JOIN category c " +
            "ON p.id_category = c.id_category " +
            "WHERE p.name LIKE ? AND c.id_category = ?";
    //    private final String SELECT_1 = "select p.* , c.name_category " +
//            "from product p " +
//            "join category c " +
//            "on c.id_category = p.id_category " +
//            " where p.`name` like ?";
    private final String SEARCH_BY_NAME = "select p.*, c.name_category " +
            "from product p " +
            "join category c " +
            "on p.id_category = c.id_category " +
            "where p.name like ?";
    private static final String SELECT_PAGE = "SELECT p.*, c.name_category " +
            "FROM product p " +
            "JOIN category c ON p.id_category = c.id_category " +
            "LIMIT ? OFFSET ?";
    private static final String SEARCH_BY_CATEGORY = "SELECT p.id,p.name,p.price,p.description,p.publisher,c.name_category " +
            "FROM product p " +
            "JOIN category c ON p.id_category = c.id_category " +
            "WHERE c.id_category = ?";


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
                return new Product(id, name, price, description, publisher, id_category);
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
    public List<ProductDtoResponse> searchByNameAndCateGory(String searchName, String searchCategory) {
        List<ProductDtoResponse> resultList = new ArrayList<>();
            try (Connection connection = BaseRepository.getConnectDB();
                 PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_BY_NAME_AND_CATEGORY)) {
                preparedStatement.setString(1, "%" + searchName + "%");
                preparedStatement.setInt(2,Integer.parseInt(searchCategory) );
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String productName = resultSet.getString("name");
                    double price = resultSet.getDouble("price");
                    String description = resultSet.getString("description");
                    String publisher = resultSet.getString("publisher");
                    String categoryName = resultSet.getString("name_category");
                    resultList.add(new ProductDtoResponse(id, productName, price, description, publisher, categoryName));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        return resultList;
    }

    @Override
    public List<ProductDtoResponse> searchByCategory(String searchCategory) {
        List<ProductDtoResponse> productDtoResponseList = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_BY_CATEGORY)) {
            preparedStatement.setInt(1, Integer.parseInt(searchCategory));
            ResultSet resultSet =  preparedStatement.executeQuery();
            while (resultSet.next()){
                int id =Integer.parseInt(resultSet.getString("id"));
                String name = resultSet.getString("name");
                double price =Double.parseDouble(resultSet.getString("price"));
                String description = resultSet.getString("description");
                String publisher = resultSet.getString("publisher");
                String nameCategory = resultSet.getString("name_category");
                ProductDtoResponse productDtoResponse = new ProductDtoResponse(id,name,price,description,publisher,nameCategory);
                productDtoResponseList.add(productDtoResponse);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return productDtoResponseList;
    }

    @Override
    public List<ProductDtoResponse> searchByName(String searchName) {
        List<ProductDtoResponse> productDtoResponseList = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareCall(SEARCH_BY_NAME)) {
            preparedStatement.setString(1, "%" + searchName + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id =Integer.parseInt(resultSet.getString("id"));
                String name = resultSet.getString("name");
                double price =Double.parseDouble(resultSet.getString("price"));
                String description = resultSet.getString("description");
                String publisher = resultSet.getString("publisher");
                String nameCategory = resultSet.getString("name_category");
                ProductDtoResponse productDtoResponse = new ProductDtoResponse(id,name,price,description,publisher,nameCategory);
                productDtoResponseList.add(productDtoResponse);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi gọi searchByName");
            throw new RuntimeException(e);

        }
        return productDtoResponseList;
    }


    public List<ProductDtoResponse> findPaginated(int limit, int offset) {
        List<ProductDtoResponse> productDtoResponses = new ArrayList<>();
        try (Connection conn = BaseRepository.getConnectDB();
             PreparedStatement ps = conn.prepareStatement(SELECT_PAGE)) {
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ResultSet resultSet = ps.executeQuery();
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
            e.printStackTrace();
        }
        return productDtoResponses;
    }

    public int countTotalProducts() {
        try (Connection conn = BaseRepository.getConnectDB();
             PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM product")) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

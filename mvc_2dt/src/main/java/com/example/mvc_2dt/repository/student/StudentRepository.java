package com.example.mvc_2dt.repository.student;

import com.example.mvc_2dt.dto.StudentDtoReponse;
import com.example.mvc_2dt.entity.Student;
import com.example.mvc_2dt.repository.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements IStudentRepository {
    private final String SELECT_ALL_JOIN = "select s.*, c.name_class " +
            "from students s " +
            "JOIN class c " +
            "on c.id_class = s.id_class";
    private final String INSERT_INTO = "insert into students(name_student,id_class) values\n" +
            "(?,?)";
    private final String FIND_BY_ID = "select * from students where id=?";
    private final String UPDATE_STUDENT = "Update students set name_student = ?, id_class = ? where id = ?";
    private final String DELETE_BY_ID = "delete from students where id =?";
    private final String SEARCH_BY_NAME_AND_CLASS = "select s.*,c.name_class from students s\n" +
            "join class c on c.id_class = s.id_class\n" +
            "where s.name_student like ? and c.id_class =?";
    private final String SEARCH_BY_CLASS = "select s.*,c.name_class from students s \n" +
            "join class c on c.id_class =s.id_class\n" +
            "where c.id_class=?";
    private final String SEARCH_BY_NAME = "select s.*,c.name_class from students s\n" +
            "join class c on c.id_class = s.id_class\n" +
            "where s.name_student like ?";

    @Override
    public List<StudentDtoReponse> findAll() {
        List<StudentDtoReponse> studentList = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_JOIN)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idStudent = resultSet.getInt("id");
                String nameStudent = resultSet.getString("name_student");
                String nameClass = resultSet.getString("name_class");
                StudentDtoReponse studentDtoReponse = new StudentDtoReponse(idStudent, nameStudent, nameClass);
                studentList.add(studentDtoReponse);
            }
        } catch (SQLException e) {
            System.out.println("Không hiển thị được");
            throw new RuntimeException(e);
        }
        return studentList;
    }

    @Override
    public boolean add(Student student) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO)) {
            preparedStatement.setString(1, student.getNameStudent());
            preparedStatement.setInt(2, student.getIdClass());
            int effectRow = preparedStatement.executeUpdate();
            return effectRow == 1;
        } catch (SQLException e) {
            System.out.println("Không hiển thị được");
        }
        return false;
    }

    @Override
    public Student findById(int id) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name_student");
                int idClass = resultSet.getInt("id_class");
                return new Student(id, name, idClass);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean update(Student student) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT)) {
            preparedStatement.setString(1, student.getNameStudent());
            preparedStatement.setInt(2, student.getIdClass());
            preparedStatement.setInt(3, student.getIdStudent());
            int effectRow = preparedStatement.executeUpdate();
            return effectRow == 1;
        } catch (SQLException e) {
            System.out.println("Lỗi không update được");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, id);
            int effectRow = preparedStatement.executeUpdate();
            return effectRow == 1;
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối DB");
        }
        return false;
    }

    @Override
    public List<StudentDtoReponse> searchByNameAndClass(String searchName, String searchClass) {
        List<StudentDtoReponse> reusltList = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_BY_NAME_AND_CLASS)) {
            preparedStatement.setString(1, "%" + searchName + "%");
            preparedStatement.setInt(2, Integer.parseInt(searchClass));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nameStudent = resultSet.getString("name_student");
                String nameClass = resultSet.getString("name_class");
                reusltList.add(new StudentDtoReponse(id, nameStudent, nameClass));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reusltList;

    }

    @Override
    public List<StudentDtoReponse> searchByName(String searchName) {
        List<StudentDtoReponse> reusltList = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_BY_NAME)) {
            preparedStatement.setString(1, "%" + searchName + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nameStudent = resultSet.getString("name_student");
                String nameClass = resultSet.getString("name_class");
                reusltList.add(new StudentDtoReponse(id, nameStudent, nameClass));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi gọi searchByName");
            throw new RuntimeException(e);
        }
        return reusltList;
    }

    @Override
    public List<StudentDtoReponse> searchByClass(String searchClass) {
        List<StudentDtoReponse> reusltList = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_BY_CLASS)) {
            preparedStatement.setInt(1, Integer.parseInt(searchClass));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nameStudent = resultSet.getString("name_student");
                String nameClass = resultSet.getString("name_class");
                reusltList.add(new StudentDtoReponse(id, nameStudent, nameClass));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reusltList;
    }


}

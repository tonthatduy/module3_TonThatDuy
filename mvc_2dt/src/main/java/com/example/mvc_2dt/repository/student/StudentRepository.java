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
            "(?,?),";

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


}

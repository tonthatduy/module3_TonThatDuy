package com.example.mvc_2dt.service.student;

import com.example.mvc_2dt.dto.StudentDtoReponse;
import com.example.mvc_2dt.entity.Student;

import java.util.List;

public interface IStudentService {
    List<StudentDtoReponse> findAll();
    boolean add (Student student);
    Student findById(int id);
    boolean update(Student student);
    boolean deleteById(int id);
    List<StudentDtoReponse> searchByNameAndClass(String searchName,String searchClass);
    List<StudentDtoReponse> searchByName(String searchName);
    List<StudentDtoReponse> searchByClass(String searchClass);
}

package com.example.mvc_2dt.repository.student;

import com.example.mvc_2dt.dto.StudentDtoReponse;
import com.example.mvc_2dt.entity.Student;

import java.util.List;

public interface IStudentRepository {
    List<StudentDtoReponse> findAll();

}

package com.codegym.c0225g1.repository;

import com.codegym.c0225g1.entity.Student;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface IStudentRepository {
    List<Student> findAll();
    void add(Student student);
}

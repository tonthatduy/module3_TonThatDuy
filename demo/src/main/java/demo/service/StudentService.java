package com.codegym.c0225g1.service;

import com.codegym.c0225g1.entity.Student;
import com.codegym.c0225g1.repository.IStudentRepository;
import com.codegym.c0225g1.repository.StudentRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class StudentService implements IStudentService {
    private IStudentRepository studentRepository = new StudentRepository();

    @Override
    public List<Student> findAll() {

        return studentRepository.findAll();
    }

    @Override
    public void add(Student student) {
            studentRepository.add(student);
    }
}

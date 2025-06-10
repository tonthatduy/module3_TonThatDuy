package com.example.mvc_2dt.service.student;

import com.example.mvc_2dt.dto.StudentDtoReponse;
import com.example.mvc_2dt.entity.Student;
import com.example.mvc_2dt.repository.student.IStudentRepository;
import com.example.mvc_2dt.repository.student.StudentRepository;

import java.util.List;

public class StudentService implements IStudentService {
    private IStudentRepository studentRepository = new StudentRepository();

    @Override
    public List<StudentDtoReponse> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public boolean add(Student student) {
        return studentRepository.add(student);
    }

    @Override
    public Student findById(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public boolean update(Student student) {
        return studentRepository.update(student);
    }

    @Override
    public boolean deleteById(int id) {
        return studentRepository.deleteById(id);
    }

    @Override
    public List<StudentDtoReponse> searchByNameAndClass(String searchName, String searchClass) {
        return studentRepository.searchByNameAndClass(searchName,searchClass);
    }

    @Override
    public List<StudentDtoReponse> searchByName(String searchName) {
        return studentRepository.searchByName(searchName);
    }

    @Override
    public List<StudentDtoReponse> searchByClass(String searchClass) {
        return studentRepository.searchByClass(searchClass);
    }
}

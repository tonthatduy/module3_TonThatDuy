package com.example.mvc_2dt.service.classs;

import com.example.mvc_2dt.entity.Class;
import com.example.mvc_2dt.repository.classs.ClassRepository;
import com.example.mvc_2dt.repository.classs.IClassRepository;

import java.util.List;

public class ClassService implements IClassService {
    private IClassRepository classRepository = new ClassRepository();

    @Override
    public List<Class> findAll() {
        return classRepository.findAll();
    }
}

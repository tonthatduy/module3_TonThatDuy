package com.codegym.c0225g1.service;

import com.codegym.c0225g1.entity.Person;

import java.util.List;

public interface IService<T> {

    List<T> findAll();
    void add(T student);
}

package com.example.mvc_2dt.dto;

public class StudentDtoReponse {
    private int id;
    private String name;
    private String nameClass;

    public StudentDtoReponse(int id, String name, String nameClass) {
        this.id = id;
        this.name = name;
        this.nameClass = nameClass;
    }

    public StudentDtoReponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }
}

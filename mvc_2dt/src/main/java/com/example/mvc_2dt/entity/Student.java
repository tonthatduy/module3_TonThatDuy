package com.example.mvc_2dt.entity;

public class Student {
    private int idStudent;
    private String nameStudent;
    private int idClass;

    public Student() {
    }

    public Student(int idStudent, String nameStudent, int idClass) {
        this.idStudent = idStudent;
        this.nameStudent = nameStudent;
        this.idClass = idClass;
    }

    public Student(String nameStudent, int idClass) {
        this.nameStudent = nameStudent;
        this.idClass = idClass;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }
}

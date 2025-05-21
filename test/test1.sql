create database if not exists c0225g1;
use c0225g1;
CREATE TABLE jame (
    username VARCHAR(50) PRIMARY KEY,
    `password` VARCHAR(50)
);
CREATE TABLE class (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50)
);
CREATE TABLE room (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    class_id INT,
    FOREIGN KEY (class_id)
        REFERENCES class (id)
);
CREATE TABLE student (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    gender BOOLEAN,
    birthday DATE,
    email VARCHAR(100),
    point FLOAT,
    username VARCHAR(50) UNIQUE,
    class_id INT,
    FOREIGN KEY (username)
        REFERENCES jame (username),
    FOREIGN KEY (class_id)
        REFERENCES class (id)
);
CREATE TABLE instructor (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    birthday DATE,
    salary FLOAT
);
CREATE TABLE instructor_class (
    instructor_id INT,
    class_id INT,
    start_time DATE,
    end_time_time DATE,
    PRIMARY KEY (instructor_id , class_id),
    FOREIGN KEY (instructor_id)
        REFERENCES instructor (id),
    FOREIGN KEY (class_id)
        REFERENCES class (id)
);


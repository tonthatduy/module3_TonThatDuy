create database management_codegym;

use management_codegym;

CREATE TABLE class (
    id INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(100) NOT NULL
);

CREATE TABLE class_room (
    id INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `room_type` ENUM('LyThuyet,ThucHanh') NOT NULL,
    class_id INT,
    FOREIGN KEY (class_id)
        REFERENCES class (id)
);

CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `birthday` DATE NOT NULL,
    `gender` ENUM('Nam', 'Nu') NOT NULL,
    `email` VARCHAR(255) UNIQUE,
    `score` FLOAT,
    class_id INT,
    FOREIGN KEY (class_id)
        REFERENCES class (id)
);

CREATE TABLE accounts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    `user_name` VARCHAR(255) NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    student_id INT UNIQUE,
    FOREIGN KEY (student_id)
        REFERENCES students (id)
);

CREATE TABLE teachers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `birthday` DATE NOT NULL,
    `salary` DECIMAL(10 , 2 ) NOT NULL
);

CREATE TABLE teaching (
    teacher_id INT,
    class_id INT,
    PRIMARY KEY (teacher_id , class_id),
    FOREIGN KEY (teacher_id)
        REFERENCES teachers (id),
    FOREIGN KEY (class_id)
        REFERENCES class (id)
);
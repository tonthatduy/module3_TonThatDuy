create database quan_ly_sinh_vien;

use quan_ly_sinh_vien;

create table class(
class_id int auto_increment primary key,
class_name varchar(100),
star_date date,
`status` boolean
);

create table student(
student_id int auto_increment primary key,
student_name varchar(50),
address varchar(50),
phone char(10),
`status` boolean,
class_id int,
foreign key(class_id) references class(class_id)
);

create table `subject`(
sub_id int auto_increment primary key,
sub_name varchar(50),
credit int,
`status` boolean
); 

create table mark(
mark_id int auto_increment primary key,
sub_id int,
student_id int,
mark int,
exam_times int,
foreign key(sub_id) references subject(sub_id),
foreign key(student_id) references student(student_id)
);

INSERT INTO class (class_name,star_date,`status`)
VALUES 
( 'A1', '2008-12-20', 1),
( 'A2', '2008-12-22', 1),
( 'B3', current_date, 0);

INSERT INTO student (student_name, address, phone, `status`, class_id)
VALUES 
('Hung', 'Ha Noi', '0912113113', 1, 1),
('Manh', 'HCM', '0123123123', 0, 2);
INSERT INTO student (student_name, address, `status`, class_id)
VALUES 
('Hoa', 'Hai phong', 1, 1);

INSERT INTO `subject`
VALUES 
(1, 'CF', 5, 1),
(2, 'C', 6, 1),
(3, 'HDJ', 5, 1),
(4, 'RDBMS', 10, 1);

INSERT INTO mark (sub_id, student_id, mark, exam_times)
VALUES 
(1, 1, 8, 1),
 (1, 2, 10, 2),
 (2, 1, 12, 1);
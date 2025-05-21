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
-- hiển thị bảng học sinh
select * from student;
-- hiển thị học sinh đang học
select * from student 
where `status` = true;
-- hiển thị môn học có thời gian nhỏ hơn 10
select * 
from `subject`
where credit <10;
-- hiển thị hai bảng học sinh và lớp học
select s.student_id,s.student_name,c.class_name
from student s join class c on s.class_id = c. class_id;
-- hiển thị các học sinh ở lớp 'A1' 
select s.student_id,s.student_name,c.class_name
from student s join class c on s.class_id = c. class_id
where c.class_name = 'A1';
-- hiển thị tất cả các điểm đang có của học viên
select s.student_id,s.student_name,sub.sub_name,m.mark
from student s join mark m on s.student_id = m.student_id join `subject` sub on m.sub_id = sub.sub_id;
-- hiển thị điểm môn cf của học viên
select s.student_id,s.student_name,sub.sub_name,m.mark
from student s join mark m on s.student_id = m.student_id join `subject` sub on m.sub_id = sub.sub_id
where sub.sub_name = 'CF';

create database student_management;

use student_management;

create table class(
id int primary key,
name varchar(255)
);

create table teacher(
id int primary key,
name varchar(255),
age int,
country varchar(255)
);

insert into class(id,name)
values
(1,"C0125G1"),
(2,"C0225G1"),
(3,"C0325G1");
insert into teacher(id,name,age,country)
values
(1,"Chánh TV",30,"Quảng Nam"),
(2,"Hải TT",27,"Đà Nẵng"),
(3,"Sự Trương",25,"Đà Nẵng");
select * from class;
select * from teacher;
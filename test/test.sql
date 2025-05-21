create database my_database;

use my_database;

create table my_table (
cot int primary key,
cot2 varchar(255)
);

alter table my_table add primary key(cot);

select * from my_table;

insert into my_table(cot,cot2)
values
(1, "gia tri 1"),
(2,"gia tri 2"),
(3,"gia tri 3"),
(4,"gia tri 4");

delete from my_table where cot = 1;

drop table my_table;

-- chỉnh sửa dữ liêu
update my_table 
set cot2 ='gia tri 0'
where cot =1;
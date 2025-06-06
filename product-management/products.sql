create database product_management;

use product_management;


create table category(
id_category int auto_increment primary key,
name_category varchar(100)
);


create table product (
id int auto_increment primary key,
`name` varchar(100) not null,
price double,
`description` text,
publisher varchar(100),
id_category  int,
foreign key (id_category) references category(id_category)
);

INSERT INTO category (name_category) VALUES 
('Sách Giáo Khoa'),
('Tiểu Thuyết'),
('Truyện Tranh'),
('Tâm Lý - Kỹ Năng');


INSERT INTO product (`name`, price, `description`, publisher, id_category) VALUES 
('Toán lớp 10', 45000, 'Sách giáo khoa Toán lớp 10 mới', 'NXB Giáo Dục', 1),
('Đắc Nhân Tâm', 98000, 'Sách kỹ năng giao tiếp nổi tiếng', 'NXB Trẻ', 4),
('Doraemon Tập 1', 18000, 'Truyện tranh thiếu nhi nổi tiếng của Nhật', 'NXB Kim Đồng', 3),
('Harry Potter Tập 1', 120000, 'Tiểu thuyết phép thuật nổi tiếng toàn cầu', 'NXB Trẻ', 2);

select p.* , c.name_category 
from product p
join category c
on c.id_category = p.id_category;
select * from product;

select * from category;

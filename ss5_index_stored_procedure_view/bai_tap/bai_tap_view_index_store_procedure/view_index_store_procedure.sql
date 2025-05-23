create database demo;

use demo;

create table products(
id int auto_increment primary key,
product_code varchar(50),
product_name varchar(255),
product_price decimal(10,2),
product_amount int,
product_description text,
product_status boolean
);

INSERT INTO products (product_code, product_name, product_price, product_amount, product_description, product_status)
VALUES 
('P001', 'iPhone 15', 999.99, 50, 'Smartphone Apple thế hệ mới nhất', TRUE),
('P002', 'Samsung Galaxy S23', 899.99, 40, 'Điện thoại flagship Samsung 2023', TRUE),
('P003', 'Xiaomi Redmi Note 13', 299.50, 100, 'Điện thoại giá rẻ cấu hình cao', TRUE),
('P004', 'MacBook Pro M2', 1999.00, 20, 'Laptop hiệu năng cao cho dân chuyên nghiệp', TRUE),
('P005', 'AirPods Pro', 249.00, 0, 'Tai nghe không dây chống ồn của Apple', FALSE);

-- bước 3 tạo index
create unique index product_code on products(product_code);
create unique index id_name_price on products(product_name,product_price);	

explain select product_code from products;
explain select product_name,product_price from products;

-- bước 4 tạo view
-- create view product1 as
-- select product_code,product_name,product_price,product_status
-- from products;

-- select *
-- from product1;

-- alter view product1 as
-- select  product_code,product_name,product_price,product_status,product_amount
-- from products;

-- drop view product1;

-- bước 5 tạo sp

DELIMITER //
create procedure get_all_product()
begin
select * from products;
end //
DELIMITER ;

call get_all_product();
-- thêm một sản phẩm mới

Delimiter //
create procedure add_product_by_id(
IN p_code varchar(10),
IN p_name varchar(255),
IN p_price DECIMAL(10,2),
IN p_amount int,
IN p_desc text,
IN p_status boolean
)
begin
insert into products (
product_code,
product_name,
product_price,
product_amount,
product_description,
product_status
) values
(p_code,p_name,p_price,p_amount,p_desc,p_status)
;
end//
Delimiter ;
delete from products where product_code = 'P007';
call add_product_by_id('P008','iPad Pro 2024', 1299.00, 15, 'Máy tính bảng cao cấp của Apple', TRUE);
call add_product_by_id('P007','iPad Pro 2024', 1299.00, 15, 'Máy tính bảng cao cấp của Apple', TRUE);

-- Sửa sản phẩm
Delimiter //
create procedure update_product(
IN p_id int,
IN p_code varchar(10),
IN p_name varchar(255),
IN p_price DECIMAL(10,2),
IN p_amount int,
IN p_desc text,
IN p_status boolean
)
begin
update products 
Set 
product_code = p_code,
product_name = p_name,
product_price = p_price,
product_amount = p_amount,
product_description = p_desc,
product_status = p_status
where id = p_id;
end//
Delimiter ;

call update_product(13,'P007','iPad Pro 2024', 1299.00, 15, 'Máy tính bảng cao cấp của Apple', TRUE);

-- xóa sản phẩm
Delimiter //
create procedure delete_product(IN p_id int)
begin
delete from products where id = p_id;
end //
Delimiter ;

call delete_product(13);



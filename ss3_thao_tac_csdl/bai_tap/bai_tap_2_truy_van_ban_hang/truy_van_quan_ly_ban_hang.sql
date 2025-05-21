create database quan_ly_ban_hang;

use quan_ly_ban_hang;

create table customers(
customer_id int auto_increment primary key,
name varchar(100),
age int
);

create table orders(
order_id int auto_increment primary key,
order_date date,
order_total_price double,
customer_id int,
foreign key(customer_id) references customers(customer_id)
);

create table product(
product_id int auto_increment primary key,
name varchar(255),
price double
);

create table order_detail(
order_id int,
product_id int,
order_quantity int,
primary key(order_id,product_id),
foreign key(order_id) references orders(order_id),
foreign key(product_id) references product(product_id)
);

INSERT INTO customers ( Name, age)
VALUES 
  ( 'Minh Quan', 10),
  ( 'Ngoc Oanh', 20),
  ( 'Hong Ha', 50);
INSERT INTO orders ( customer_id, order_date, order_total_price) 
VALUES
( 1, '2006-03-21', NULL),
( 2, '2006-03-23', NULL),
( 1, '2006-03-16', NULL);
INSERT INTO product ( name, price) VALUES
( 'May Giat', 3),
('Tu Lanh', 5),
( 'Dieu Hoa', 7),
( 'Quat', 1),
('Bep Dien', 2);
INSERT INTO order_detail (order_id, product_id, order_quantity) VALUES
(1, 1, 3),
(1, 3, 7),
(1, 4, 2),
(1, 5, 1),
(2, 1, 1),
(2, 3, 4),
(3, 2, 1),
(3, 3, 3);
-- Hiển thị các thông tin  gồm oID, oDate, oPrice 
-- của tất cả các hóa đơn trong bảng Order
select order_id,order_date,order_total_price
from  orders;
-- Hiển thị danh sách các khách hàng đã mua hàng, 
-- và danh sách sản phẩm được mua bởi các khách
select 
c.name,p.name,od.order_quantity,o.order_date
from customers c
join orders o on c.customer_id = o.customer_id
join order_detail od on o.order_id = od.order_id
join product p on od.product_id = p.product_id
group by c.customer_id , p.product_id;

SET GLOBAL sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));
-- Hiển thị tên những khách hàng không mua bất kỳ một sản phẩm nào

select 
ifnull(c.name,"Không rõ") as customer_name, 
ifnull(o.order_id,"Chưa mua hàng") as order_info
from customers c
left join orders o on c.customer_id = o.customer_id;

-- Hiển thị mã hóa đơn, ngày bán và giá tiền của từng hóa đơn
-- (giá một hóa đơn được tính bằng tổng giá bán của từng loại mặt hàng xuất hiện trong hóa đơn. Giá bán của từng loại được tính = odQTY*pPrice)

select o.order_id, o.order_date, sum( od.order_quantity * p.price) as "total price"
from orders o
join order_detail od on o.order_id = od.order_id
join product p on od.product_id = p.product_id
group by o.order_id, o.order_date;
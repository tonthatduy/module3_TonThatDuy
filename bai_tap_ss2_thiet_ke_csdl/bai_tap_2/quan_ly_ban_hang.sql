create database quan_ly_ban_hang;

use quan_ly_ban_hang;

create table customers(
id int auto_increment primary key,
name varchar(100),
age int
);

create table orders(
id int auto_increment primary key,
order_date date,
order_total_price double,
customer_id int,
foreign key(customer_id) references customers(id)
);

create table product(
id int auto_increment primary key,
name varchar(255),
price double
);

create table order_detail(
order_id int,
product_id int,
order_quantity int,
primary key(order_id,product_id),
foreign key(order_id) references orders(id),
foreign key(product_id) references product(id)
);
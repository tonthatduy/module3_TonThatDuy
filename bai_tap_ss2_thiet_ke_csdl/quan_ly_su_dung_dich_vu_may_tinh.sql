create database quan_ly_su_dung_may_tinh;

use quan_ly_su_dung_may_tinh;

create table hang_san_xuat(
id int auto_increment primary key,
ten_hang varchar(100)
);

create table may_tinh(
id int auto_increment primary key,
vi_tri_dat_may varchar(100),
hang_id int,
foreign key(hang_id) references hang_san_xuat(id)
);

create table loai_khach(
id int auto_increment primary key,
ten_loai_khach varchar(100)
);

create table khach_hang(
id int auto_increment primary key,
ten varchar(100),
email varchar(100),
loai_khach_id int,
foreign key(loai_khach_id) references loai_khach(id)
);

create table so_dien_thoai(
so_dien_thoai varchar(11) primary key,
khach_hang_id int,
foreign key (khach_hang_id) references khach_hang(id)
);

create table dich_vu(
id int auto_increment primary key,
ten_dich_vu varchar(100),
gia_dich_vu double
);

create table su_dung_dich_vu(
id int auto_increment primary key,
thoi_gian_bat_dau datetime,
thoi_gian_ket_thuc datetime,
tong_tien bigint,
khach_hang_id int,
may_tinh_id int,
foreign key (khach_hang_id) references khach_hang(id),
foreign key (may_tinh_id) references may_tinh(id)
);

create table chi_tiet_su_dung(
dich_vu_id int,
su_dung_dich_vu_id int,
primary key(dich_vu_id,su_dung_dich_vu_id),
foreign key (dich_vu_id) references dich_vu(id),
foreign key (su_dung_dich_vu_id) references su_dung_dich_vu(id)
);
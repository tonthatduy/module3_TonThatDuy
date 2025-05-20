create database quanly_vattu_nhacungcap;

use quanly_vattu_nhacungcap;

create table phieu_nhap(
id int auto_increment primary key,
ngay_nhap date
);

create table phieu_xuat(
id int auto_increment primary key,
ngay_xuat date
);

create table vat_tu(
id int auto_increment primary key,
ten_vat_tu varchar(50)
);

create table nha_cung_cap(
id int auto_increment primary key,
ten_nha_cung_cap varchar(50),
dia_chi varchar(50)
);

create table so_dien_thoai(
so_dien_thoai varchar(11) primary key,
nha_cung_cap_id int,
foreign key (nha_cung_cap_id) references nha_cung_cap(id)
);

create table don_dat_hang(
id int auto_increment primary key,
ngay_dat_hang date,
nha_cung_cap_id int,
foreign key (nha_cung_cap_id) references nha_cung_cap(id)
);

create table chi_tiet_don_dat_hang(
don_dat_hang_id int,
vat_tu_id int,
primary key(don_dat_hang_id,vat_tu_id),
foreign key (don_dat_hang_id) references don_dat_hang(id),
foreign key (vat_tu_id) references vat_tu(id)
);

create table chi_tiet_phieu_xuat(
phieu_xuat_id int,
vat_tu_id int,
don_gia_xuat bigint,
so_luong_xuat int,
primary key(phieu_xuat_id,vat_tu_id),
foreign key (phieu_xuat_id) references phieu_xuat(id),
foreign key (vat_tu_id) references vat_tu(id)
);

create table chi_tiet_phieu_nhap(
phieu_nhap_id int,
vat_tu_id int,
don_gia_nhap bigint,
so_luong_nhap int,
primary key(phieu_nhap_id,vat_tu_id),
foreign key (phieu_nhap_id) references phieu_nhap(id),
foreign key (vat_tu_id) references vat_tu(id)
);
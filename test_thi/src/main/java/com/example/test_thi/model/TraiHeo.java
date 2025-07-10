package com.example.test_thi.model;

import java.time.LocalDate;

public class TraiHeo {
    private int id;
    private String maSoHeo;
    private String ngayNhapChuong;
    private double trongLuongNhapChuong;
    private String ngayXuatChuong;
    private double trongLuongXuatChuong;
    private int idXuatXu;

    public TraiHeo() {
    }

    public TraiHeo(int id, String maSoHeo, String ngayNhapChuong, double trongLuongNhapChuong, String ngayXuatChuong, double trongLuongXuatChuong, int idXuatXu) {
        this.id = id;
        this.maSoHeo = maSoHeo;
        this.ngayNhapChuong = ngayNhapChuong;
        this.trongLuongNhapChuong = trongLuongNhapChuong;
        this.ngayXuatChuong = ngayXuatChuong;
        this.trongLuongXuatChuong = trongLuongXuatChuong;
        this.idXuatXu = idXuatXu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaSoHeo() {
        return maSoHeo;
    }

    public void setMaSoHeo(String maSoHeo) {
        this.maSoHeo = maSoHeo;
    }

    public String getNgayNhapChuong() {
        return ngayNhapChuong;
    }

    public void setNgayNhapChuong(String ngayNhapChuong) {
        this.ngayNhapChuong = ngayNhapChuong;
    }

    public double getTrongLuongNhapChuong() {
        return trongLuongNhapChuong;
    }

    public void setTrongLuongNhapChuong(double trongLuongNhapChuong) {
        this.trongLuongNhapChuong = trongLuongNhapChuong;
    }

    public String getNgayXuatChuong() {
        return ngayXuatChuong;
    }

    public void setNgayXuatChuong(String ngayXuatChuong) {
        this.ngayXuatChuong = ngayXuatChuong;
    }

    public double getTrongLuongXuatChuong() {
        return trongLuongXuatChuong;
    }

    public void setTrongLuongXuatChuong(double trongLuongXuatChuong) {
        this.trongLuongXuatChuong = trongLuongXuatChuong;
    }

    public int getIdXuatXu() {
        return idXuatXu;
    }

    public void setIdXuatXu(int idXuatXu) {
        this.idXuatXu = idXuatXu;
    }
}

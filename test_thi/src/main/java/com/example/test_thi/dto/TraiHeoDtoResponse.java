package com.example.test_thi.dto;

import java.time.LocalDate;

public class TraiHeoDtoResponse {
    private int id;
    private String maSoHeo;
    private String  ngayNhapChuong;
    private double trongLuongNhapChuong;
    private String  ngayXuatChuong;
    private double trongLuongXuatChuong;
    private String tenXuatXu;

    public TraiHeoDtoResponse() {
    }

    public TraiHeoDtoResponse(int id, String maSoHeo, String ngayNhapChuong, double trongLuongNhapChuong, String ngayXuatChuong, double trongLuongXuatChuong, String tenXuatXu) {
        this.id = id;
        this.maSoHeo = maSoHeo;
        this.ngayNhapChuong = ngayNhapChuong;
        this.trongLuongNhapChuong = trongLuongNhapChuong;
        this.ngayXuatChuong = ngayXuatChuong;
        this.trongLuongXuatChuong = trongLuongXuatChuong;
        this.tenXuatXu = tenXuatXu;
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

    public String getTenXuatXu() {
        return tenXuatXu;
    }

    public void setTenXuatXu(String tenXuatXu) {
        this.tenXuatXu = tenXuatXu;
    }
}

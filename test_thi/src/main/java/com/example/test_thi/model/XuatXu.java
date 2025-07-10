package com.example.test_thi.model;

public class XuatXu {
    private int idXuatXu;
    private String tenXuatXu;

    public XuatXu() {
    }

    public XuatXu(int idXuatXu, String tenXuatXu) {
        this.idXuatXu = idXuatXu;
        this.tenXuatXu = tenXuatXu;
    }

    public int getIdXuatXu() {
        return idXuatXu;
    }

    public void setIdXuatXu(int idXuatXu) {
        this.idXuatXu = idXuatXu;
    }

    public String getTenXuatXu() {
        return tenXuatXu;
    }

    public void setTenXuatXu(String tenXuatXu) {
        this.tenXuatXu = tenXuatXu;
    }
}

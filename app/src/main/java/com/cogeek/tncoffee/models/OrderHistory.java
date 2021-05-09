package com.cogeek.tncoffee.models;

import java.io.Serializable;

public class OrderHistory implements Serializable {
    private String hinh;
    private String ten;
    private int soSanPham;
    private int soTien;

    public OrderHistory(String hinh, String ten, int soSanPham, int soTien) {
        this.hinh = hinh;
        this.ten = ten;
        this.soSanPham = soSanPham;
        this.soTien = soTien;
    }

    public OrderHistory() {
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getSoSanPham() {
        return soSanPham;
    }

    public void setSoSanPham(int soSanPham) {
        this.soSanPham = soSanPham;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }
}

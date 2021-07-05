package com.example.appxuatnhapkho.Object;

public class ObjItemHoaDon {
    private int Id, soLuong;
    private String tenSP, tenKH, ngayThang, soDienThoai;

    public ObjItemHoaDon(int id, int soLuong, String tenSP, String tenKH, String ngayThang, String soDienThoai) {
        Id = id;
        this.soLuong = soLuong;
        this.tenSP = tenSP;
        this.tenKH = tenKH;
        this.ngayThang = ngayThang;
        this.soDienThoai = soDienThoai;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getNgayThang() {
        return ngayThang;
    }

    public void setNgayThang(String ngayThang) {
        this.ngayThang = ngayThang;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
}

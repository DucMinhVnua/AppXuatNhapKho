package com.example.appxuatnhapkho.Object;

public class ObjItemSP {
    private int Id;
    private String tenSp;
    private String giaSp;

    public ObjItemSP(int id, String tenSp, String giaSp) {
        Id = id;
        this.tenSp = tenSp;
        this.giaSp = giaSp;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public String getGiaSp() {
        return giaSp;
    }

    public void setGiaSp(String giaSp) {
        this.giaSp = giaSp;
    }
}

package com.example.things.Model;

public class KategoriModel {
    private String nama,type;
    private int gambarResID; // Menggunakan ID gambar sebagai sumber

    public KategoriModel(String nama, String type, int gambarResID) {
        this.nama = nama;
        this.type = type;
        this.gambarResID = gambarResID;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setGambarResID(int gambarResID) {
        this.gambarResID = gambarResID;
    }

    public String getNama() {
        return nama;
    }

    public int getGambarResID() {
        return gambarResID;
    }
}
